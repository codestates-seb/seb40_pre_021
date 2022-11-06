package com.pre21.exception;

import lombok.Getter;

public enum ExceptionCode {
    USER_NOT_FOUND(504, "User not found"),
    COMMENT_NOT_FOUND(504, "Comment not found"),
    QUESTION_NOT_FOUND(504, "Question not found"),
    TOKEN_NOT_FOUND(504, "Token not found"),
    COOKIE_NOT_FOUND(504, "Cookie not found"),
    ANSWER_NOT_FOUND(504, "Answer not found"),
    UNAUTHORIZED_USER(401, "Unauthorized user"),
    ALREADY_ADOPTED(506, "Already adopted"),

    USER_EXISTS(506, "User already exists"),


    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_USER_STATUS(600, "Invalid member status");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
