package digit.com.sys.lms.models.entities.transactions;

import digit.com.sys.lms.models.entities.materials.LibraryItem;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "borrow")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @Schema(description = "The unique identifier of the borrow record.", example = "3e373158-9ba3-4f24-a7f0-0d2e8b0b3b49")
    private String id;

    @NotBlank(message = "Borrower ID is required.")
    @Column(name = "borrower_id", nullable = false)
    @Schema(description = "The ID of the borrower.", example = "3e373158-9ba3-4f24-a7f0-0d2e8b0b3b49")
    private String borrowerId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "borrow_date", nullable = false)
    @Schema(description = "The date when the item was borrowed.", example = "2023-09-26T10:00:00Z")
    private Date borrowDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "return_date")
    @Schema(description = "The date when the item was returned.", example = "2023-09-30T14:30:00Z")
    private Date returnDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "library_item_id", nullable = false)
    @Schema(description = "The library item that was borrowed.")
    private LibraryItem libraryItem;
}






