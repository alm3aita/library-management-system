package digit.com.sys.lms.services.interfaces;

import digit.com.sys.lms.exceptions.ServiceException;
import digit.com.sys.lms.models.entities.materials.LibraryItem;
import digit.com.sys.lms.models.enums.LibraryItemType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public interface ILibraryItemService {

    @Operation(summary = "Get a library item by ID")
    LibraryItem getLibraryItemById(
            @Parameter(description = "Library item ID", example = "3e373158-9ba3-4f24-a7f0-0d2e8b0b3b49") String libraryItemId
    ) throws ServiceException;

    @Operation(summary = "Get all library items by type")
    List<LibraryItem> getAllLibraryItems(
            @Parameter(description = "Library item type") LibraryItemType libraryItemType
    ) throws ServiceException;

    @Operation(summary = "Get all available library items by type")
    List<LibraryItem> getAllAvailableLibraryItems(
            @Parameter(description = "Library item type") LibraryItemType libraryItemType,
            @Parameter(description = "Availability status", example = "true") boolean isAvailable
    ) throws ServiceException;

    @Operation(summary = "Create a new library item")
    LibraryItem createLibraryItem(
            @Parameter(description = "Library item details") LibraryItem libraryItem
    ) throws ServiceException;

    @Operation(summary = "Update a library item")
    LibraryItem updateLibraryItem(
            @Parameter(description = "Updated library item details") LibraryItem updatedLibraryItem,
            @Parameter(description = "Library item ID", example = "3e373158-9ba3-4f24-a7f0-0d2e8b0b3b49") String libraryItemId
    ) throws ServiceException;

    @Operation(summary = "Add quantity to a library item")
    String addQuantityToLibraryItem(
            @Parameter(description = "Library item ID", example = "3e373158-9ba3-4f24-a7f0-0d2e8b0b3b49") String libraryItemId,
            @Parameter(description = "Quantity to add", example = "5") int quantityToAdd
    ) throws ServiceException;

    @Operation(summary = "Delete a library item")
    void deleteLibraryItem(
            @Parameter(description = "Library item ID", example = "3e373158-9ba3-4f24-a7f0-0d2e8b0b3b49") String libraryItemId
    ) throws ServiceException;
}
