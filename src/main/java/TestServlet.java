import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Human> humanList;
        humanList = new ArrayList<>();

        // 팀원들 추가
        Human h01;
        h01 = new Human("test01", 10);
        humanList.add(h01);

        // 팀원마다 가진 과자들을 추가
        Snack snack01;
        snack01 = new Snack("과자01", 1000, "복숭아");
        h01.snackList.add(snack01);


        // 팀원들 추가
        Human h02;
        h02 = new Human("test02", 20);
        humanList.add(h02);

        // 팀원마다 가진 과자들을 추가
        Snack snack02;
        snack02 = new Snack("과자02", 2000, "딸기");
        Snack snack03;
        snack03 = new Snack("과자03", 2000, "사과");
        h02.snackList.add(snack02);
        h02.snackList.add(snack03);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(humanList);


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(json);


    }
}
