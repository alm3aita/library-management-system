package digit.com.sys.lms.models.entities.members;

import digit.com.sys.lms.models.entities.bases.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "college_employee")
@Data
public class CollegeEmployee extends Employee {

    @Schema(description = "The department of the college employee.", example = "Mathematics")
    @NotEmpty(message = "Department cannot be empty.")
    private String department;
}
