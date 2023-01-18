package com.example.Puzzlogging.common.exception.type;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 400 Bad Request
    INVALID("BR000", "잘못된 요청입니다"),
    INVALID_TYPE("BR001", "잘못된 타입이 입력되었습니다"),

    INVALID_MISSING_PARAMETER("BR100", "필수 파라미터가 입력되지 않았습니다"),
    INVALID_UPLOAD_FILE_SIZE("BR208", "업로드 가능한 파일 크기를 초과했습니다"),

    // 401 UnAuthorized
    FAIL_TO_LOGIN("UA001", "없는 아이디거나 비밀번호가 틀렸습니다."),


    // 403 Forbidden
    FORBIDDEN( "FB000", "허용하지 않는 요청입니다"),


    // 404 Not Found
    NOTFOUND("NF000", "존재하지 않습니다"),
    NOTFOUND_USER( "NF001", "탈퇴하거나 존재하지 않는 유저입니다"),


    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED( "MN000", "Not Allowed Method"),


    // 406 Not Acceptable
    NOT_ACCEPTABLE( "NA000", "Not Acceptable"),


    // 409 Conflict
    CONFLICT( "CF000", "이미 존재합니다"),


    // 415 Unsupported Media Type
    UNSUPPORTED_MEDIA_TYPE("UM000", "Unsupported Media Type"),


    // 500 Internal Server Exception
    INTERNAL_SERVER("IS000", "예상치 못한 에러가 발생하였습니다.\n잠시 후 다시 시도해주세요!"),


    // 502 Bad Gateway
    BAD_GATEWAY("BG000", "일시적인 에러가 발생하였습니다.\n잠시 후 다시 시도해주세요!"),


    // 503 Service UnAvailable
    SERVICE_UNAVAILABLE("SU000", "현재 해당 기능은 점검 중입니다.\n잠시 후 다시 시도해주세요!"),

    // 511 모자이크 패턴 사진 생성 실패
    FAIL_TO_GENERATE("FG000", "모자이크 패턴 사진 생성 실패"),
    // 512 모자이크 패턴 사진 생성 중
    WAIT_TO_GENERATE("FG000", "모자이크 패턴 사진 생성 중입니다.\n잠시 후 다시 시도해주세요!"),
    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
