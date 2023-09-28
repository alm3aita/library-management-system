package digit.com.sys.lms.controllers;


import digit.com.sys.lms.exceptions.ServiceException;
import digit.com.sys.lms.models.entities.materials.LibraryItem;
import digit.com.sys.lms.services.interfaces.ILibraryItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static digit.com.sys.lms.models.constants.PatternConstants.UUID_PATTERN;

@RestController
@RequestMapping("/library-item")
@RequiredArgsConstructor
@Validated
@Tag(name = "LibraryItem", description = "Library Item API")
public class LibrarianController {

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


    @Operation(summary = "Create a new library item")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Library item created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<LibraryItem> createLibraryItem(@RequestBody @Valid LibraryItem libraryItem) throws ServiceException {
        return ResponseEntity.ok(libraryItemService.createLibraryItem(libraryItem));
    }

    @Operation(summary = "Update a library item by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Library item updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Library item not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{libraryItemId}")
    public ResponseEntity<LibraryItem> updateLibraryItem(
            @PathVariable @Pattern(regexp = UUID_PATTERN) String libraryItemId,
            @RequestBody @Valid LibraryItem updatedLibraryItem) throws ServiceException {

        return ResponseEntity.ok(libraryItemService.updateLibraryItem(updatedLibraryItem, libraryItemId));
    }

    @Operation(summary = "Add quantity to a library item by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quantity added successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Library item not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{libraryItemId}/add-quantity")
    public ResponseEntity<String> addQuantityToLibraryItem(
            @PathVariable @Pattern(regexp = UUID_PATTERN) String libraryItemId,
            @RequestParam @Positive(message = "QuantityToAdd must be positive") @NotNull int quantityToAdd
    ) throws ServiceException {
        return ResponseEntity.ok(libraryItemService.addQuantityToLibraryItem(libraryItemId, quantityToAdd));
    }

    @Operation(summary = "Delete a library item by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Library item deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Library item not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{libraryItemId}")
    public ResponseEntity<?> deleteLibraryItem(@PathVariable @Pattern(regexp = UUID_PATTERN) String libraryItemId) throws ServiceException {
        libraryItemService.deleteLibraryItem(libraryItemId);
        return ResponseEntity.ok("Library item deleted successfully");
    }
}


