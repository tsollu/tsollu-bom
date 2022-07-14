package com.tsollu.exception;

import org.springframework.lang.NonNull;

/**
 * 业务处理异常，有明确的业务语义，不需要记录错误（Error） 日志，不需要重试（Retry）。
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
     * @param errorCode   错误码
     * @param errorReason 错误原因
     */
    public BusinessException(@NonNull ErrorCode errorCode, String errorReason) {
        super(errorCode, errorReason);
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
     * @param errorCode   错误码
     * @param errorReason 错误原因
     * @param cause       异常
     */
    public BusinessException(@NonNull ErrorCode errorCode, String errorReason, Throwable cause) {
        super(errorCode, errorReason, cause);
    }

}
