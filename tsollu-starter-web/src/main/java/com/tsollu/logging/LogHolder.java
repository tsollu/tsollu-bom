package com.tsollu.logging;

import com.google.common.base.Strings;

import com.tsollu.KaddoConstants;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.MDC;

import java.util.Optional;

import cn.hutool.core.util.StrUtil;

public class LogHolder {

    /**
     * 获取一个随机字符串
     *
     * @return TraceId 请求追踪标识
     */
    public static String getTraceId() {
        return getTraceId(12);
    }

    /**
     * 设置一个请求追踪标识
     *
     * @param traceId 请求追踪标识
     */
    public static void setTraceId(String traceId) {
        setAndGetTraceId(traceId);
    }

    /**
     * 获取一个随机字符串
     *
     * @param length 字符串长度（6-32位）
     * @return TraceId 请求追踪标识
     */
    public static String getTraceId(int length) {
        if (length > 32 || length < 6) {
            throw new IllegalArgumentException(StrUtil.format("Length must be between {} and {}.", 6, 32));
        }
        return RandomStringUtils.randomAlphanumeric(length);
    }

    /**
     * 获取当前的TraceId，没有则随机生成一个随机字符串。
     *
     * @return TraceId 请求追踪标识
     */
    public static String getCurrentTraceId() {
        return Optional.ofNullable(Strings.emptyToNull(MDC.get(KaddoConstants.LOG_TRACE_ID)))
            .orElseGet(() -> setAndGetTraceId(LogHolder.getTraceId()));
    }

    /**
     * 设置并获取一个请求追踪标识
     *
     * @param traceId 请求追踪标识
     * @return 请求追踪标识
     */
    private static String setAndGetTraceId(String traceId) {
        MDC.put(KaddoConstants.LOG_TRACE_ID, traceId);
        return traceId;
    }

    /**
     * 释放线程中的缓存
     */
    public static void clear() {
        MDC.remove(KaddoConstants.LOG_TRACE_ID);
    }

}
