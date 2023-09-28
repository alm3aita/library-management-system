package digit.com.sys.lms.models.entities.institutions;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import digit.com.sys.lms.models.entities.bases.BaseInstitutionEntity;
import digit.com.sys.lms.models.entities.members.CollegeEmployee;
import digit.com.sys.lms.models.entities.members.Student;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "college")
@Data
@AllArgsConstructor
@Builder
public class College extends BaseInstitutionEntity {

    @Schema(description = "The library associated with the college.")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "college-library")
    private Library library;

    @Schema(description = "The students associated with the college.")
    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "college-students")
    private List<Student> students;

    @Schema(description = "The employees associated with the college.")
    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "college-employees")
    private List<CollegeEmployee> collegeEmployees;

    public College() {
    }
}
