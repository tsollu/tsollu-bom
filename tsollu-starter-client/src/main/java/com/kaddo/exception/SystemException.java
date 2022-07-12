package com.kaddo.exception;

import org.springframework.lang.NonNull;

/**
 * 已知的系统异常，需要记录 Error 日志，可以 Retry
 *
 * @author larry.qi
 * @date 2022-07-02
 */
public class SystemException extends BaseException {
    private static final long serialVersionUID = 1L;

    /**
     * 已知的系统异常
     *
     * @param errorCode 错误码
     */
    public SystemException(@NonNull ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * 已知的系统异常
     *
     * @param errorCode 错误码
     * @param reason    错误原因
     */
    public SystemException(@NonNull ErrorCode errorCode, String reason) {
        super(errorCode, reason);
    }

    /**
     * 已知的系统异常
     *
     * @param errorCode 错误码
     * @param cause     异常
     */
    public SystemException(@NonNull ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * 已知的系统异常
     *
     * @param errorCode 错误码
     * @param reason    错误原因
     * @param cause     异常
     */
    public SystemException(@NonNull ErrorCode errorCode, String reason, Throwable cause) {
        super(errorCode, reason, cause);
    }

}
