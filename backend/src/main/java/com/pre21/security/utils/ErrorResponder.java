package com.pre21.security.utils;

import com.google.gson.Gson;
import com.pre21.response.ErrorResponse;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;

public class ErrorResponder {
    @SneakyThrows
    public static void sendErrorResponse(HttpServletResponse req, HttpStatus status) {
        Gson gson = new Gson();
        ErrorResponse errorResponse = ErrorResponse.of(status);
        req.setContentType(MediaType.APPLICATION_JSON_VALUE);
        req.setStatus(status.value());
        req.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }
}
