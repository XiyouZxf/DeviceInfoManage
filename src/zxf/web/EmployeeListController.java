package zxf.web;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import zxf.dao.EmployeeDao;
import zxf.model.PageBean;
import zxf.model.Employee;
import zxf.util.DbUtil;
import zxf.util.JsonUtil;
import zxf.util.ResponseUtil;
import zxf.util.StringUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

/**
 * @author Zxf
 * @date 2019/4/20  14:28
 */
@Controller
public class EmployeeListController {
    Employee  employee;
    PageBean pageBean;
    DbUtil dbUtil=new DbUtil();
    @Autowired EmployeeDao  employeeDao;
    @RequestMapping("/employeeList")
    public void employeeList(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        String emp_no=request.getParameter("no");
        String emp_name=request.getParameter("name");
        String emp_sex=request.getParameter("sex");
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        employee=new Employee();

        if (emp_no!=null){
            employee.setEmp_no(emp_no);
            employee.setEmp_name(emp_name);
            employee.setEmp_sex(emp_sex);
        }
        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONObject result = new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(employeeDao.employeeList(conn,pageBean,employee));
            int total=employeeDao.employeeCount(conn,employee);
            result.put("rows",jsonArray);
            result.put("total",total);
            ResponseUtil.write(response,result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeCon(conn);
        }
    }
    @RequestMapping("/employeeDelete")
    public void employeeDelete(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String delIds=request.getParameter("delIds");
        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONObject result = new JSONObject();
            int delNums=employeeDao.employeeDelete(conn,delIds);
            if (delNums>0){
                result.put("success","true");
                result.put("delNums",delNums);
            }else {
                result.put("errorMag","删除失败");
            }
            ResponseUtil.write(response,result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeCon(conn);
        }
    }
    @RequestMapping("/employeeSave")
    public void employeeSave(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        String id=request.getParameter("emp_id");
        String employeeName=request.getParameter("emp_name");
        String employeeNo=request.getParameter("emp_no");
        String telNumber=request.getParameter("emp_tel_num");
        String employeeSex=request.getParameter("emp_sex");
        String employeeAddr=request.getParameter("emp_addr");
        String employeeEmail=request.getParameter("emp_email");
        employee=new Employee();
        employee.setEmp_no(employeeNo);
        employee.setEmp_name(employeeName);
        employee.setEmp_sex(employeeSex);
        employee.setEmp_addr(employeeAddr);
        employee.setEmp_tel_num(telNumber);
        employee.setEmp_email(employeeEmail);
        if(StringUtil.isNotEmpty(id)){
            employee.setEmp_id(Integer.parseInt(id));
        }
        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONObject result = new JSONObject();
            int saveNums=0;
            if(StringUtil.isNotEmpty(id)){
                saveNums=employeeDao.employeeModify(conn,employee);
            }else {
                saveNums=employeeDao.employeeAdd(conn,employee);
            }
            if (saveNums>0){
                result.put("success","true");

            }else {
                result.put("success","true");
                result.put("errorMsg","删除失败");
            }
            ResponseUtil.write(response,result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeCon(conn);
        }
    }

}
