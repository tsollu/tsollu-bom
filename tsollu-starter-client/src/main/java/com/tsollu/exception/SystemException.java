package com.tsollu.exception;

import org.springframework.lang.NonNull;

/**
 * 已知的系统异常，需要记录错误（Error）日志，可以重试（Retry）。
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
     * @param errorCode   错误码
     * @param errorReason 错误原因
     */
    public SystemException(@NonNull ErrorCode errorCode, String errorReason) {
        super(errorCode, errorReason);
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
     * @param errorCode   错误码
     * @param errorReason 错误原因
     * @param cause       异常
     */
    public SystemException(@NonNull ErrorCode errorCode, String errorReason, Throwable cause) {
        super(errorCode, errorReason, cause);
    }

}
