package zxf.model;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Zxf
 * @date 2019/5/6  15:29
 */
@Data
public class Department {
    private int depart_id;
    private String depart_identity;
    private String depart_name;
    private int depart_includePeople;
    private String depart_tel_num;
    private String depart_status;
    private String depart_desc;
    public  Department(){}

    public Department(String depart_identity, String depart_name) {
        this.depart_identity = depart_identity;
        this.depart_name = depart_name;
    }

    public Department(int depart_id,String depart_identity, String depart_name, int depart_includePeople, String depart_tel_num, String depart_status, String depart_desc) {
        this.depart_id = depart_id;
        this.depart_name = depart_name;
        this.depart_identity = depart_identity;
        this.depart_includePeople = depart_includePeople;
        this.depart_tel_num = depart_tel_num;
        this.depart_desc = depart_desc;
        this.depart_status = depart_status;
    }
}
