package digit.com.sys.lms;

import digit.com.sys.lms.controllers.BorrowController;
import digit.com.sys.lms.exceptions.BorrowException;
import digit.com.sys.lms.exceptions.ReturnException;
import digit.com.sys.lms.exceptions.ServiceException;
import digit.com.sys.lms.models.dtos.BorrowDTO;
import digit.com.sys.lms.models.dtos.LibraryItemDTO;
import digit.com.sys.lms.models.dtos.ReturnDTO;
import digit.com.sys.lms.services.interfaces.IBorrowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class BorrowControllerTest {
    @InjectMocks
    private BorrowController borrowController;

    @Mock
    private IBorrowService borrowService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        borrowController = new BorrowController(borrowService);
    }

    @Test
    public void testBorrowLibraryItem() throws Exception {

        BorrowDTO borrowDTO = new BorrowDTO("libraryItemId", "borrowerId");
        when(borrowService.borrowLibraryItem(borrowDTO)).thenReturn(ResponseEntity.ok("Item borrowed successfully"));


        ResponseEntity<String> response = borrowController.borrowLibraryItem(borrowDTO);


        assertEquals(200, response.getStatusCode().value());
        assertEquals("Item borrowed successfully", response.getBody());
    }

    @Test
    public void testBorrowLibraryItem_InvalidInput() throws Exception {

        BorrowDTO borrowDTO = new BorrowDTO("", "");
        when(borrowService.borrowLibraryItem(borrowDTO)).thenThrow(new BorrowException("Invalid input"));

        assertThrows(BorrowException.class, () -> borrowController.borrowLibraryItem(borrowDTO));
    }

    @Test
    public void testReturnLibraryItem() throws Exception {

        ReturnDTO returnDTO = new ReturnDTO("libraryItemId", "borrowerId");
        when(borrowService.returnLibraryItem(returnDTO)).thenReturn(ResponseEntity.ok("Item returned successfully"));


        ResponseEntity<String> response = borrowController.returnLibraryItem(returnDTO);


        assertEquals(200, response.getStatusCode().value());
        assertEquals("Item returned successfully", response.getBody());
    }

    @Test
    public void testReturnLibraryItem_InvalidInput() throws Exception {

        ReturnDTO returnDTO = new ReturnDTO("", "");
        when(borrowService.returnLibraryItem(returnDTO)).thenThrow(new ReturnException("Invalid input"));

        assertThrows(ReturnException.class, () -> borrowController.returnLibraryItem(returnDTO));
    }

    @Test
    public void testGetBorrowedItems() throws ServiceException {

        String borrowerId = "borrowerId";
        List<LibraryItemDTO> borrowedItems = new ArrayList<>();
        when(borrowService.getBorrowedItems(borrowerId)).thenReturn(borrowedItems);


        List<LibraryItemDTO> response = borrowController.getBorrowedItems(borrowerId);


        assertEquals(borrowedItems, response);
    }

    @Test
    public void testCheckBorrowAvailability_Available() throws Exception {

        String libraryItemId = "libraryItemId";
        String borrowerId = "borrowerId";
        when(borrowService.isAvailableForBorrowing(new BorrowDTO(libraryItemId, borrowerId))).thenReturn(true);


        ResponseEntity<String> response = borrowController.checkBorrowAvailability(libraryItemId, borrowerId);


        assertEquals(200, response.getStatusCode().value());
        assertEquals("Library item is available for borrowing.", response.getBody());
    }

    @Test
    public void testCheckBorrowAvailability_NotAvailable() throws Exception {

        String libraryItemId = "libraryItemId";
        String borrowerId = "borrowerId";
        when(borrowService.isAvailableForBorrowing(new BorrowDTO(libraryItemId, borrowerId))).thenReturn(false);


        ResponseEntity<String> response = borrowController.checkBorrowAvailability(libraryItemId, borrowerId);


        assertEquals(200, response.getStatusCode().value());
        assertEquals("Library item is not available for borrowing.", response.getBody());
    }
}
