package zxf.dao;
import org.springframework.stereotype.Repository;
import zxf.model.PageBean;
import zxf.model.User;
import zxf.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class UserDao {

    public User login(Connection con, User user) throws Exception {
        User resultUser = null;
        String sql = "select * from user where user_no=? and user_pass=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getUser_no());
        pstmt.setString(2, user.getUser_pass());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            resultUser = new User();
            resultUser.setUser_no(rs.getString("user_no"));
            resultUser.setUser_pass(rs.getString("user_pass"));
        }
        return resultUser;
    }

    public ResultSet userList(Connection con,PageBean pageBean,User user) throws Exception {
        StringBuffer sb = new StringBuffer("select id,user_no,user_name,user_tel_num,user_addr,user_email" +
                " from user");
		if(user!=null && StringUtil.isNotEmpty(user.getUser_name())){
			sb.append(" and user_name like '%"+user.getUser_name()+"%'");
		}
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and","where"));
        return pstmt.executeQuery();
    }

    public int userCount(Connection connection,User user) throws Exception {
        StringBuilder sb = new StringBuilder("select count(*) as total from user");
        if(user!=null && StringUtil.isNotEmpty(user.getUser_name())){
            sb.append(" and user_name like '%"+user.getUser_name()+"%'");
        }
        PreparedStatement psmt = connection.prepareStatement(sb.toString().replaceFirst("and","where"));
        ResultSet rs = psmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }

    }

    public int userDelete(Connection connection,String delIds)throws Exception{
        String sql="delete from user where id in ("+delIds+")";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    public int userAdd(Connection connection,User user)throws Exception{
        String sql="insert  into user values (null,?,?,?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,user.getUser_no());
        pstmt.setString(2,user.getUser_name());
        pstmt.setString(3,user.getUser_tel_num());
        pstmt.setString(4,user.getUser_pass());
        pstmt.setString(5,user.getUser_addr());
        pstmt.setString(6,user.getUser_email());
        return pstmt.executeUpdate();
    }

    public int userModify(Connection connection,User user)throws Exception{
        String sql="update user set user_no=?,user_name=?,user_tel_num=?,user_pass=?,user_addr=?,user_email=? where id=?";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,user.getUser_no());
        pstmt.setString(2,user.getUser_name());
        pstmt.setString(3,user.getUser_tel_num());
        pstmt.setString(4,user.getUser_pass());
        pstmt.setString(5,user.getUser_addr());
        pstmt.setString(6,user.getUser_email());
        pstmt.setString(7,user.getId()+"");

        return pstmt.executeUpdate();
    }
}
