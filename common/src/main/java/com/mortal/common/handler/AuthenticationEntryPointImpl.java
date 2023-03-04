package com.mortal.common.handler;

import com.alibaba.fastjson.JSON;
import com.mortal.common.utils.R;
import com.mortal.common.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.stream.Stream;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        R result = R.failed(HttpStatus.UNAUTHORIZED.value(),"用户认证失败,查詢登錄");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }
}
