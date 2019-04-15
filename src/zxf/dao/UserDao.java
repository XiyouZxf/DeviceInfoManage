package zxf.dao;

import org.springframework.stereotype.Component;
import zxf.model.PageBean;
import zxf.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Component
public class UserDao {

	/**֤
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con,User user) throws Exception{
		User resultUser=null;
		String sql="select * from user where user_no=? and user_pass=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUser_no());
		pstmt.setString(2, user.getUser_pass());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setUser_no(rs.getString("user_no"));
			resultUser.setUser_pass(rs.getString("user_pass"));
		}
		return resultUser;
	}
	
	public ResultSet userList(Connection con,PageBean pageBean)throws Exception{
		System.out.println("aaa");
		StringBuffer sb=new StringBuffer("select * from user");
		/*if(grade!=null && StringUtil.isNotEmpty(grade.getGradeName())){
			sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");
		}*/
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public int userCount(Connection connection)throws Exception{
		String sql="select count(*) as total from user";
		PreparedStatement psmt=connection.prepareStatement(sql);
		ResultSet rs=psmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("total");
		}else {
			return 0;
		}
		
		
	}
}
