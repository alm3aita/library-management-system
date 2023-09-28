package digit.com.sys.lms.repositories;

import digit.com.sys.lms.models.entities.materials.LibraryItem;
import digit.com.sys.lms.models.enums.LibraryItemType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryItemRepository extends JpaRepository<LibraryItem, String> {

    @Operation(summary = "Find library items by library item type")
    List<LibraryItem> findByLibraryItemType(
            @Parameter(description = "The type of library items to retrieve", example = "BOOK") LibraryItemType libraryItemType
    );

    @Operation(summary = "Find available library items by library item type")
    List<LibraryItem> findByLibraryItemTypeAndIsAvailable(
            @Parameter(description = "The type of library items to retrieve", example = "BOOK") LibraryItemType libraryItemType,
            @Parameter(description = "Flag indicating whether the items should be available", example = "true", in = ParameterIn.QUERY) boolean isAvailable
    );
}
