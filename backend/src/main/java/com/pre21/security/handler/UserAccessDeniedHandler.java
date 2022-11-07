package com.pre21.security.handler;

import com.pre21.security.utils.ErrorResponder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 인가되지 않은 사용자가 액세스 할 수 없는 요청을 할 시 동작하는 클래스
 * @author mozzi327
 */
@Slf4j
public class UserAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req,
                       HttpServletResponse res,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorResponder.sendErrorResponse(res, HttpStatus.FORBIDDEN);
        log.warn("Forbidden error happened: {}", accessDeniedException);
    }
}
