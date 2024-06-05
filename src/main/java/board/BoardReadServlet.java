package board;

import board.response.GetBoardRes;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.BaseResponse;
import config.BaseResponseMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/board/read")
public class BoardReadServlet extends HttpServlet {
    BoardDao dao;
    ObjectMapper mapper;

    @Override
    public void init() {
        dao = new BoardDao();
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idx = Integer.parseInt(req.getParameter("idx"));

        // ------------------- 회원 가입하는 Dao의 메소드 실행 -------------------
        GetBoardRes result = dao.find(idx);
        // ------------------- ------------------- -------------------


        // ------------------- Dao의 처리 결과에 따른 응답 설정 부분 -------------------
        String jsonResponse;
        if (result != null) {
            BaseResponse response = new BaseResponse(result);
            jsonResponse = mapper.writeValueAsString(response);
        } else {
            BaseResponse response = new BaseResponse(BaseResponseMessage.BOARD_GET_LIST_FAIL_EMPTY);
            jsonResponse = mapper.writeValueAsString(response);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResponse);
        // ------------------- ------------------- -------------------

    }
}
