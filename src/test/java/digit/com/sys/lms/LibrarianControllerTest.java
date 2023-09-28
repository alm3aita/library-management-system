package digit.com.sys.lms;

import digit.com.sys.lms.controllers.LibrarianController;
import digit.com.sys.lms.models.entities.materials.LibraryItem;
import digit.com.sys.lms.services.implementations.LibraryItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class LibrarianControllerTest {
    @InjectMocks
    private LibrarianController librarianController;

    @Mock
    private LibraryItemService libraryItemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        librarianController = new LibrarianController(libraryItemService);
    }

    @Test
    public void testGetLibraryItemById() throws Exception {

        String libraryItemId = "123";
        LibraryItem libraryItem = new LibraryItem();
        when(libraryItemService.getLibraryItemById(libraryItemId)).thenReturn(libraryItem);


        ResponseEntity<LibraryItem> response = librarianController.getLibraryItemById(libraryItemId);


        assertEquals(200, response.getStatusCodeValue());
        assertEquals(libraryItem, response.getBody());
    }


    @Test
    public void testCreateLibraryItem() throws Exception {

        LibraryItem libraryItem = new LibraryItem();
        when(libraryItemService.createLibraryItem(libraryItem)).thenReturn(libraryItem);


        ResponseEntity<LibraryItem> response = librarianController.createLibraryItem(libraryItem);


        assertEquals(200, response.getStatusCode().value());
        assertEquals(libraryItem, response.getBody());
    }

    @Test
    public void testUpdateLibraryItem() throws Exception {

        String libraryItemId = "123";
        LibraryItem updatedLibraryItem = new LibraryItem();
        when(libraryItemService.updateLibraryItem(updatedLibraryItem, libraryItemId)).thenReturn(updatedLibraryItem);


        ResponseEntity<LibraryItem> response = librarianController.updateLibraryItem(libraryItemId, updatedLibraryItem);


        assertEquals(200, response.getStatusCode().value());
        assertEquals(updatedLibraryItem, response.getBody());
    }

    @Test
    public void testAddQuantityToLibraryItem() throws Exception {

        String libraryItemId = "123";
        int quantityToAdd = 5;
        String successMessage = "Quantity added successfully";
        when(libraryItemService.addQuantityToLibraryItem(libraryItemId, quantityToAdd)).thenReturn(successMessage);


        ResponseEntity<String> response = librarianController.addQuantityToLibraryItem(libraryItemId, quantityToAdd);


        assertEquals(200, response.getStatusCode().value());
        assertEquals(successMessage, response.getBody());
    }

    @Test
    public void testDeleteLibraryItem() throws Exception {

        String libraryItemId = "123";
        doNothing().when(libraryItemService).deleteLibraryItem(libraryItemId);

        ResponseEntity<?> response = librarianController.deleteLibraryItem(libraryItemId);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Library item deleted successfully", response.getBody());
    }
}

