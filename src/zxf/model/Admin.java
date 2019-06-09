package zxf.model;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Zxf
 * @date 2019/4/26  21:20
 */
@Data
public class Admin {
    private int admin_id;
    private String admin_no;
    private String admin_pass;
    private String admin_name;
    private String admin_sex;
    private String admin_tel_num;
    private String admin_addr;
    private String admin_email;
    public Admin(){}
    public Admin(String admin_no, String admin_pass){
        this.admin_no = admin_no;
        this.admin_pass = admin_pass;
    }
    public Admin(String admin_no, String admin_name, String admin_sex, String admin_tel_num, String admin_addr, String admin_email) {
        this.admin_no = admin_no;
        this.admin_name = admin_name;
        this.admin_sex = admin_sex;
        this.admin_tel_num = admin_tel_num;
        this.admin_addr = admin_addr;
        this.admin_email = admin_email;
    }
}
