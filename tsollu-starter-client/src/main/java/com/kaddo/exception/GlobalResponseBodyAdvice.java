package com.kaddo.exception;

import com.kaddo.dto.Response;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.zalando.problem.Problem;

import java.util.Optional;

/**
 * @author larry.qi
 * @date 2022-07-05
 */
@Configuration
@ConditionalOnWebApplication
@RestControllerAdvice
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private static ErrorCode convert(final String statusCode, final String defaultMsg) {
        String code = "E0" + statusCode;
        final ErrorCodeDefault[] list = ErrorCodeDefault.values();
        for (final ErrorCodeDefault errorCodeDefault : list) {
            if (StringUtils.equals(errorCodeDefault.getCode(), code)) {
                return errorCodeDefault;
            }
        }
        return ErrorCodeBuilder.of(code, defaultMsg);
    }

    @Override
    public boolean supports(final MethodParameter returnType,
                            final Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(final Object body, final MethodParameter returnType,
                                  final MediaType selectedContentType, final Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  final ServerHttpRequest request, final ServerHttpResponse response) {

        // Handle Problem Exception
        if (body instanceof Problem) {
            final Problem problem = (Problem) body;
            final String statusCode = String.valueOf(problem.getStatus().getStatusCode());
            return Response.buildFailure(convert(statusCode, problem.getTitle())).setReason(problem.getDetail());
        }

        // Handle REST Response headers
        if (body instanceof Response) {
            final Response result = (Response) body;
            Optional.ofNullable(result.getHeaders()).ifPresent(headers -> response.getHeaders().addAll(headers));
            return result;
        }

        return body;
    }

}
