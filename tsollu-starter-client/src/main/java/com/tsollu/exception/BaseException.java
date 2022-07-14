package com.tsollu.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import cn.hutool.core.util.StrUtil;

/**
 * 异常基类（抽象类），自定义异常继承该类，便于全局异常处理。
 *
 * @author larry.qi
 * @date 2022-07-02
 */
public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * 异常的错误原因（可选）
     */
    private final String reason;
    /**
     * 异常的错误码（必须）
     */
    private ErrorCode errorCode;
    /**
     * 异常的响应状态码（默认值：200）
     */
    private int httpStatus = 200;

    /**
     * 异常的响应头信息（可选）
     */
    private HttpHeaders headers;

    /**
     * 异常基类
     *
     * @param errorCode 错误码
     */
    public BaseException(@NonNull ErrorCode errorCode) {
        this(errorCode, null, null);
    }

    /**
     * 异常基类
     *
     * @param errorCode 错误码
     * @param reason    错误原因
     */
    public BaseException(@NonNull ErrorCode errorCode, String reason) {
        this(errorCode, reason, null);
    }

    /**
     * 异常基类
     *
     * @param errorCode 错误码
     * @param cause     异常信息
     */
    public BaseException(@NonNull ErrorCode errorCode, Throwable cause) {
        this(errorCode, null, cause);
    }

    /**
     * 异常基类
     *
     * @param errorCode 错误码
     * @param reason    错误原因
     * @param cause     异常信息
     */
    public BaseException(@NonNull ErrorCode errorCode, String reason, Throwable cause) {
        super(reason, cause);
        this.errorCode = errorCode;
        this.reason = reason;
    }

    /**
     * 重写异常错误码的错误信息
     *
     * @param message 错误信息
     * @return BaseException 异常基类
     */
    public BaseException rewrite(String message) {
        this.errorCode = ErrorCodeBuilder.of(errorCode, message);
        return this;
    }

    /**
     * 重写异常错误码的错误信息
     *
     * @param message 错误信息 - 格式化文本, {} 表示占位符
     * @param params  可变数组
     * @return BaseException 异常基类
     */
    public BaseException rewrite(String message, Object... params) {
        this.errorCode = ErrorCodeBuilder.of(errorCode, message, params);
        return this;
    }

    /**
     * 重写异常错误码的错误信息
     *
     * @param message 错误信息 - 格式化文本, {key} 表示占位符
     * @param map     对象集合
     * @return BaseException 异常基类
     */
    public BaseException rewrite(String message, Map<?, ?> map) {
        this.errorCode = ErrorCodeBuilder.of(errorCode, message, map);
        return this;
    }

    /**
     * 获取异常的的错误描述
     *
     * @return 错误描述
     */
    @Override
    public String getMessage() {
        StringBuffer sb = new StringBuffer();
        sb.append(errorCode.getMessage() + "(" + errorCode.getCode() + ")");
        if (StrUtil.isNotBlank(super.getMessage())) {
            sb.append(" - " + super.getMessage());
        }
        return sb.toString();
    }

    /**
     * 获取异常的响应状态码（默认值：200）
     *
     * @return 响应状态码
     */
    public int getHttpStatus() {
        return httpStatus;
    }

    /**
     * 设置异常的响应状态码（默认值：200）
     *
     * @param httpStatus 响应状态码
     * @return BaseException 异常基类
     */
    public BaseException setHttpStatus(int httpStatus) {
        if (httpStatus > 0) {
            this.httpStatus = httpStatus;
        }
        return this;
    }

    /**
     * 设置异常的响应状态码（默认值：200）
     *
     * @param httpStatus 响应状态码
     * @return BaseException 异常基类
     */
    public BaseException setHttpStatus(HttpStatus httpStatus) {
        return setHttpStatus(httpStatus.value());
    }

    /**
     * 获取异常的错误码
     *
     * @return 错误码
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    /**
     * 获取异常的错误原因
     *
     * @return 错误原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 获取异常的响应头信息
     *
     * @return 响应头信息
     */
    public HttpHeaders getHeaders() {
        return headers;
    }

    /**
     * 设置异常的响应头信息
     *
     * @param headers 响应头信息
     * @return BaseException 异常基类
     */
    public BaseException setHeaders(@Nullable HttpHeaders headers) {
        HttpHeaders tempHeaders = new HttpHeaders();
        if (headers != null) {
            tempHeaders.putAll(headers);
        }
        this.headers = HttpHeaders.readOnlyHttpHeaders(tempHeaders);
        return this;
    }

    /**
     * Assert a boolean expression
     *
     * @param expression a boolean expression
     * @throws BaseException if {@code expression} is {@code true}
     */
    public void assertThrow(boolean expression) {
        if (expression) {
            throw this;
        }
    }

    /**
     * Assert that a boolean expression default is {@code true}.
     *
     * @throws BaseException {@code expression} default is {@code true}
     */
    public void assertThrow() {
        assertThrow(true);
    }

    /**
     * Assert a boolean expression
     * <pre class="code">Assert.state(id == null, "The id property must not already be
     * initialized");</pre>
     *
     * @param expression a boolean expression
     * @throws BaseException if {@code expression} is {@code false}
     */
    public void assertState(boolean expression) {
        assertThrow(!expression);
    }

    /**
     * Assert a boolean expression
     * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
     *
     * @param expression a boolean expression
     * @throws BaseException if {@code expression} is {@code false}
     */
    public void assertTrue(boolean expression) {
        assertThrow(!expression);
    }

    /**
     * Assert a boolean expression
     * <pre class="code">Assert.isFalse(i &gt; 0, "The value must be less than zero");</pre>
     *
     * @param expression a boolean expression
     * @throws BaseException if {@code expression} is {@code true}
     */
    public void assertFalse(boolean expression) {
        assertThrow(expression);
    }

    /**
     * Assert that an object is {@code null}.
     * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
     *
     * @param object the object to check
     * @throws BaseException if the object is not {@code null}
     */
    public void assertIsNull(@Nullable Object object) {
        assertThrow(Objects.nonNull(object));
    }

    /**
     * Assert that an object is not {@code null}.
     * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
     *
     * @param object the object to check
     * @throws BaseException if the object is {@code null}
     */
    public void assertNotNull(@Nullable Object object) {
        assertThrow(Objects.isNull(object));
    }

    /**
     * Assert that the given String is not empty; that is, it must not be {@code null} and not the
     * empty String.
     * <pre class="code">Assert.hasLength(name, "Name must not be empty");</pre>
     *
     * @param text the String to check
     * @throws BaseException if the text is empty
     * @see StringUtils#hasLength
     */
    public void assertHasLength(@Nullable String text) {
        assertThrow(!StringUtils.hasLength(text));
    }

    /**
     * Assert that the given String contains valid text content; that is, it must not be {@code
     * null} and must contain at least one non-whitespace character.
     * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
     *
     * @param text the String to check
     * @throws BaseException if the text does not contain valid text content
     * @see StringUtils#hasText
     */
    public void assertHasText(@Nullable String text) {
        assertThrow(!StringUtils.hasText(text));
    }

    /**
     * Assert that the given text does not contain the given substring.
     * <pre class="code">Assert.doesNotContain(name, "rod", "Name must not contain 'rod'");</pre>
     *
     * @param textToSearch the text to search
     * @param substring    the substring to find within the text
     * @throws BaseException if the text contains the substring
     */
    public void assertNotContain(@Nullable String textToSearch, String substring) {
        assertThrow(StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) &&
            textToSearch.contains(substring));
    }

    /**
     * Assert that an array contains elements; that is, it must not be {@code null} and must contain
     * at least one element.
     * <pre class="code">Assert.notEmpty(array, "The array must contain elements");</pre>
     *
     * @param array the array to check
     * @throws BaseException if the object array is {@code null} or contains no elements
     */
    public void assertNotEmpty(@Nullable Object[] array) {
        assertThrow(ObjectUtils.isEmpty(array));
    }

    /**
     * Assert that an array contains no {@code null} elements.
     * <p>Note: Does not complain if the array is empty!
     * <pre class="code">Assert.noNullElements(array, "The array must contain non-null
     * elements");</pre>
     *
     * @param array the array to check
     * @throws BaseException if the object array contains a {@code null} element
     */
    public void assertNoNullElements(@Nullable Object[] array) {
        if (array != null) {
            for (Object element : array) {
                assertThrow(Objects.isNull(element));
            }
        }
    }

    /**
     * Assert that a collection contains elements; that is, it must not be {@code null} and must
     * contain at least one element.
     * <pre class="code">Assert.notEmpty(collection, "Collection must contain elements");</pre>
     *
     * @param collection the collection to check
     * @throws BaseException if the collection is {@code null} or contains no elements
     */
    public void assertNotEmpty(@Nullable Collection<?> collection) {
        assertThrow(CollectionUtils.isEmpty(collection));
    }

    /**
     * Assert that a collection contains no {@code null} elements.
     * <p>Note: Does not complain if the collection is empty!
     * <pre class="code">Assert.noNullElements(collection, "Collection must contain non-null
     * elements");</pre>
     *
     * @param collection the collection to check
     * @throws BaseException if the collection contains a {@code null} element
     */
    public void assertNoNullElements(@Nullable Collection<?> collection) {
        if (collection != null) {
            for (Object element : collection) {
                assertThrow(Objects.isNull(element));
            }
        }
    }

    /**
     * Assert that a Map contains entries; that is, it must not be {@code null} and must contain at
     * least one entry.
     * <pre class="code">Assert.notEmpty(map, "Map must contain entries");</pre>
     *
     * @param map the map to check
     * @throws BaseException if the map is {@code null} or contains no entries
     */
    public void assertNotEmpty(@Nullable Map<?, ?> map) {
        assertThrow(CollectionUtils.isEmpty(map));
    }

}
