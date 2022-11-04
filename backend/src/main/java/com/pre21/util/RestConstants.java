package com.pre21.util;

public class RestConstants {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    public static final String QUESTION_URL = "/question";
    public static final String USER_URL = "/user";

    public static final String ASK_QUESTION_URL = QUESTION_URL + "/ask";

    public static final String JWT_SIGN_UP_URL = USER_URL + "/signup";
    public static final String JWT_LOGIN_URL = USER_URL + "/login";
    public static final String JWT_LOGUT_URL = USER_URL + "/logout";


    public static final String SECURED_API_URL = "/api/v1/**";

    public static final String BOOKMARK_BASIC_PAGE = "";
}
