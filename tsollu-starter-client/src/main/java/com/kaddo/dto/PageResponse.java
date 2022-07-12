package com.kaddo.dto;

import com.kaddo.exception.ErrorCode;
import com.kaddo.exception.ErrorCodeDefault;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * API Response with batch page record to return, usually use in page query.
 *
 * @author larry.qi
 * @date 2022-07-02
 */
public class PageResponse<T> extends Response {

    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long totalCount;

    /**
     * 返回结果集
     */
    private Collection<T> data;

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     * @param data      泛型集合
     */
    private PageResponse(@NonNull ErrorCode errorCode, @Nullable Collection<T> data) {
        super(errorCode);
        this.data = data;
    }

    /**
     * 返回一个失败的结果
     *
     * @param errorCode 错误码
     * @return 结果
     */
    public static PageResponse buildFailure(@NonNull ErrorCode errorCode) {
        return buildFailure(errorCode, null);
    }

    /**
     * 返回一个失败的结果
     *
     * @param errorCode 错误码
     * @param data      泛型集合
     * @return 结果
     */
    public static <T> PageResponse<T> buildFailure(@NonNull ErrorCode errorCode, @Nullable Collection<T> data) {
        return new PageResponse<>(errorCode, data);
    }

    /**
     * 返回一个成功的结果
     *
     * @param data 泛型集合
     * @return 结果
     */
    public static <T> PageResponse<T> buildSuccess(@Nullable Collection<T> data) {
        return buildFailure(ErrorCodeDefault.SUCCESS, data);
    }

    /**
     * 返回一个成功的结果
     *
     * @param data       泛型集合
     * @param totalCount 总记录数
     * @return 结果
     */
    public static <T> PageResponse<T> buildSuccess(@Nullable Collection<T> data, long totalCount) {
        return buildFailure(ErrorCodeDefault.SUCCESS, data).setTotalCount(totalCount);
    }

    /**
     * 返回一个成功的结果
     *
     * @return 结果
     */
    public static PageResponse buildSuccess() {
        return buildSuccess(null);
    }

    /**
     * 重写错误信息 - 支持已定义的错误代码，重写错误信息
     *
     * @param message 错误信息
     * @return 结果
     */
    @Override
    public PageResponse rewrite(String message) {
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
    public PageResponse rewrite(String message, Object... params) {
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
    public PageResponse rewrite(String message, Map<?, ?> map) {
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
    public PageResponse setHeaders(@Nullable HttpHeaders headers) {
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
    public PageResponse setReason(String reason) {
        super.setReason(reason);
        return this;
    }

    public long getTotalCount() {
        return (totalCount >= 0) ? totalCount : 0L;
    }

    /**
     * 设置总记录数（可选）
     *
     * @param totalCount 总记录数
     * @return 结果
     */
    public PageResponse setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public List<T> getData() {
        return Objects.isNull(data) ? Collections.emptyList() : new ArrayList<>(data);
    }

}
