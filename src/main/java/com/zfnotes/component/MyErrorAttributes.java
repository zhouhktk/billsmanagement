package com.zfnotes.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 自定义错误数据响应类
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    /**
     * 自定义错误响应数据
     * @param webRequest
     * @param includeStackTrace
     * @return
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        //自定义错误响应数据
        map.put("company", "自定义数据进行响应");
        return map;
    }
}
