package digit.com.sys.lms.models.entities.bases;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseInstitutionEntity extends BaseEntity {

    @Schema(description = "The address of the institution.", example = "123 mustafa Street, zarqa, jordan")
    private String Address;
}
