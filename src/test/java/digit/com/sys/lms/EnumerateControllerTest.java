package digit.com.sys.lms;

import digit.com.sys.lms.controllers.EnumerateController;
import digit.com.sys.lms.exceptions.ServiceException;
import digit.com.sys.lms.models.entities.materials.LibraryItem;
import digit.com.sys.lms.models.enums.LibraryItemType;
import digit.com.sys.lms.services.interfaces.ILibraryItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EnumerateControllerTest {

    @InjectMocks
    private EnumerateController enumerateController;

    @Mock
    private ILibraryItemService libraryItemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetLibraryItemById() throws ServiceException {

        LibraryItem expectedLibraryItem = new LibraryItem();
        when(libraryItemService.getLibraryItemById(anyString())).thenReturn(expectedLibraryItem);

        ResponseEntity<LibraryItem> response = enumerateController.getLibraryItemById("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedLibraryItem, response.getBody());

        verify(libraryItemService).getLibraryItemById("123");
    }

    @Test
    public void testGetAllLibraryItemsByType() throws ServiceException {

        LibraryItemType itemType = LibraryItemType.BOOK;
        List<LibraryItem> expectedLibraryItems = Arrays.asList(new LibraryItem(), new LibraryItem());
        when(libraryItemService.getAllLibraryItems(itemType)).thenReturn(expectedLibraryItems);

        ResponseEntity<List<LibraryItem>> response = enumerateController.getAllLibraryItemsByType(itemType.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedLibraryItems, response.getBody());

        verify(libraryItemService).getAllLibraryItems(itemType);
    }

    @Test
    public void testGetAllAvailableLibraryItems() throws ServiceException {

        LibraryItemType itemType = LibraryItemType.BOOK;
        List<LibraryItem> expectedLibraryItems = Arrays.asList(new LibraryItem(), new LibraryItem());
        when(libraryItemService.getAllAvailableLibraryItems(itemType, true)).thenReturn(expectedLibraryItems);

        ResponseEntity<List<LibraryItem>> response = enumerateController.getAllAvailableLibraryItems(itemType.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedLibraryItems, response.getBody());

        verify(libraryItemService).getAllAvailableLibraryItems(itemType, true);
    }

}