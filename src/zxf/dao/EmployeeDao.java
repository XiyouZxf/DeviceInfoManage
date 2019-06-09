package zxf.dao;

import org.springframework.stereotype.Repository;
import zxf.model.Employee;
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
public class EmployeeDao {

    public ResultSet employeeList(Connection con, PageBean pageBean, Employee employee) throws Exception {
        StringBuffer sb = new StringBuffer("select * from employee e");
        if(employee!=null && StringUtil.isNotEmpty(employee.getEmp_no())){
            sb.append(" and e.emp_no like '%"+employee.getEmp_no()+"%'");
        }
        if(employee!=null && StringUtil.isNotEmpty(employee.getEmp_name())){
            sb.append(" and e.emp_name like '%"+employee.getEmp_name()+"%'");
        }
        if(employee!=null && StringUtil.isNotEmpty(employee.getEmp_sex())){
            sb.append(" and e.emp_sex ='"+employee.getEmp_sex()+"'");
        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and","where"));
        return pstmt.executeQuery();
    }

    public int employeeCount(Connection connection, Employee employee) throws Exception {
        StringBuilder sb = new StringBuilder("select count(*) as total from employee e");
        if(employee!=null && StringUtil.isNotEmpty(employee.getEmp_no())){
            sb.append(" and e.emp_no like '%"+employee.getEmp_no()+"%'");
        }
        if(employee!=null && StringUtil.isNotEmpty(employee.getEmp_name())){
            sb.append(" and e.emp_name like '%"+employee.getEmp_name()+"%'");
        }
        if(employee!=null && StringUtil.isNotEmpty(employee.getEmp_sex())){
            sb.append(" and e.emp_sex ='"+employee.getEmp_sex()+"'");
        }

        PreparedStatement psmt = connection.prepareStatement(sb.toString().replaceFirst("and","where"));
        ResultSet rs = psmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }

    }

    public int employeeDelete(Connection connection,String delIds)throws Exception{
        String employeeSql="delete from employee where emp_id in ("+delIds+")";
        PreparedStatement pstmt=connection.prepareStatement(employeeSql);
        return pstmt.executeUpdate();
    }

    public int employeeAdd(Connection connection,Employee employee)throws Exception{
        String sql="insert  into employee values (null,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,employee.getEmp_no());
        pstmt.setString(2,employee.getEmp_name());
        pstmt.setString(3,"123456");
        pstmt.setString(4,employee.getEmp_sex());
        pstmt.setString(5,employee.getEmp_tel_num());
        pstmt.setString(6,employee.getEmp_addr());
        pstmt.setString(7,employee.getEmp_email());
        return pstmt.executeUpdate();
    }

    public int employeeModify(Connection connection,Employee employee)throws Exception{
        String sql="update employee set emp_no=?,emp_name=?,emp_sex=?,emp_tel_num=?,emp_addr=?,emp_email=? where emp_id=?";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,employee.getEmp_no());
        pstmt.setString(2,employee.getEmp_name());
        pstmt.setString(3,employee.getEmp_sex());
        pstmt.setString(4,employee.getEmp_tel_num());
        pstmt.setString(5,employee.getEmp_addr());
        pstmt.setString(6,employee.getEmp_email());
        pstmt.setString(7,employee.getEmp_id()+"");
        return pstmt.executeUpdate();
    }


}
