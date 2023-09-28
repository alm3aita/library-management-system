package digit.com.sys.lms.models.entities.members;

import com.fasterxml.jackson.annotation.JsonBackReference;
import digit.com.sys.lms.models.entities.bases.Employee;
import digit.com.sys.lms.models.entities.institutions.Library;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "librarian")
@Data
public class Librarian extends Employee {

    @Schema(description = "The library to which the librarian is assigned.")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference(value = "library-librarians")
    private Library library;
}
