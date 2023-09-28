package digit.com.sys.lms.models.entities.materials;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import digit.com.sys.lms.models.entities.bases.BaseMaterialEntity;
import digit.com.sys.lms.models.entities.institutions.Library;
import digit.com.sys.lms.models.entities.transactions.Borrow;
import digit.com.sys.lms.models.enums.LibraryItemType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "libraryItem")
@Data
public class LibraryItem extends BaseMaterialEntity {

    @Schema(description = "The type of library item (e.g., Book, Journal).", example = "Book")
    @Enumerated(EnumType.STRING)
    private LibraryItemType libraryItemType;

    @Schema(description = "The list of borrow records associated with the library item.")
    @OneToMany(mappedBy = "libraryItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "library-item-borrows")
    private List<Borrow> borrows;

    @Schema(description = "The library to which the library item belongs.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", nullable = false)
    @JsonBackReference(value = "library-library-items")
    private Library library;
}
