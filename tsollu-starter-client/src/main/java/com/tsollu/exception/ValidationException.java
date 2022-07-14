package com.tsollu.exception;

/**
 * 请求参数校验异常，有明确的错误语义，不需要记录错误（Error）日志，不需要重试（Retry）。
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
        super(ErrorCodeDefault.S0400);
    }

    /**
     * 请求参数校验异常
     *
     * @param errorReason 错误原因
     */
    public ValidationException(String errorReason) {
        super(ErrorCodeDefault.S0400, errorReason);
    }

    /**
     * 请求参数校验异常
     *
     * @param cause 异常
     */
    public ValidationException(Throwable cause) {
        super(ErrorCodeDefault.S0400, cause);
    }

    /**
     * 请求参数校验异常
     *
     * @param errorReason 错误原因
     * @param cause       异常
     */
    public ValidationException(String errorReason, Throwable cause) {
        super(ErrorCodeDefault.S0400, errorReason, cause);
    }

}
