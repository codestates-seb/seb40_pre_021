package com.pre21.exception;

import lombok.Getter;

public enum ExceptionCode {
    USER_NOT_FOUND(404, "User not found"),
    COMMENT_NOT_FOUND(404, "Comment not found"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    TOKEN_NOT_FOUND(404, "Token not found"),
    COOKIE_NOT_FOUND(404, "Cookie not found"),
    ANSWER_NOT_FOUND(404, "Answer not found"),

    USER_EXISTS(409, "User already exists"),


    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_USER_STATUS(400, "Invalid member status");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
