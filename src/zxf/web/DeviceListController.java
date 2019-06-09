package zxf.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import zxf.dao.DeviceDao;
import zxf.model.Device;
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
public class DeviceListController {
    Device  device;
    PageBean pageBean;
    DbUtil dbUtil=new DbUtil();
    @Autowired DeviceDao  deviceDao;
    @RequestMapping("/deviceList")
    public void deviceList(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        String dev_identity=request.getParameter("d_dev_identity");
        String dev_name=request.getParameter("d_dev_name");
        String dev_type=request.getParameter("d_dev_type");
        String dev_manufacturer=request.getParameter("d_dev_manufacturer");
        String dev_usingstatus=request.getParameter("d_dev_usingstatus");
        String s_dev_buytime=request.getParameter("d_sdev_buytime");
        String e_dev_buytime=request.getParameter("d_edev_buytime");
        String dev_identification=request.getParameter("d_dev_identification");
        String s_depart_id=request.getParameter("d_depart_id");

        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        device=new Device();

        if (dev_identity!=null){
            device.setDev_identity(dev_identity);
            device.setDev_name(dev_name);
            device.setDev_type(dev_type);
            device.setDev_manufacturer(dev_manufacturer);
            device.setDev_usingstatus(dev_usingstatus);
            device.setDev_identification(dev_identification);
            if(StringUtil.isNotEmpty(s_depart_id)){
                device.setDev_departId(Integer.parseInt(s_depart_id));

            }
            if(StringUtil.isNotEmpty(s_depart_id)){
                device.setDev_departId(Integer.parseInt(s_depart_id));
            }
        }
        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONObject result = new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(deviceDao.deviceList(conn,pageBean,device,s_dev_buytime,e_dev_buytime));
            int total=deviceDao.deviceCount(conn,device,s_dev_buytime,e_dev_buytime);
            result.put("rows",jsonArray);
            result.put("total",total);
            ResponseUtil.write(response,result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeCon(conn);
        }
    }
    @RequestMapping("/deviceComboList")
    public void deviceComboList(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONArray jsonArray=new JSONArray();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("dev_id", "-2");
            jsonObject.put("dev_identity", "请选择设备");
            jsonArray.add(jsonObject);
            jsonArray.addAll(JsonUtil.formatRsToJsonArray(deviceDao.deviceComboList(conn)));
            ResponseUtil.write(response,jsonArray);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeCon(conn);
        }
    }
    @RequestMapping("/deviceDelete")
    public void deviceDelete(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String delIds=request.getParameter("delIds");
        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONObject result = new JSONObject();
            int delNums=deviceDao.deviceDelete(conn,delIds);
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
    @RequestMapping("/deviceSave")
    public void deviceSave(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        String dev_id=request.getParameter("dev_id");
        String add_dev_identity=request.getParameter("dev_identity");
        String add_dev_name=request.getParameter("dev_name");
        String add_dev_specification=request.getParameter("dev_specification");
        String add_dev_type=request.getParameter("dev_type");
        String add_dev_manufacturer=request.getParameter("dev_manufacturer");
        String add_dev_value=request.getParameter("dev_value");
        String depart_id=request.getParameter("depart_id");
        String add_dev_installaddr=request.getParameter("dev_installaddr");
        String add_dev_usingstatus=request.getParameter("dev_usingstatus");
        String add_dev_operater=request.getParameter("dev_operater");
        String add_dev_buytime=request.getParameter("dev_buytime");
        String add_dev_identification=request.getParameter("dev_identification");
        device=new Device();
        device.setDev_identity(add_dev_identity);
        device.setDev_name(add_dev_name);
        device.setDev_specification(add_dev_specification);
        device.setDev_type(add_dev_type);
        device.setDev_manufacturer(add_dev_manufacturer);
        if(StringUtil.isNotEmpty(add_dev_value)){
            device.setDev_value(Integer.parseInt(add_dev_value));
        }
        if(StringUtil.isNotEmpty(depart_id)){
            device.setDev_departId(Integer.parseInt(depart_id));
        }
        device.setDev_installaddr(add_dev_installaddr);
        device.setDev_usingstatus(add_dev_usingstatus);
        device.setDev_operater(add_dev_operater);
        device.setDev_buytime(DateUtil.formatString(add_dev_buytime,"yyyy-MM-dd"));
        device.setDev_identification(add_dev_identification);
        if(StringUtil.isNotEmpty(dev_id)){
            device.setDev_id(Integer.parseInt(dev_id));
        }
        Connection conn=null;
        try {
            conn=dbUtil.getCon();
            JSONObject result = new JSONObject();
            int saveNums=0;
            if(StringUtil.isNotEmpty(dev_id)){
                saveNums=deviceDao.deviceModify(conn,device);
            }else {
                saveNums=deviceDao.deviceAdd(conn,device);
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
