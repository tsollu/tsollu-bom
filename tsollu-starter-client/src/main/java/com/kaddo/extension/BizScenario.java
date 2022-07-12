package com.kaddo.extension;

import cn.hutool.core.util.StrUtil;

/**
 * BizScenario（业务场景）= bizId + useCase + scenario, which can uniquely identify a user scenario.
 *
 * @author larry.qi
 * @date 2022-07-02
 */
public class BizScenario {
    private final static String DEFAULT_BIZ_ID = "#defaultBizId#";
    private final static String DEFAULT_USE_CASE = "#defaultUseCase#";
    private final static String DEFAULT_SCENARIO = "#defaultScenario#";
    private final static String DOT_SEPARATOR = ".";

    /**
     * bizId is used to identify a business, such as "mall"
     */
    private String bizId = DEFAULT_BIZ_ID;

    /**
     * useCase is used to identify a use case, such as "placeOrder"
     */
    private String useCase = DEFAULT_USE_CASE;

    /**
     * scenario is used to identify a use case, such as "88vip","normal"
     */
    private String scenario = DEFAULT_SCENARIO;

    public static BizScenario of(String bizId, String useCase, String scenario) {
        BizScenario bizScenario = new BizScenario();
        bizScenario.bizId = StrUtil.blankToDefault(bizId, DEFAULT_BIZ_ID);
        bizScenario.useCase = StrUtil.blankToDefault(useCase, DEFAULT_USE_CASE);
        bizScenario.scenario = StrUtil.blankToDefault(scenario, DEFAULT_SCENARIO);
        return bizScenario;
    }

    public static BizScenario of(String bizId, String useCase) {
        return BizScenario.of(bizId, useCase, DEFAULT_SCENARIO);
    }

    public static BizScenario of(String bizId) {
        return BizScenario.of(bizId, DEFAULT_USE_CASE, DEFAULT_SCENARIO);
    }

    /**
     * For above case, the BizScenario will be "mall.placeOrder.88vip", with this code, we can
     * provide extension processing other than "mall.placeOrder.normal" scenario.
     */
    public String getBizScenario() {
        return bizId + DOT_SEPARATOR + useCase + DOT_SEPARATOR + scenario;
    }

}
