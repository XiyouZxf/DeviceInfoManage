package zxf.web;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import zxf.dao.UserDao;
import zxf.model.PageBean;
import zxf.util.DbUtil;
import zxf.util.JsonUtil;
import zxf.util.ResponseUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
/**
 * @author Zxf
 * @date 2019/4/20  14:28
 */
@Controller
public class UserListController {
    @Autowired PageBean pageBean;
    @Autowired DbUtil   dbUtil;
    @Autowired UserDao  userDao;
    @RequestMapping("/userList")
    public void userList(HttpServletRequest request, HttpServletResponse response)throws Exception{

        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        pageBean.setPage(Integer.parseInt(page));
        pageBean.setRows(Integer.parseInt(rows));
        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONObject result = new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(userDao.userList(conn,pageBean));
            int total=userDao.userCount(conn);
            result.put("rows",jsonArray);
            result.put("total",total);
            ResponseUtil.write(response,result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeCon(conn);

        }




    }
}
