package digit.com.sys.lms.models.entities.bases;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.Year;

@Data
@MappedSuperclass
public abstract class BaseMaterialEntity extends BaseEntity {

    @Schema(description = "The author of the material.", required = true, example = "agatha")
    @NotEmpty(message = "Library item Author cannot be empty.")
    private String author;

    @Schema(description = "The year the material was published.", example = "1996")
    private int yearPublished;

    @Schema(description = "Flag indicating whether the material is available.")
    private boolean isAvailable;

    @Schema(description = "The quantity of the material in stock.", example = "10")
    @PositiveOrZero(message = "Quantity must be a positive or zero value.")
    private int quantity;

    public boolean checkYear(int yearPublished) {
        return yearPublished <= Year.now().getValue();
    }

}
