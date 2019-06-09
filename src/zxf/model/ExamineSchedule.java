package zxf.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Zxf
 * @date 2019/5/13  17:07
 */
@Data
public class ExamineSchedule {

    private int exam_id;
    private String exam_status;
    private String dev_identity;
    private String dev_name;
    private String dev_specification;
    private int    depart_id=-1;
    private String depart_name;
    private String exam_way;
    private String exam_period;
    private Date exam_lasttime;
    private Date exam_nexttime;
    private Date dev_buytime;
    private String dev_installaddr;
    private String exam_desc;

    public ExamineSchedule(){}


}
