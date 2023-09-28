package digit.com.sys.lms.repositories;

import digit.com.sys.lms.models.entities.transactions.Borrow;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, String> {

    @Operation(summary = "Find all borrowing records by borrower ID where return date is null")
    List<Borrow> findByBorrowerIdAndReturnDateIsNull(
            @Parameter(description = "The ID of the borrower", example = "3e373158-9ba3-4f24-a7f0-0d2e8b0b3b49") String memberId
    );

    @Operation(summary = "Find a borrowing record by library item ID and borrower ID where return date is null")
    Optional<Borrow> findByLibraryItemIdAndBorrowerIdAndReturnDateIsNull(
            @Parameter(description = "The ID of the library item", example = "3e373158-9ba3-4f24-a7f0-0d2e8b0b3b49") String libraryItem_id,
            @Parameter(description = "The ID of the borrower", example = "3e373158-9ba3-4f24-a7f0-0d2e8b0b3b49") String borrowerId
    );

    @Operation(summary = "Check if a borrowing record exists for a library item and borrower where return date is null")
    boolean existsByLibraryItemIdAndBorrowerIdAndReturnDateIsNull(
            @Parameter(description = "The ID of the library item", example = "3e373158-9ba3-4f24-a7f0-0d2e8b0b3b49") String libraryItemId,
            @Parameter(description = "The ID of the borrower", example = "3e373158-9ba3-4f24-a7f0-0d2e8b0b3b49") String borrowerId
    );
}