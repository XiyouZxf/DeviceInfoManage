package zxf.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import zxf.dao.DepartmentDao;
import zxf.model.Department;
import zxf.model.PageBean;
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
public class DepartmentListController {
    Department  department;
    PageBean pageBean;
    DbUtil dbUtil=new DbUtil();
    @Autowired DepartmentDao  departmentDao;
    @RequestMapping("/departmentList")
    public void departmentList(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        String depart_identity=request.getParameter("identity");
        String depart_name=request.getParameter("name");

        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));

        if (depart_identity==null){
            depart_identity="";
        }
        if (depart_name==null){
            depart_name="";
        }
        department=new Department();
        department.setDepart_identity(depart_identity);
        department.setDepart_name(depart_name);

        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONObject result = new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(departmentDao.departmentList(conn,pageBean,department));
            int total=departmentDao.departmentCount(conn,department);
            result.put("rows",jsonArray);
            result.put("total",total);
            ResponseUtil.write(response,result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeCon(conn);
        }
    }
    @RequestMapping("/departmentComboList")
    public void departmentComboList(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONArray jsonArray=new JSONArray();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("depart_id", "-2");
            jsonObject.put("depart_name", "请选择所在部门");
            jsonArray.add(jsonObject);
            jsonArray.addAll(JsonUtil.formatRsToJsonArray(departmentDao.departmentList(conn, null,null)));
            ResponseUtil.write(response,jsonArray);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeCon(conn);
        }
    }
//    @RequestMapping("/departmentDelete")
//    public void departmentDelete(HttpServletRequest request, HttpServletResponse response)throws Exception{
//        String delIds=request.getParameter("delIds");
//        Connection conn=null;
//        try {
//            conn=dbUtil.getCon();
//            JSONObject result = new JSONObject();
//            int delNums=departmentDao.departmentDelete(conn,delIds);
//            if (delNums>0){
//                result.put("success","true");
//                result.put("delNums",delNums);
//            }else {
//                result.put("errorMag","删除失败");
//            }
//            ResponseUtil.write(response,result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            dbUtil.closeCon(conn);
//        }
//    }
    @RequestMapping("/departmentSave")
    public void departmentSave(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        String id=request.getParameter("depart_id");
        String depart_identity=request.getParameter("depart_identity");
        String depart_name=request.getParameter("depart_name");
        String depart_includePeople=request.getParameter("depart_includePeople");
        String depart_tel_num=request.getParameter("depart_tel_num");
        String depart_status=request.getParameter("depart_status");
        String depart_desc=request.getParameter("depart_desc");
        department=new Department();
        department.setDepart_identity(depart_identity);
        department.setDepart_name(depart_name);
        department.setDepart_includePeople(Integer.parseInt(depart_includePeople));
        department.setDepart_tel_num(depart_tel_num);
        department.setDepart_status(depart_status);
        department.setDepart_desc(depart_desc);
        if(StringUtil.isNotEmpty(id)){
            department.setDepart_id(Integer.parseInt(id));
        }
        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONObject result = new JSONObject();
            int saveNums=0;
            if(StringUtil.isNotEmpty(id)){
                saveNums=departmentDao.departmentModify(conn,department);
            }else {
                saveNums=departmentDao.departmentAdd(conn,department);
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
