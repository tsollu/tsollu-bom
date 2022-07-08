package com.kaddo.dto;

import com.kaddo.exception.ErrorCode;
import com.kaddo.exception.ErrorCodeDefault;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * API Response with single record to return.
 *
 * @author larry.qi
 * @date 2022-07-02
 */
public class SingleResponse<T> extends Response {

    private static final long serialVersionUID = 1L;

    /**
     * 返回结果对象（泛型对象）
     */
    private T data;

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     * @param data      泛型对象
     */
    public SingleResponse(@NonNull ErrorCode errorCode, @Nullable T data) {
        super(errorCode);
        this.data = data;
    }

    /**
     * 返回一个失败的结果
     *
     * @param errorCode 错误码
     * @return 结果
     */
    public static SingleResponse buildFailure(@NonNull ErrorCode errorCode) {
        return buildFailure(errorCode, null);
    }

    /**
     * 返回一个失败的结果
     *
     * @param errorCode 错误码
     * @param data      泛型对象
     * @return 结果
     */
    public static <T> SingleResponse<T> buildFailure(@NonNull ErrorCode errorCode, @Nullable T data) {
        return new SingleResponse<>(errorCode, data);
    }

    /**
     * 返回一个成功的结果
     *
     * @param data 泛型对象
     * @return 结果
     */
    public static <T> SingleResponse<T> buildSuccess(@Nullable T data) {
        return buildFailure(ErrorCodeDefault.SUCCESS, data);
    }

    /**
     * 返回一个成功的结果
     *
     * @return 结果
     */
    public static SingleResponse buildSuccess() {
        return buildSuccess(null);
    }

    /**
     * 重写错误信息 - 支持已定义的错误代码，重写错误信息
     *
     * @param message 错误信息
     * @return 结果
     */
    @Override
    public SingleResponse rewrite(String message) {
        super.rewrite(message);
        return this;
    }

    /**
     * 重写错误信息 - 支持已定义的错误代码，重写错误信息
     *
     * @param message 错误信息 - 格式化文本, {} 表示占位符
     * @param params  可变数组
     * @return 结果
     */
    @Override
    public SingleResponse rewrite(String message, Object... params) {
        super.rewrite(message, params);
        return this;
    }

    /**
     * 重写错误信息 - 支持已定义的错误代码，重写错误信息
     *
     * @param message 错误信息 - 格式化文本, {key} 表示占位符
     * @param map     对象集合
     * @return 结果
     */
    @Override
    public SingleResponse rewrite(String message, Map<?, ?> map) {
        super.rewrite(message, map);
        return this;
    }

    /**
     * 设置响应头信息（可选）
     *
     * @param headers 响应头信息
     * @return 结果
     */
    @Override
    public SingleResponse setHeaders(@Nullable HttpHeaders headers) {
        super.setHeaders(headers);
        return this;
    }

    /**
     * 设置错误原因（可选）
     *
     * @param reason 错误原因
     * @return 结果
     */
    @Override
    public SingleResponse setReason(@Nullable String reason) {
        super.setReason(reason);
        return this;
    }

    public T getData() {
        return data;
    }

}
