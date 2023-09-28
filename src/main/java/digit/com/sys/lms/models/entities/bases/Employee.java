package digit.com.sys.lms.models.entities.bases;

import com.fasterxml.jackson.annotation.JsonBackReference;
import digit.com.sys.lms.models.entities.institutions.College;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Employee extends BaseMemberEntity {

    @Schema(description = "The type of employee.", required = true, example = "Professor")
    @NotBlank(message = "Employee type cannot be blank")
    @Size(max = 20, message = "Employee type must be less than or equal to 20 characters")
    private String employeeType;

    @Schema(description = "The college associated with the employee.")
    @ManyToOne
    @JsonBackReference(value = "college-employees")
    private College college;

}
