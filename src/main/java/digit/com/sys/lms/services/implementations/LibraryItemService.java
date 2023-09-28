package digit.com.sys.lms.services.implementations;


import digit.com.sys.lms.exceptions.LibraryItemServiceException;
import digit.com.sys.lms.exceptions.ServiceException;
import digit.com.sys.lms.models.entities.materials.LibraryItem;
import digit.com.sys.lms.models.enums.LibraryItemType;
import digit.com.sys.lms.repositories.LibraryItemRepository;
import digit.com.sys.lms.services.interfaces.ILibraryItemService;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class LibraryItemService implements ILibraryItemService {

    private final LibraryItemRepository libraryItemRepository;

    private final Validator validator;

    @Override
    public LibraryItem getLibraryItemById(String libraryItemId) throws ServiceException {
        return libraryItemRepository.findById(libraryItemId).orElseThrow(() -> new LibraryItemServiceException("libraryItem not present"));
    }

    @Override
    public List<LibraryItem> getAllLibraryItems(LibraryItemType libraryItemType) throws ServiceException {
        return libraryItemRepository.findByLibraryItemType(libraryItemType);
    }

    @Override
    public List<LibraryItem> getAllAvailableLibraryItems(LibraryItemType libraryItemType, boolean isAvailable) throws ServiceException {
        return libraryItemRepository.findByLibraryItemTypeAndIsAvailable(libraryItemType, true);
    }


    @Override
    public LibraryItem createLibraryItem(@NotNull LibraryItem libraryItem) throws ServiceException {

        validateLibraryItem(libraryItem);

        return libraryItemRepository.save(libraryItem);
    }

    @Override
    public LibraryItem updateLibraryItem(@NotNull LibraryItem updatedLibraryItem, String libraryItemId) throws ServiceException {

        updatedLibraryItem.setId(libraryItemId);

        validateLibraryItem(updatedLibraryItem);

        return libraryItemRepository.save(updatedLibraryItem);
    }

    @Override
    public String addQuantityToLibraryItem(String libraryItemId, int quantityToAdd) throws ServiceException {
        Optional<LibraryItem> libraryItemOptional = libraryItemRepository.findById(libraryItemId);

        if (libraryItemOptional.isPresent()) {
            LibraryItem libraryItem = libraryItemOptional.get();

            libraryItem.setQuantity(libraryItem.getQuantity() + quantityToAdd);
            libraryItem.setAvailable(true);


            libraryItemRepository.save(libraryItem);


            return "Quantity added successfully, Quantity now is : " + libraryItem.getQuantity();
        } else {
            throw new LibraryItemServiceException("Library item not found with ID: " + libraryItemId);
        }
    }

    @Override
    public void deleteLibraryItem(String libraryItemId) throws ServiceException {
        Optional<LibraryItem> existingLibraryItem = libraryItemRepository.findById(libraryItemId);
        if (existingLibraryItem.isEmpty()) {
            throw new IllegalArgumentException("Library item with ID " + libraryItemId + " not found.");
        }

        libraryItemRepository.deleteById(libraryItemId);
    }

    private void validateLibraryItem(LibraryItem libraryItem) throws ServiceException {
        Set<ConstraintViolation<LibraryItem>> violations = validator.validate(libraryItem);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Validation failed for LibraryItem: " + violations.toString());
        }
    }
}


