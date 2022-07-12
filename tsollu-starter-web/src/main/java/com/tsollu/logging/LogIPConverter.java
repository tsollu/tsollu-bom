package com.tsollu.logging;

import org.apache.commons.lang3.StringUtils;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.hutool.core.net.NetUtil;

/**
 * 在日志文件中打印服务器IP地址，便于在集群环境中定位到日志所有在机器。
 */
public class LogIPConverter extends ClassicConverter {

    private static final String IP;

    static {
        IP = StringUtils.defaultIfBlank(NetUtil.getLocalhostStr(), NetUtil.getLocalHostName());
    }

    @Override
    public String convert(ILoggingEvent event) {
        return IP;
    }

}
