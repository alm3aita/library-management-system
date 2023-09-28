package digit.com.sys.lms.models.entities.institutions;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import digit.com.sys.lms.models.entities.bases.BaseInstitutionEntity;
import digit.com.sys.lms.models.entities.materials.LibraryItem;
import digit.com.sys.lms.models.entities.members.Librarian;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "library")
@Data
public class Library extends BaseInstitutionEntity {

    @Schema(description = "The college associated with the library.")
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "library", cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @JsonBackReference(value = "college-library")
    private College college;

    @Schema(description = "The library items associated with the library.")
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "library-library-items")
    private List<LibraryItem> libraryItems;

    @Schema(description = "The librarians associated with the library.")
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "library-librarians")
    private List<Librarian> librarians;
}
