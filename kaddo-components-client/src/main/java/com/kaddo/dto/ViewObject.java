package com.kaddo.dto;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

/**
 * VO (View Object) - 显示层对象，通常是 Web 向模板渲染引擎层传输的对象。
 *
 * @author larry.qi
 * @date 2022-07-02
 */
public class ViewObject extends DTO {
    private static final long serialVersionUID = 1L;
    protected Map<String, Object> params = Maps.newConcurrentMap();

    public Object getParams(String key) {
        return getParams().get(key);
    }

    public Object getParams(String key, Object defaultValue) {
        return getParams().getOrDefault(key, defaultValue);
    }

    public void setParam(String key, Object value) {
        this.params.put(key, value);
    }

    public Map<String, Object> getParams() {
        return Objects.isNull(params) ? Maps.newConcurrentMap() : params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}
