package digit.com.sys.lms.models.entities.members;

import com.fasterxml.jackson.annotation.JsonBackReference;
import digit.com.sys.lms.models.entities.bases.BaseMemberEntity;
import digit.com.sys.lms.models.entities.institutions.College;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student extends BaseMemberEntity {

    @Schema(description = "The major of the student.", example = "Computer Science")
    @NotEmpty(message = "Major cannot be empty.")
    private String major;

    @Schema(description = "The college to which the student is affiliated.")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference(value = "college-students")
    private College college;

}
