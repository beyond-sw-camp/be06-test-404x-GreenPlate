package user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.BaseResponse;
import config.BaseResponseMessage;
import user.request.PostUserEditReq;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/user/edit")
public class UserEditServlet extends HttpServlet {
    UserDao dao;
    ObjectMapper mapper;

    public void init() {
        dao = new UserDao();
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        PostUserEditReq dto = mapper.readValue(json.toString(), PostUserEditReq.class);
        Boolean result = dao.edit(dto);

        BaseResponse resp;
        if (result) {
            resp = new BaseResponse(BaseResponseMessage.USER_EDIT_SUCCESS);
        } else {
            resp = new BaseResponse(BaseResponseMessage.USER_EDIT_FAIL);
        }
        String jsonResp = mapper.writeValueAsString(resp);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResp);
    }
}