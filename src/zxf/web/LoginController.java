package zxf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import zxf.dao.UserDao;
import zxf.model.User;
import zxf.util.DbUtil;
import zxf.util.StringUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
/**
 * @author Zxf
 * @date 2019/4/10  15:47
 */
@Controller
public class LoginController {

    @Autowired DbUtil dbUtil;
    @Autowired UserDao userDao;
    @RequestMapping("/login")
    public void login(HttpServletRequest req, HttpServletResponse resp) throws Exception{

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            req.setAttribute("error", "用户名或密码为空！");
            req.getRequestDispatcher("L.jsp").forward(req, resp);
        }

        User user = new User(username,password);
        Connection conn=null;
        try {
            conn =dbUtil.getCon();
            User currentUser =userDao.login(conn, user);
            if (currentUser==null) {
                req.setAttribute("error", "用户名或密码错误！");
                req.getRequestDispatcher("L.jsp").forward(req, resp);
            }else {
                HttpSession session=req.getSession();
                session.setAttribute("currentUser", currentUser);
                resp.sendRedirect("main.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(conn);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
