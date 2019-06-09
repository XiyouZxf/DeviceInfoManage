package zxf.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import zxf.dao.ReportDao;
import zxf.dao.ReportDao;
import zxf.model.Report;
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
public class ReportListController {
    Report  report;
    PageBean pageBean;
    DbUtil dbUtil=new DbUtil();
    @Autowired
    ReportDao reportDao;
    @RequestMapping("/reportList")
    public void reportList(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        String r_report_no=request.getParameter("r_report_no");
        String r_report_serialNumber=request.getParameter("r_report_serialNumber");
        String r_report_devName=request.getParameter("r_report_devName");
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        report=new Report();
        if (r_report_no!=null){
            report.setReport_no(r_report_no);
            report.setReport_serialNumber(r_report_serialNumber);
            report.setDev_name(r_report_devName);
        }
        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONObject result = new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(reportDao.reportList(conn,pageBean,report));
            int total=reportDao.reportCount(conn,report);
            result.put("rows",jsonArray);
            result.put("total",total);
            ResponseUtil.write(response,result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeCon(conn);
        }
    }
//    @RequestMapping("/reportDelete")
//    public void reportDelete(HttpServletRequest request, HttpServletResponse response)throws Exception{
//        String delIds=request.getParameter("delIds");
//        Connection conn=null;
//        try {
//            conn=dbUtil.getCon();
//            JSONObject result = new JSONObject();
//            int delNums=reportDao.reportDelete(conn,delIds);
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
    @RequestMapping("/reportSave")
    public void reportSave(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        String report_id=request.getParameter("report_id");
        String report_name=request.getParameter("report_name");
        String report_no=request.getParameter("report_no");

        String dev_identity=request.getParameter("dev_identity");
        String report_serialNumber=request.getParameter("report_serialNumber");
        String report_identifyDepart=request.getParameter("report_identifyDepart");
        String report_identifier=request.getParameter("report_identifier");
        String report_identifyTime=request.getParameter("report_identifyTime");
//        String dev_id=request.getParameter("dev_id");
        report=new Report();
        report.setReport_no(report_no);
        report.setReport_name(report_name);
        report.setReport_serialNumber(report_serialNumber);
        report.setReport_identifyDepart(report_identifyDepart);
        report.setReport_identifier(report_identifier);
        report.setReport_identifyTime(DateUtil.formatString(report_identifyTime,"yyyy-MM-dd"));
        report.setDev_id(dev_identity);

        if(StringUtil.isNotEmpty(report_id)){
            report.setReport_id(Integer.parseInt(report_id));
        }
        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONObject result = new JSONObject();
            int saveNums=0;
            if(StringUtil.isNotEmpty(report_id)){
                saveNums=reportDao.reportModify(conn,report);
            }else {
                saveNums=reportDao.reportAdd(conn,report);
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
