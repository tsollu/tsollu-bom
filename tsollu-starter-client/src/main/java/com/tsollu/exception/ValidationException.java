package com.tsollu.exception;

/**
 * 请求参数校验异常，有明确的错误语义，不需要记录 Error 日志，不需要 Retry
 *
 * @author larry.qi
 * @date 2022-07-02
 */
public class ValidationException extends BusinessException {
    private static final long serialVersionUID = 1L;

    /**
     * 请求参数校验异常
     */
    public ValidationException() {
        super(ErrorCodeDefault.E0400);
    }

    /**
     * 请求参数校验异常
     *
     * @param reason 错误原因
     */
    public ValidationException(String reason) {
        super(ErrorCodeDefault.E0400, reason);
    }

    /**
     * 请求参数校验异常
     *
     * @param cause 异常
     */
    public ValidationException(Throwable cause) {
        super(ErrorCodeDefault.E0400, cause);
    }

    /**
     * 请求参数校验异常
     *
     * @param reason 错误原因
     * @param cause  异常
     */
    public ValidationException(String reason, Throwable cause) {
        super(ErrorCodeDefault.E0400, reason, cause);
    }

}
