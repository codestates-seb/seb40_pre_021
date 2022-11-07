package com.pre21.security.handler;

import com.pre21.security.utils.ErrorResponder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 인증되지 않은 사용자가 요청을 했을 때 동작하는 핸들러 클래스
 * @author mozzi327
 */
@Slf4j
@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest req,
                         HttpServletResponse res,
                         AuthenticationException authException) throws IOException, ServletException {
//        Exception exception = (Exception) req.getAttribute("exception");
//        ErrorResponder.sendErrorResponse(res, HttpStatus.UNAUTHORIZED);
//        log.warn("Unauthorized error happened: {}", exception);
    }
}
