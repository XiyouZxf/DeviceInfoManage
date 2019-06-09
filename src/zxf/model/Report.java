package zxf.model;
import lombok.Data;

import java.util.Date;
/**
 * @author Zxf
 * @date 2019/5/23  12:56
 */
@Data
public class Report {
    private int report_id;
    private String report_name;
    private String dev_specification;
    private String report_serialNumber;
    private String report_no;
    private String report_identifyDepart;
    private String report_identifier;
    private Date report_identifyTime;
    private String    dev_id;
    private String dev_name;

    public Report(){}


}
