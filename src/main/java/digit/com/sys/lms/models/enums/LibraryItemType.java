package digit.com.sys.lms.models.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public enum LibraryItemType {
    @Schema(description = "A library item of type BOOK.")
    BOOK,

    @Schema(description = "A library item of type JOURNAL.")
    JOURNAL;

    // Validation annotation to ensure only valid enum values are accepted
    @Pattern(regexp = "BOOK|JOURNAL", message = "Invalid library item type. Accepted values are BOOK or JOURNAL.")
    public String value() {
        return this.name();
    }
}
