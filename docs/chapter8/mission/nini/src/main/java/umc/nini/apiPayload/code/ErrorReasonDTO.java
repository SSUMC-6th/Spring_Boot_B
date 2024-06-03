package umc.nini.apiPayload.code;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ErrorReasonDTO {
    private final HttpStatus httpStatus;
    private final String message;
    private final String code;
    private final Boolean isSuccess;
}

