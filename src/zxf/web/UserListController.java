package zxf.web;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import zxf.model.PageBean;
import zxf.model.User;
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
public class UserListController {
//    @Autowired User  user;
//    @Autowired PageBean pageBean;
//    @Autowired DbUtil   dbUtil;
//    @Autowired UserDao  userDao;
//    @RequestMapping("/userList")
//    public void userList(HttpServletRequest request, HttpServletResponse response)throws Exception{
//        String page=request.getParameter("page");
//        String rows=request.getParameter("rows");
//        String userName=request.getParameter("userName");
//        if(userName==null){
//            userName="";
//        }
//        user.setUser_name(userName);
//        pageBean.setPage(Integer.parseInt(page));
//        pageBean.setRows(Integer.parseInt(rows));
//        Connection conn=null;
//        try {
//            conn=dbUtil.getCon();
//            JSONObject result = new JSONObject();
//            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(userDao.userList(conn,pageBean,user));
//            int total=userDao.userCount(conn,user);
//            result.put("rows",jsonArray);
//            result.put("total",total);
//            ResponseUtil.write(response,result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            dbUtil.closeCon(conn);
//        }
//    }
//    @RequestMapping("/userDelete")
//    public void userDelete(HttpServletRequest request, HttpServletResponse response)throws Exception{
//        String delIds=request.getParameter("delIds");
//        Connection conn=null;
//        try {
//            conn=dbUtil.getCon();
//            JSONObject result = new JSONObject();
//            int delNums=userDao.userDelete(conn,delIds);
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
//    @RequestMapping("/userSave")
//    public void userSave(HttpServletRequest request, HttpServletResponse response)throws Exception{
//        request.setCharacterEncoding("utf-8");
//        String id=request.getParameter("id");
//        String userName=request.getParameter("userName");
//        String userNo=request.getParameter("userNo");
//        String telNumber=request.getParameter("telNumber");
//        String userPass=request.getParameter("userPass");
//        String userAddr=request.getParameter("userAddr");
//        String userEmail=request.getParameter("userEmail");
//        user.setUser_no(userNo);
//        user.setUser_name(userName);
//        user.setUser_pass(userPass);
//        user.setUser_addr(userAddr);
//        user.setUser_tel_num(telNumber);
//        user.setUser_email(userEmail);
//        if(StringUtil.isNotEmpty(id)){
//            user.setId(Integer.parseInt(id));
//        }
//        Connection conn=null;
//        try {
//            conn=dbUtil.getCon();
//            JSONObject result = new JSONObject();
//            int saveNums=0;
//            if(StringUtil.isNotEmpty(id)){
//                saveNums=userDao.userModify(conn,user);
//            }else {
//                saveNums=userDao.userAdd(conn,user);
//            }
//            if (saveNums>0){
//                result.put("success","true");
//
//            }else {
//                result.put("success","true");
//                result.put("errorMsg","删除失败");
//            }
//            ResponseUtil.write(response,result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            dbUtil.closeCon(conn);
//        }
//    }

}
