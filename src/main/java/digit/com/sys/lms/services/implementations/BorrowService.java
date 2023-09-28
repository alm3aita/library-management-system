package digit.com.sys.lms.services.implementations;

import digit.com.sys.lms.exceptions.BorrowException;
import digit.com.sys.lms.exceptions.ReturnException;
import digit.com.sys.lms.exceptions.ServiceException;
import digit.com.sys.lms.models.dtos.BorrowDTO;
import digit.com.sys.lms.models.dtos.LibraryItemDTO;
import digit.com.sys.lms.models.dtos.ReturnDTO;
import digit.com.sys.lms.models.entities.materials.LibraryItem;
import digit.com.sys.lms.models.entities.transactions.Borrow;
import digit.com.sys.lms.repositories.BorrowRepository;
import digit.com.sys.lms.repositories.CollegeRepository;
import digit.com.sys.lms.services.interfaces.IBorrowService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowService implements IBorrowService {

    private final BorrowRepository borrowRepository;

    private final LibraryItemService libraryItemService;

    private final CollegeRepository collegeRepository;

    @Override
    public ResponseEntity<String> borrowLibraryItem(BorrowDTO borrowDTO) throws ServiceException {

        if (collegeRepository
                .findAllIdsFromAllUsers()
                .stream()
                .noneMatch(id -> id.equals(borrowDTO.getBorrowerId()))) {
            throw new BorrowException("borrower id doesn't exist");
        }

        LibraryItem libraryItem = libraryItemService.getLibraryItemById(borrowDTO.getLibraryItemId());

        if (!isAvailableForBorrowing(borrowDTO)) {
            throw new BorrowException("library item is not available for borrowing");
        }

        libraryItem.setQuantity(libraryItem.getQuantity() - 1);

        if (libraryItem.getQuantity() == 0) {
            libraryItem.setAvailable(false);
        }

        libraryItemService.updateLibraryItem(libraryItem, libraryItem.getId());

        Borrow borrow = Borrow.builder()
                .borrowDate(new Date())
                .libraryItem(libraryItem)
                .borrowerId(borrowDTO.getBorrowerId())
                .build();

        borrowRepository.save(borrow);

        return ResponseEntity.ok("Item borrowed successfully.");
    }

    @Override
    public ResponseEntity<String> returnLibraryItem(ReturnDTO returnDTO) throws ServiceException {

        LibraryItem libraryItem = libraryItemService.getLibraryItemById(returnDTO.getLibraryItemId());


        Optional<Borrow> borrowOptional = borrowRepository.
                findByLibraryItemIdAndBorrowerIdAndReturnDateIsNull(
                        libraryItem.getId(),
                        returnDTO.getBorrowerId());

        if (borrowOptional.isEmpty()) {
            throw new ReturnException("library item has not been borrowed before");
        }


        libraryItem.setAvailable(true);
        libraryItem.setQuantity(libraryItem.getQuantity() + 1);

        if (libraryItem.getQuantity() != 0) {
            libraryItem.setAvailable(true);
        }

        libraryItemService.updateLibraryItem(libraryItem, libraryItem.getId());

        Borrow borrow = borrowOptional.get();

        borrow.setReturnDate(new Date());
        borrowRepository.save(borrow);

        return ResponseEntity.ok("Item returned successfully.");
    }

    @Override
    public List<LibraryItemDTO> getBorrowedItems(String borrowerId) throws ServiceException {

        if (collegeRepository
                .findAllIdsFromAllUsers()
                .stream()
                .noneMatch(id -> id.equals(borrowerId))) {
            throw new BorrowException("borrower id doesn't exist");
        }

        List<Borrow> borrowings = borrowRepository.findByBorrowerIdAndReturnDateIsNull(borrowerId);


        return borrowings.stream()
                .map(borrow -> {
                    LibraryItemDTO dto = new LibraryItemDTO();
                    dto.setName(borrow.getLibraryItem().getName());

                    return dto;
                })
                .collect(Collectors.toList());
    }

    private boolean hasBorrowedSameItem(BorrowDTO borrowDTO) {

        return borrowRepository.existsByLibraryItemIdAndBorrowerIdAndReturnDateIsNull(
                borrowDTO.getLibraryItemId(),
                borrowDTO.getBorrowerId());
    }

    @Override
    public boolean isAvailableForBorrowing(BorrowDTO borrowDTO) throws ServiceException {

        LibraryItem libraryItem = libraryItemService.getLibraryItemById(borrowDTO.getLibraryItemId());

        if (hasBorrowedSameItem(borrowDTO)) {
            throw new BorrowException("You have already borrowed this library item.");
        }

        return libraryItem != null && libraryItem.isAvailable() && libraryItem.getQuantity() > 0;
    }

}
