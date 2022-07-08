package com.kaddo.framework.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * 在日志文件中打印请求追踪日志，自动生成一个随机字符串。
 */
public class LogTraceIdConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        return LogHolder.getCurrentTraceId();
    }

}
