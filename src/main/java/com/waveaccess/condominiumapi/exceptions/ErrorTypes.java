package com.waveaccess.condominiumapi.exceptions;

public interface ErrorTypes {

    /*
     * http-4xx
     */
    String BAD_REQUEST = "bad-request";
    String UNAUTHORIZED = "unauthorized";
    String FORBIDDEN = "forbidden";
    String NOT_FOUND = "not-found";
    String GONE = "gone";
    String TOO_MANY_REQUESTS = "too-many-requests";

    /*
     * http-5xx
     */
    String REQUEST_FAILED = "request-failed";
    String BAD_GATEWAY = "bad-gateway";
    String GATEWAY_TIMEOUT = "gateway-timeout";

    /*
     * validation
     */
    String VALIDATION_ERROR = "validation-error";
    String PARSE_ERROR = "parse-error";
    String MISSING_PARAMETERS = "missing-parameters";
    String MISSING_LANGUAGES = "missing-languages";
    String INVALID_STATE = "invalid-state";

    String ALREADY_EXIST = "already-exist";
    String ALREADY_USED = "already-used";
    String ALREADY_PAID = "already-paid";

    String NO_MATCH = "no-match";
    String NO_PERMISSIONS = "no-permissions";
    String NOT_AVAILABLE = "not-available";
    String NOT_ENOUGH = "not-enough";
    String NOT_PAID = "not-paid";

    String TOO_SIMPLE = "too-simple";
    String TOO_MANY_CHARACTERS = "too-many-characters";
    String TOO_MANY_ROWS = "too-many-rows";

    String NOT_UNIQUE_SORTING_TYPE = "not-unique-sorting-type";

    /*
     * auth
     */
    String BAD_CREDENTIALS = "bad-credentials";
    String ALREADY_REGISTERED = "already-registered";

    /*
     * users
     */
    String USER_NOT_FOUND = "user-not-found";
    String USER_NOT_REGISTERED = "user-not-registered";

    /*
     * files
     */
    String FILE_NOT_FOUND = "file-not-found";
    String INVALID_FILE_TYPE = "invalid-file-type";
    String INVALID_FILE_MODULE = "invalid-file-module";
    String INVALID_FILE_NAME = "invalid-file-name";
    String TOO_BIG_FILE_SIZE = "too-big-file-size";
    String TOO_MANY_FILES = "too-many-files";

    /*
     * detailed validation
     */
    String EMPTY_PARAM = "empty-param";
    String INVALID_PARAM = "invalid-param";
    String EXCESS_PARAM = "excess-param";
    String DUPLICATE = "duplicate";
    String RETRY_AFTER = "retry-after";
}

