package zxf.dao;

import org.springframework.stereotype.Repository;
import zxf.model.ExamineSchedule;
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
public class ExamineScheduleDao {

    public ResultSet examineScheduleList(Connection con, PageBean pageBean, ExamineSchedule examineSchedule,String s_dev_buytime,String e_dev_buytime) throws Exception {
        StringBuffer sb = new StringBuffer(
                "select * from examineSchedule e,department dep,device dev where e.exam_devId=dev.dev_id and dev.dev_departId=dep.depart_id ");
        if(StringUtil.isNotEmpty(examineSchedule.getExam_status())){
            sb.append(" and e.exam_status = '"+examineSchedule.getExam_status()+"'");
        }
        if(StringUtil.isNotEmpty(examineSchedule.getDev_identity())){
            sb.append(" and dev.dev_identity like '%"+examineSchedule.getDev_identity()+"%'");
        }
        if(StringUtil.isNotEmpty(examineSchedule.getDev_name())){
            sb.append(" and dev.dev_name like '%"+examineSchedule.getDev_name()+"%'");
        }
        if(StringUtil.isNotEmpty(s_dev_buytime)){
            sb.append(" and TO_DAYS(dev.dev_buytime)>=TO_DAYS('"+s_dev_buytime+"')");
        }
        if(StringUtil.isNotEmpty(e_dev_buytime)){
            sb.append(" and TO_DAYS(dev.dev_buytime)<=TO_DAYS('"+e_dev_buytime+"')");
        }
        if(examineSchedule.getDepart_id() != -1){
            if(examineSchedule.getDepart_id() != -2){
                sb.append(" and dev.dev_departId = '"+examineSchedule.getDepart_id()+"'");
            }else{
                sb.append(" and dev.dev_departId like '%"+"_"+"%'");
            }
        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    public int examineScheduleCount(Connection connection, ExamineSchedule examineSchedule,String s_dev_buytime,String e_dev_buytime) throws Exception {
        StringBuilder sb = new StringBuilder("select count(*) as total from examineSchedule e,department dep,device dev where e.exam_devId=dev.dev_id and dev.dev_departId=dep.depart_id ");
        if(StringUtil.isNotEmpty(examineSchedule.getExam_status())){
            sb.append(" and e.exam_status = '"+examineSchedule.getExam_status()+"'");
        }
        if(StringUtil.isNotEmpty(examineSchedule.getDev_identity())){
            sb.append(" and dev.dev_identity like '%"+examineSchedule.getDev_identity()+"%'");
        }
        if(StringUtil.isNotEmpty(examineSchedule.getDev_name())){
            sb.append(" and dev.dev_name like '%"+examineSchedule.getDev_name()+"%'");
        }
        if(StringUtil.isNotEmpty(s_dev_buytime)){
            sb.append(" and TO_DAYS(dev.dev_buytime)>=TO_DAYS('"+s_dev_buytime+"')");
        }
        if(StringUtil.isNotEmpty(e_dev_buytime)){
            sb.append(" and TO_DAYS(dev.dev_buytime)<=TO_DAYS('"+e_dev_buytime+"')");
        }
        if(examineSchedule.getDepart_id() != -1){
            if(examineSchedule.getDepart_id() != -2){
                sb.append(" and dev.dev_departId = '"+examineSchedule.getDepart_id()+"'");
            }else{
                sb.append(" and dev.dev_departId like '%"+"_"+"%'");
            }
        }
        PreparedStatement psmt = connection.prepareStatement(sb.toString());
        ResultSet rs = psmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }

    }

    public int examineScheduleDelete(Connection connection,String delIds)throws Exception{
        String examineScheduleSql="delete from examineSchedule where exam_id in ("+delIds+")";
        PreparedStatement pstmt=connection.prepareStatement(examineScheduleSql);
        return pstmt.executeUpdate();
    }

    public int examineScheduleAdd(Connection connection,ExamineSchedule examineSchedule)throws Exception{
        String sql="insert  into examineSchedule values (null,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,examineSchedule.getExam_status());
        pstmt.setString(2,examineSchedule.getDev_identity());
        pstmt.setString(3,examineSchedule.getExam_way());
        pstmt.setString(4,examineSchedule.getExam_period());
        pstmt.setString(5,"2019-01-01");
        pstmt.setString(6,"2019-01-01");
        pstmt.setString(7,examineSchedule.getExam_desc());
        return pstmt.executeUpdate();
    }

    public int examineScheduleModify(Connection connection,ExamineSchedule examineSchedule)throws Exception{
        String sql="update examineSchedule set exam_status=?,exam_devId=?,exam_way=?,exam_period=?,exam_lasttime=?," +
                "         exam_nexttime=?,exam_desc=? where exam_id=?";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,examineSchedule.getExam_status());
        pstmt.setString(2,examineSchedule.getDev_identity());
        pstmt.setString(3,examineSchedule.getExam_way());
        pstmt.setString(4,examineSchedule.getExam_period());
        pstmt.setString(5,"2019-01-01");
        pstmt.setString(6,"2019-01-01");
        pstmt.setString(7,examineSchedule.getExam_desc());
        pstmt.setString(8,examineSchedule.getExam_id()+"");
        return pstmt.executeUpdate();
    }


}
