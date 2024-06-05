package config;

public enum BaseResponseMessage {

    REQUEST_SUCCESS(true, 1000, "요청이 정상적으로 처리되었습니다"),
    

    MEMBER_REGISTER_SUCCESS(true, 2000, "정상적으로 가입되었습니다"),
    MEMBER_REGISTER_FAIL_PASSWORD_EMPTY(false, 2101, "패스워드를 입력해주세요"),
    MEMBER_REGISTER_FAIL_PASSWORD_COMPLEXITY(false, 2102, "복잡한 패스워드를 사용해주세요"),


    BOARD_GET_LIST_SUCCESS(true, 3000, "게시글 목록 조회 성공"),
    BOARD_GET_LIST_FAIL_EMPTY(false, 3001, "조회된 게시글이 없습니다."),


    USER_LOGIN_SUCCESS(true, 1100, "로그인에 성공했습니다."),
    USER_LOGIN_FAIL_EMPTY(false,1101,"입력하신 정보와 일치하는 정보가 없습니다."),
    USER_LOGIN_FAIL_PASSWORD(false,1102,"비밀번호가 일치하지 않습니다."),
    USER_LOGIN_FAIL_EMPTY_EMAIL(false,1103,"이메일을 입력해주세요"),
    USER_LOGIN_FAIL_EMPTY_PASSWORD(false, 1104, "비밀번호를 입력해주세요.");


    private Boolean success;
    private Integer code;
    private String message;

    BaseResponseMessage(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
