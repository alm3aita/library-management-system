package digit.com.sys.lms.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import static digit.com.sys.lms.models.constants.PatternConstants.UUID_PATTERN;

@Data
@AllArgsConstructor
public class ReturnDTO {

    @NotBlank(message = "Library item ID is required")
    @Pattern(regexp = UUID_PATTERN)
    @Schema(description = "The ID of the library item to return.", example = "3e373158-9ba3-4f24-a7f0-0d2e8b0b3b49")
    private String libraryItemId;

    @NotBlank(message = "Borrower ID is required")
    @Pattern(regexp = UUID_PATTERN)
    @Schema(description = "The ID of the borrower.", example = "3e373158-9ba3-4f24-a7f0-0d2e8b0b3b49")
    private String borrowerId;
}
