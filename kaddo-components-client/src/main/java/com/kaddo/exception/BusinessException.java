package com.kaddo.exception;

import org.springframework.lang.NonNull;

/**
 * 业务异常，有明确的业务语义，不需要记录 Error 日志，不需要 Retry
 *
 * @author larry.qi
 * @date 2022-07-02
 */
public class BusinessException extends BaseException {
    private static final long serialVersionUID = 1L;

    /**
     * 业务异常
     *
     * @param errorCode 错误码
     */
    public BusinessException(@NonNull ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * 业务异常
     *
     * @param errorCode 错误码
     * @param reason    错误原因
     */
    public BusinessException(@NonNull ErrorCode errorCode, String reason) {
        super(errorCode, reason);
    }

    /**
     * 业务异常
     *
     * @param errorCode 错误码
     * @param cause     异常
     */
    public BusinessException(@NonNull ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * 业务异常
     *
     * @param errorCode 错误码
     * @param reason    错误原因
     * @param cause     异常
     */
    public BusinessException(@NonNull ErrorCode errorCode, String reason, Throwable cause) {
        super(errorCode, reason, cause);
    }

}
