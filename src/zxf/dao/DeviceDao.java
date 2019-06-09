package zxf.dao;

import org.springframework.stereotype.Repository;
import zxf.model.Department;
import zxf.model.Device;
import zxf.model.PageBean;
import zxf.util.DateUtil;
import zxf.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Zxf
 * @date 2019/4/26  21:51
 */
@Repository
public class DeviceDao {
    public ResultSet deviceComboList(Connection con) throws Exception {
        StringBuffer sb = new StringBuffer("select * from device ");
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    public ResultSet deviceList(Connection con, PageBean pageBean, Device device,String s_dev_buytime,String e_dev_buytime) throws Exception {
        StringBuffer sb = new StringBuffer("select * from device d,department depart where d.dev_departId=depart.depart_id");
        if(StringUtil.isNotEmpty(device.getDev_identity())){
            sb.append(" and d.dev_identity like '%"+device.getDev_identity()+"%'");
        }
        if(StringUtil.isNotEmpty(device.getDev_name())){
            sb.append(" and d.dev_name like '%"+device.getDev_name()+"%'");
        }
        if(StringUtil.isNotEmpty(device.getDev_type())){
            sb.append(" and d.dev_type like '%"+device.getDev_type()+"%'");
        }
        if(StringUtil.isNotEmpty(device.getDev_manufacturer())){
            sb.append(" and d.dev_manufacturer like '%"+device.getDev_manufacturer()+"%'");
        }
        if(StringUtil.isNotEmpty(device.getDev_usingstatus())){
            sb.append(" and d.dev_usingstatus like '%"+device.getDev_usingstatus()+"%'");
        }
        if(StringUtil.isNotEmpty(s_dev_buytime)){
            sb.append(" and TO_DAYS(d.dev_buytime)>=TO_DAYS('"+s_dev_buytime+"')");
        }
        if(StringUtil.isNotEmpty(e_dev_buytime)){
            sb.append(" and TO_DAYS(d.dev_buytime)<=TO_DAYS('"+e_dev_buytime+"')");
        }
        if(StringUtil.isNotEmpty(device.getDev_identification())){
            sb.append(" and d.dev_identification like '%"+device.getDev_identification()+"%'");
        }
        if(device.getDev_departId() != -1){
            if(device.getDev_departId() != -2){
                sb.append(" and d.dev_departId = '"+device.getDev_departId()+"'");
            }else{
                sb.append(" and d.dev_departId like '%"+"_"+"%'");
            }

        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    public int deviceCount(Connection connection, Device device,String s_dev_buytime,String e_dev_buytime) throws Exception {
        StringBuilder sb = new StringBuilder("select count(*) as total from device d,department depart where d.dev_departId=depart.depart_id");
        if(StringUtil.isNotEmpty(device.getDev_identity())){
            sb.append(" and d.dev_identity like '%"+device.getDev_identity()+"%'");
        }
        if(StringUtil.isNotEmpty(device.getDev_name())){
            sb.append(" and d.dev_name like '%"+device.getDev_name()+"%'");
        }
        if(StringUtil.isNotEmpty(device.getDev_type())){
            sb.append(" and d.dev_type like '%"+device.getDev_type()+"%'");
        }
        if(StringUtil.isNotEmpty(device.getDev_manufacturer())){
            sb.append(" and d.dev_manufacturer like '%"+device.getDev_manufacturer()+"%'");
        }
        if(StringUtil.isNotEmpty(device.getDev_usingstatus())){
            sb.append(" and d.dev_usingstatus = '"+device.getDev_usingstatus()+"'");
        }
        if(StringUtil.isNotEmpty(s_dev_buytime)){
            sb.append(" and TO_DAYS(d.dev_buytime)>=TO_DAYS('"+s_dev_buytime+"')");
        }
        if(StringUtil.isNotEmpty(e_dev_buytime)){
            sb.append(" and TO_DAYS(d.dev_buytime)<=TO_DAYS('"+e_dev_buytime+"')");
        }

        if(StringUtil.isNotEmpty(device.getDev_identification())){
            sb.append(" and d.dev_identification = '"+device.getDev_identification()+"'");

        }
        if(device.getDev_departId() != -1){
            sb.append(" and d.dev_departId = '"+device.getDev_departId()+"'");
        }
        PreparedStatement psmt = connection.prepareStatement(sb.toString());
        ResultSet rs = psmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }

    }

    public int deviceDelete(Connection connection,String delIds)throws Exception{
        String deviceSql="delete from device where dev_id in ("+delIds+")";
        PreparedStatement pstmt=connection.prepareStatement(deviceSql);
        return pstmt.executeUpdate();
    }

    public int deviceAdd(Connection connection,Device device)throws Exception{
        String sql="insert  into device values (null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,device.getDev_identity());
        pstmt.setString(2,device.getDev_name());
        pstmt.setString(3,device.getDev_specification());
        pstmt.setString(4,device.getDev_type());
        pstmt.setString(5,device.getDev_manufacturer());
        pstmt.setString(6,device.getDev_value()+"");
        pstmt.setString(7,device.getDev_departId()+"");
        pstmt.setString(8,device.getDev_installaddr());
        pstmt.setString(9,device.getDev_usingstatus());
        pstmt.setString(10,device.getDev_operater());
        pstmt.setString(11,DateUtil.formatDate(device.getDev_buytime(), "yyyy-MM-dd"));
        pstmt.setString(12,DateUtil.formatDate(device.getDev_buytime(), "yyyy-MM-dd"));    //有问题
        pstmt.setString(13,device.getDev_identification());

        return pstmt.executeUpdate();
    }

    public int deviceModify(Connection connection,Device device)throws Exception{
        String sql="update device set dev_identity=?,dev_name=?,dev_specification=?,dev_type=?,dev_manufacturer=?," +
                "         dev_value=?,dev_departId=?,dev_installaddr=?,dev_usingstatus=?,dev_operater=?,dev_buytime=?," +
                "          dev_identification=? where dev_id=?";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,device.getDev_identity());
        pstmt.setString(2,device.getDev_name());
        pstmt.setString(3,device.getDev_specification());
        pstmt.setString(4,device.getDev_type());
        pstmt.setString(5,device.getDev_manufacturer());
        pstmt.setString(6,device.getDev_value()+"");
        pstmt.setString(7,device.getDev_departId()+"");
        pstmt.setString(8,device.getDev_installaddr());
        pstmt.setString(9,device.getDev_usingstatus());
        pstmt.setString(10,device.getDev_operater());
        pstmt.setString(11,DateUtil.formatDate(device.getDev_buytime(), "yyyy-MM-dd"));
        pstmt.setString(12,device.getDev_identification());
        pstmt.setString(13,device.getDev_id()+"");

        return pstmt.executeUpdate();
    }


}
