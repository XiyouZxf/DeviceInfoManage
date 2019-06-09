package zxf.dao;

import org.springframework.stereotype.Repository;
import zxf.model.Department;
import zxf.model.PageBean;
import zxf.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Zxf
 * @date 2019/4/26  21:51
 */
@Repository
public class DepartmentDao {


    public ResultSet departmentList(Connection con, PageBean pageBean, Department department) throws Exception {
        StringBuffer sb = new StringBuffer("select * from department ");
        if(department!=null && StringUtil.isNotEmpty(department.getDepart_identity())){
            sb.append(" and depart_identity like '%"+department.getDepart_identity()+"%'");

        }
        if(department!=null && StringUtil.isNotEmpty(department.getDepart_name())){
            sb.append(" and depart_name like '%"+department.getDepart_name()+"%'");
        }
//        if(department!=null && StringUtil.isNotEmpty(department.getDepart_name())){
//            sb.append(" where d.depart_name like '%"+department.getDepart_name()+"%'");
//            if(StringUtil.isNotEmpty(department.getDepart_identity())){
//                sb.append(" and d.depart_identity like '%"+department.getDepart_identity()+"%'");
//            }
//        }
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
        }

        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    public int departmentCount(Connection connection, Department department) throws Exception {
        StringBuilder sb = new StringBuilder("select count(*) as total from department d");
        if(StringUtil.isNotEmpty(department.getDepart_identity())){
            sb.append(" and d.depart_identity like '%"+department.getDepart_identity()+"%'");

        }
        if(StringUtil.isNotEmpty(department.getDepart_name())){
            sb.append(" and d.depart_name like '%"+department.getDepart_name()+"%'");
        }
//        if(StringUtil.isNotEmpty(department.getDepart_name())){
//            sb.append(" where d.depart_name like '%"+department.getDepart_name()+"%'");
//            if(StringUtil.isNotEmpty(department.getDepart_identity())){
//                sb.append(" and d.depart_identity like '%"+department.getDepart_identity()+"%'");
//            }
//        }
        PreparedStatement psmt = connection.prepareStatement(sb.toString().replaceFirst("and", "where"));
        ResultSet rs = psmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }

    }

//    public int departmentDelete(Connection connection,String delIds)throws Exception{
//        String departmentSql="delete from department where emp_id in ("+delIds+")";
//        PreparedStatement pstmt=connection.prepareStatement(departmentSql);
//        return pstmt.executeUpdate();
//    }
//
    public int departmentAdd(Connection connection,Department department)throws Exception{
        String sql="insert  into department values (null,?,?,?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,department.getDepart_identity());
        pstmt.setString(2,department.getDepart_name());
        pstmt.setInt(3,department.getDepart_includePeople());
        pstmt.setString(4,department.getDepart_tel_num());
        pstmt.setString(5,department.getDepart_status());
        pstmt.setString(6,department.getDepart_desc());
        return pstmt.executeUpdate();
    }

    public int departmentModify(Connection connection,Department department)throws Exception{
        String sql="update department set depart_identity=?,depart_name=?,depart_includePeople=?,depart_tel_num=?,depart_status=?,depart_desc=? where depart_id=?";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,department.getDepart_identity());
        pstmt.setString(2,department.getDepart_name());
        pstmt.setString(3,department.getDepart_includePeople()+"");
        pstmt.setString(4,department.getDepart_tel_num());
        pstmt.setString(5,department.getDepart_status());
        pstmt.setString(6,department.getDepart_desc());
        pstmt.setString(7,department.getDepart_id()+"");
        return pstmt.executeUpdate();
    }


}
