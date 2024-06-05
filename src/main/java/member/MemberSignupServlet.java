package member;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.BaseResponse;
import config.BaseResponseMessage;
import member.request.PostMemberReq;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/member/signup")
public class MemberSignupServlet extends HttpServlet {
    MemberDao dao;
    ObjectMapper mapper;

    @Override
    public void init() {
        dao = new MemberDao();
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // ------------------- 클라이언트로부터 요청을 받아서 Dto에 저장하는 부분 -------------------
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        PostMemberReq dto = mapper.readValue(json.toString(), PostMemberReq.class);
        // ------------------- ------------------- -------------------


        // ------------------- 회원 가입하는 Dao의 메소드 실행 -------------------
        Boolean result = dao.create(dto);
//        Boolean result = false;
        // ------------------- ------------------- -------------------


        // ------------------- Dao의 처리 결과에 따른 응답 설정 부분 -------------------
        String jsonResponse;
        if (result) {
            BaseResponse response = new BaseResponse(BaseResponseMessage.MEMBER_REGISTER_SUCCESS);
            jsonResponse = mapper.writeValueAsString(response);
        } else {
            BaseResponse response = new BaseResponse(BaseResponseMessage.MEMBER_REGISTER_FAIL_PASSWORD_EMPTY);
            jsonResponse = mapper.writeValueAsString(response);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResponse);
        // ------------------- ------------------- -------------------

    }
}
