package zxf.web;

import org.springframework.stereotype.Controller;
import zxf.dao.UserDao;
import zxf.model.PageBean;
import zxf.util.DbUtil;
import zxf.util.JsonUtil;
import zxf.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
@Controller
public class UserListServlet extends HttpServlet {

	DbUtil  dbUtil= new DbUtil();
	UserDao userDao= new UserDao();
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}
	@Override

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection connection=null;
		try {
			
			connection=dbUtil.getCon();
			
			JSONObject result = new JSONObject();
			System.out.println("aaa"+connection);
			 ResultSet set=userDao.userList(connection, pageBean);
			 System.out.println("bbb"+set);
			 
			 while (set.next()) {
				String a=set.getString("id");
				System.out.println(a);
				
			}
			JSONArray jsonArray= JsonUtil.formatRsToJsonArray(userDao.userList(connection, pageBean));
			int total=userDao.userCount(connection);
			result.put("rows",jsonArray);
			result.put("total",total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
