package digit.com.sys.lms.models.entities.bases;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseMemberEntity extends BaseEntity {

    @Schema(description = "The date of birth of the member.", example = "1990-01-01")
    private Date dateOfBirth;

    @Schema(description = "The email address of the member.", example = "firas.a.almaita@gmail.com")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(description = "The phone number of the member.", example = "0790267193")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

}
