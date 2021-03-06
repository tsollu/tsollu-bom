package com.tsollu.dto2;

import com.google.common.base.Strings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tsollu.dto.DTO;
import com.tsollu.exception.ErrorCode;
import com.tsollu.exception.ErrorCodeDefault;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Map;

import cn.hutool.core.util.StrUtil;

/**
 * API Response to caller.
 *
 * @author larry.qi
 * @date 2022-07-02
 */
public class Response extends DTO {

    private static final long serialVersionUID = 1L;

    /**
     * 响应错误码
     */
    private String code;

    /**
     * 响应错误信息
     */
    private String message;

    /**
     * 响应头信息
     */
    @JsonIgnore
    private HttpHeaders headers;

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     */
    public Response(@NonNull ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    /**
     * 返回一个自定义错误码的结果
     *
     * @param errorCode 错误码
     * @return 结果
     */
    public static Response of(@NonNull ErrorCode errorCode) {
        return new Response(errorCode);
    }

    /**
     * 返回一个成功的结果
     *
     * @return 结果
     */
    public static Response of() {
        return of(ErrorCodeDefault.SUCCESS);
    }

    /**
     * 重写错误码的错误信息
     *
     * @param message 错误信息
     * @return 结果
     */
    public Response rewrite(String message) {
        this.message = message;
        return this;
    }

    /**
     * 重写错误码的错误信息
     *
     * @param message 错误信息 - 格式化文本, {} 表示占位符
     * @param params  可变数组
     * @return 结果
     */
    public Response rewrite(String message, Object... params) {
        this.message = StrUtil.format(message, params);
        return this;
    }

    /**
     * 重写错误码的错误信息
     *
     * @param message 错误信息 - 格式化文本, {key} 表示占位符
     * @param map     对象集合
     * @return 结果
     */
    public Response rewrite(String message, Map<?, ?> map) {
        this.message = StrUtil.format(message, map);
        return this;
    }

    public String getCode() {
        return Strings.nullToEmpty(code);
    }

    public String getMessage() {
        return Strings.nullToEmpty(message);
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    /**
     * 设置响应头信息（可选）
     *
     * @param headers 响应头信息
     * @return 结果
     */
    public Response setHeaders(@Nullable HttpHeaders headers) {
        HttpHeaders tempHeaders = new HttpHeaders();
        if (headers != null) {
            tempHeaders.putAll(headers);
        }
        this.headers = HttpHeaders.readOnlyHttpHeaders(tempHeaders);
        return this;
    }

    public boolean isSuccess() {
        return ErrorCodeDefault.isSuccess(getCode());
    }

}
