package digit.com.sys.lms.services.interfaces;

import digit.com.sys.lms.exceptions.BorrowException;
import digit.com.sys.lms.exceptions.ServiceException;
import digit.com.sys.lms.models.dtos.BorrowDTO;
import digit.com.sys.lms.models.dtos.LibraryItemDTO;
import digit.com.sys.lms.models.dtos.ReturnDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public interface IBorrowService {

    @Operation(summary = "Borrow a library item")
    ResponseEntity<String> borrowLibraryItem(
            @Parameter(description = "Borrow request details") BorrowDTO borrowDTO
    ) throws ServiceException;

    @Operation(summary = "Return a library item")
    ResponseEntity<String> returnLibraryItem(
            @Parameter(description = "Return request details") ReturnDTO returnDTO
    ) throws ServiceException;

    @Operation(summary = "Check if a library item is available for borrowing")
    boolean isAvailableForBorrowing(
            @Parameter(description = "Borrow request details") BorrowDTO borrowDTO
    ) throws ServiceException;

    @Operation(summary = "Get a list of borrowed library items")
    List<LibraryItemDTO> getBorrowedItems(
            @Parameter(description = "borrowerId ID of the borrower", example = "3e373158-9ba3-4f24-a7f0-0d2e8b0b3b49") String borrowerId
    ) throws BorrowException, ServiceException;
}
