package zxf.dao;

import org.springframework.stereotype.Repository;
import zxf.model.Report;
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
public class ReportDao {

    public ResultSet reportList(Connection con, PageBean pageBean, Report report) throws Exception {
        StringBuffer sb = new StringBuffer("select * from report r,device d where r.dev_id=d.dev_id ");
        if(report!=null && StringUtil.isNotEmpty(report.getReport_no())){
            sb.append(" and r.report_no like '%"+report.getReport_no()+"%'");
        }
        if(report!=null && StringUtil.isNotEmpty(report.getReport_serialNumber())){
            sb.append(" and r.report_serialNumber like '%"+report.getReport_serialNumber()+"%'");
        }
        if(report!=null && StringUtil.isNotEmpty(report.getDev_name())){
            sb.append(" and d.dev_name like'%"+report.getDev_name()+"%'");
        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    public int reportCount(Connection connection, Report report) throws Exception {
        StringBuilder sb = new StringBuilder("select count(*) as total from report r,device d where r.dev_id=d.dev_id ");
        if(report!=null && StringUtil.isNotEmpty(report.getReport_no())){
            sb.append(" and r.report_no like '%"+report.getReport_no()+"%'");
        }
        if(report!=null && StringUtil.isNotEmpty(report.getReport_serialNumber())){
            sb.append(" and r.report_serialNumber like '%"+report.getReport_serialNumber()+"%'");
        }
        if(report!=null && StringUtil.isNotEmpty(report.getDev_name())){
            sb.append(" and d.dev_name like'%"+report.getDev_name()+"%'");
        }

        PreparedStatement psmt = connection.prepareStatement(sb.toString());
        ResultSet rs = psmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }

    }

//    public int reportDelete(Connection connection,String delIds)throws Exception{
//        String reportSql="delete from report where emp_id in ("+delIds+")";
//        PreparedStatement pstmt=connection.prepareStatement(reportSql);
//        return pstmt.executeUpdate();
//    }
//
    public int reportAdd(Connection connection,Report report)throws Exception{
        String sql="insert  into report values (null,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,report.getReport_no());
        pstmt.setString(2,report.getReport_name());
        pstmt.setString(3,report.getReport_serialNumber());
        pstmt.setString(4,report.getDev_id());
        pstmt.setString(5,report.getReport_identifyDepart());
        pstmt.setString(6,report.getReport_identifier());
        pstmt.setString(7,DateUtil.formatDate(report.getReport_identifyTime(),"yyyy-MM-dd"));
        return pstmt.executeUpdate();
    }

    public int reportModify(Connection connection,Report report)throws Exception{
        String sql="update report set report_no=?,report_name=?,report_serialNumber=?,dev_id=?,report_identifyDepart=?,report_identifier=?,report_identifyTime=? where report_id=?";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,report.getReport_no());
        pstmt.setString(2,report.getReport_name());
        pstmt.setString(3,report.getReport_serialNumber());
        pstmt.setString(4,report.getDev_id()+"");
        pstmt.setString(5,report.getReport_identifyDepart());
        pstmt.setString(6,report.getReport_identifier());
        pstmt.setString(7,DateUtil.formatDate(report.getReport_identifyTime(),"yyyy-MM-dd"));
        pstmt.setString(8,report.getReport_id()+"");
        return pstmt.executeUpdate();
    }


}
