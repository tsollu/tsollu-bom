package com.tsollu.task;

import com.alibaba.fastjson.JSON;
import com.tsollu.dto2.Response;
import com.tsollu.dto2.SingleResponse;
import com.tsollu.exception.ErrorCodeDefault;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync
@EnableScheduling
@Import(KaddoExecutorService.class)
public class KaddoTaskAutoConfiguration {

    public static void main(String[] args) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("A-header01", "A-header01");
        System.out.println(JSON.toJSON(Response.of()));
        System.out.println(JSON.toJSONString(Response.of(ErrorCodeDefault.A0111)));
        System.out.println(JSON.toJSONString(Response.of(ErrorCodeDefault.A0111).rewrite("用户名已存在，请重新填写").setHeaders(headers)));

        System.out.println(JSON.toJSON(SingleResponse.of()));
        System.out.println(JSON.toJSON(SingleResponse.of(headers)));

    }

}
