package digit.com.sys.lms.controllers;

import digit.com.sys.lms.exceptions.ServiceException;
import digit.com.sys.lms.models.dtos.BorrowDTO;
import digit.com.sys.lms.models.dtos.LibraryItemDTO;
import digit.com.sys.lms.models.dtos.ReturnDTO;
import digit.com.sys.lms.services.interfaces.IBorrowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static digit.com.sys.lms.models.constants.PatternConstants.UUID_PATTERN;

@RestController
@Tag(name = "Borrow", description = "Borrow API")
@RequiredArgsConstructor
public class BorrowController {

    private final IBorrowService borrowService;

    @Operation(summary = "Borrow a library item")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item borrowed successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/borrow")
    public ResponseEntity<String> borrowLibraryItem(@RequestBody BorrowDTO borrowDTO) throws ServiceException {
        return borrowService.borrowLibraryItem(borrowDTO);
    }

    @Operation(summary = "Return a library item")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item returned successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/return")
    public ResponseEntity<String> returnLibraryItem(@RequestBody ReturnDTO returnDTO) throws ServiceException {
        return borrowService.returnLibraryItem(returnDTO);
    }

    @Operation(summary = "Get borrowed library items")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of borrowed items retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/borrowed")
    public List<LibraryItemDTO> getBorrowedItems(@RequestParam("borrowerId") @Pattern(regexp = UUID_PATTERN) String borrowerId) throws ServiceException {
        return borrowService.getBorrowedItems(borrowerId);
    }

    @Operation(summary = "Check if a library item is available for borrowing")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Library item is available for borrowing"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/availability")
    public ResponseEntity<String> checkBorrowAvailability(
            @RequestParam("libraryItemId") String libraryItemId,
            @RequestParam("borrowerId") String borrowerId
    ) throws ServiceException {

        if (borrowService.isAvailableForBorrowing(new BorrowDTO(libraryItemId, borrowerId))) {
            return ResponseEntity.ok("Library item is available for borrowing.");
        } else {
            return ResponseEntity.ok("Library item is not available for borrowing.");
        }
    }
}





