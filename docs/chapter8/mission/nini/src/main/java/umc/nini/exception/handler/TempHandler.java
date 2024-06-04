package umc.nini.exception.handler;

import umc.nini.apiPayload.code.BaseErrorCode;
import umc.nini.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
