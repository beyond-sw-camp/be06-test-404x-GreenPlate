package config;

public enum BaseResponseMessage {
    // 요청 성공 1000
    REQUEST_SUCCESS(true, 1000, "요청이 정상적으로 처리되었습니다"),
    
    // 회원 기능 2000
    MEMBER_REGISTER_SUCCESS(true, 2000, "정상적으로 가입되었습니다"),
    MEMBER_REGISTER_FAIL_PASSWORD_EMPTY(false, 2101, "패스워드를 입력해주세요"),
    MEMBER_REGISTER_FAIL_PASSWORD_COMPLEXITY(false, 2102, "복잡한 패스워드를 사용해주세요"),

    // 게신판 기능 3000
    BOARD_GET_LIST_SUCCESS(true, 3000, "게시글 목록 조회 성공"),
    BOARD_GET_LIST_FAIL_EMPTY(false, 3001, "조회된 게시글이 없습니다.")
    ;
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
