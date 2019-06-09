package zxf.model;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Zxf
 * @date 2019/4/26  21:20
 */
@Data
public class Employee {
    private int emp_id;
    private String emp_no;
    private String emp_pass;
    private String emp_name;
    private String emp_sex;
    private String emp_tel_num;
    private String emp_addr;
    private String emp_email;
    public Employee(){}

    public Employee(String emp_no, String emp_name, String emp_sex) {
        this.emp_no = emp_no;
        this.emp_name = emp_name;
        this.emp_sex = emp_sex;
    }

    public Employee(String emp_no, String emp_pass){
        this.emp_no = emp_no;
        this.emp_pass = emp_pass;
    }
    public Employee(String emp_no, String emp_name,String emp_sex, String emp_tel_num, String emp_addr, String emp_email) {
        this.emp_no = emp_no;
        this.emp_name = emp_name;
        this.emp_sex = emp_sex;
        this.emp_tel_num = emp_tel_num;
        this.emp_addr = emp_addr;
        this.emp_email = emp_email;
    }
}
