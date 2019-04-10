package zxf.web;

import zxf.dao.UserDao;
import zxf.model.User;
import zxf.util.DbUtil;
import zxf.util.StringUtil;
import zxf.util.DbUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class LoginServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil = new DbUtil();
	UserDao userDao =  new UserDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		req.setAttribute("username", username);
		req.setAttribute("password", password);
		if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
			req.setAttribute("error", "用户名或密码为空！");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
		User user = new User(username,password);
		Connection conn=null;
		try {
			conn =dbUtil.getCon();
			User currentUser =userDao.login(conn, user);
			if (currentUser==null) {
				req.setAttribute("error", "用户名或密码错误！");
				req.getRequestDispatcher("login.jsp").forward(req, resp);    //服务器跳转
			}else {
				HttpSession session=req.getSession();
				session.setAttribute("currentUser", currentUser);           
				resp.sendRedirect("main.jsp");                             //客户端跳转
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
