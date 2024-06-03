package umc.nini.apiPayload.code;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@Builder
public class ReasonDTO {

    private final String message;
    private final String code;
    private final Boolean isSuccess;
    private final HttpStatus httpStatus;

}
