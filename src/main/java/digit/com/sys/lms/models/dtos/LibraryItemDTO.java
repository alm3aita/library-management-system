package digit.com.sys.lms.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LibraryItemDTO {

    @Schema(description = "The name of the library item.", example = "Introduction to Java Programming")
    private String name;
}
