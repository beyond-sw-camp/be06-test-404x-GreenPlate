package User;

import User.request.PostUserLoginReq;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.BaseResponse;
import config.BaseResponseMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
    UserDao dao;
    ObjectMapper mapper;

    public void init(){
        dao = new UserDao();
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       BufferedReader reader = req.getReader();
       StringBuilder json = new StringBuilder();
       String line;
       while ((line = reader.readLine()) != null) {
           json.append(line);
       }

       PostUserLoginReq dto = mapper.readValue(json.toString(), PostUserLoginReq.class);

       // 로그인 DAO의 메소드 실행
       Boolean result = dao.login(dto);


       // Dao의 처리 결과에 따른 응답 설정 부분
       String jsonResponse;
       BaseResponse response;
       if (result) {
           response = new BaseResponse(BaseResponseMessage.USER_LOGIN_SUCCESS);
           jsonResponse = mapper.writeValueAsString(response);
       } else {
           response = new BaseResponse(BaseResponseMessage.USER_LOGIN_FAIL_EMPTY_PASSWORD);
           jsonResponse = mapper.writeValueAsString(response); }

       resp.setContentType("application/json");
       resp.setCharacterEncoding("UTF-8");
       resp.getWriter().write(jsonResponse);
   }
}
