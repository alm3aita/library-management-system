package digit.com.sys.lms.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceException extends Exception {

    @Schema(description = "Error message")
    private String message;
}
