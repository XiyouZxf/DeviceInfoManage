package zxf.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import zxf.dao.ExamineScheduleDao;
import zxf.model.ExamineSchedule;
import zxf.model.PageBean;
import zxf.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

/**
 * @author Zxf
 * @date 2019/4/20  14:28
 */
@Controller
public class ExamineScheduleListController {
    ExamineSchedule  examineSchedule;
    PageBean pageBean;
    DbUtil dbUtil=new DbUtil();
    @Autowired ExamineScheduleDao  examineScheduleDao;
    @RequestMapping("/examineScheduleList")
    public void examineScheduleList(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        String dev_identity=request.getParameter("d_dev_identity");
        String dev_name=request.getParameter("d_dev_name");
        String dev_status=request.getParameter("d_dev_status");
        String s_dev_buytime=request.getParameter("d_sdev_buytime");
        String e_dev_buytime=request.getParameter("d_edev_buytime");
        String s_depart_id=request.getParameter("d_depart_id");
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        examineSchedule=new ExamineSchedule();

        if (dev_identity!=null){
            examineSchedule.setDev_identity(dev_identity);
            examineSchedule.setDev_name(dev_name);
            examineSchedule.setExam_status(dev_status);
            if(StringUtil.isNotEmpty(s_depart_id)){
                examineSchedule.setDepart_id(Integer.parseInt(s_depart_id));
            }
        }
        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONObject result = new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(examineScheduleDao.examineScheduleList(conn,pageBean,examineSchedule,s_dev_buytime,e_dev_buytime));
            int total=examineScheduleDao.examineScheduleCount(conn,examineSchedule,s_dev_buytime,e_dev_buytime);
            result.put("rows",jsonArray);
            result.put("total",total);
            ResponseUtil.write(response,result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeCon(conn);
        }
    }
    @RequestMapping("/examineScheduleDelete")
    public void examineScheduleDelete(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String delIds=request.getParameter("delIds");
        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONObject result = new JSONObject();
            int delNums=examineScheduleDao.examineScheduleDelete(conn,delIds);
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
    @RequestMapping("/examineScheduleSave")
    public void examineScheduleSave(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        String exam_id=request.getParameter("exam_id");

        String exam_status=request.getParameter("exam_status");
        String dev_identity=request.getParameter("dev_identity");
        String exam_way=request.getParameter("exam_way");
        String exam_period=request.getParameter("exam_period");
        String exam_desc=request.getParameter("exam_desc");
        examineSchedule=new ExamineSchedule();
        examineSchedule.setExam_status(exam_status);
        examineSchedule.setDev_identity(dev_identity);
        examineSchedule.setExam_way(exam_way);
        examineSchedule.setExam_period(exam_period);
        examineSchedule.setExam_desc(exam_desc);
        if(StringUtil.isNotEmpty(exam_id)){
            examineSchedule.setExam_id(Integer.parseInt(exam_id));
        }
        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONObject result = new JSONObject();
            int saveNums=0;
            if(StringUtil.isNotEmpty(exam_id)){
                saveNums=examineScheduleDao.examineScheduleModify(conn,examineSchedule);
            }else {
                saveNums=examineScheduleDao.examineScheduleAdd(conn,examineSchedule);
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
