package digit.com.sys.lms.controllers;


import digit.com.sys.lms.exceptions.ServiceException;
import digit.com.sys.lms.models.entities.materials.LibraryItem;
import digit.com.sys.lms.models.enums.LibraryItemType;
import digit.com.sys.lms.services.interfaces.ILibraryItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static digit.com.sys.lms.models.constants.PatternConstants.LIBRARY_ITEM_TYPE;
import static digit.com.sys.lms.models.constants.PatternConstants.UUID_PATTERN;

@RestController
@RequestMapping("/enumerate")
@Tag(name = "Enumerate", description = "ENUMERATE API")
@RequiredArgsConstructor
public class EnumerateController {

    private final ILibraryItemService libraryItemService;

    @Operation(summary = "Get a library item by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Library item retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Library item not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{libraryItemId}")
    public ResponseEntity<LibraryItem> getLibraryItemById(@PathVariable @Pattern(regexp = UUID_PATTERN) String libraryItemId) throws ServiceException {
        LibraryItem libraryItem = libraryItemService.getLibraryItemById(libraryItemId);
        return ResponseEntity.ok(libraryItem);
    }

    @Operation(summary = "Get all library items by type")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of library items retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/type")
    public ResponseEntity<List<LibraryItem>> getAllLibraryItemsByType(
            @RequestParam(value = "lit")
            @NotNull @Pattern(regexp = LIBRARY_ITEM_TYPE, message = "can't do that")
            String libraryItemType) throws ServiceException {

        List<LibraryItem> libraryItems =
                libraryItemService.getAllLibraryItems(LibraryItemType.valueOf(libraryItemType.toUpperCase()));
        return ResponseEntity.ok(libraryItems);
    }

    @Operation(summary = "Get all available library items by type")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of available library items retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/type/available")
    public ResponseEntity<List<LibraryItem>> getAllAvailableLibraryItems(
            @RequestParam(value = "lit") @Pattern(regexp = LIBRARY_ITEM_TYPE) String libraryItemType
    ) throws ServiceException {
        List<LibraryItem> availableItems = libraryItemService.getAllAvailableLibraryItems(LibraryItemType.valueOf(libraryItemType.toUpperCase()), true);
        return ResponseEntity.ok(availableItems);
    }

}
