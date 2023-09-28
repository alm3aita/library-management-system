package digit.com.sys.lms.models.entities.bases;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @Schema(description = "The unique identifier for the entity.", example = "3e373158-9ba3-4f24-a7f0-0d2e8b0b3b49")
    private String id;

    @Column(name = "name")
    @NotEmpty(message = "name cannot be empty.")
    @Schema(description = "The name of the entity.", required = true)
    private String name;

}