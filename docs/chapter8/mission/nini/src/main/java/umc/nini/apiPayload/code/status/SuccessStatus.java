package umc.nini.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.nini.apiPayload.code.BaseCode;
import umc.nini.apiPayload.code.ReasonDTO;

@AllArgsConstructor
@Getter
public enum SuccessStatus implements BaseCode {
    _OK(HttpStatus.OK, "2000", "Ok");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}