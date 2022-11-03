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
