package zxf.model;
import lombok.Data;
import org.springframework.stereotype.Component;
/**
 * @author
 */
@Data
public class User {
    private int    user_id;
    private String emp_no;
    private String emp_pass;
    private String type;
    private String head_path;
    public User() {
        super();
    }
    public User(String empName, String password) {
        super();
        this.emp_no = empName;
        this.emp_pass = password;
    }

//    private int id;
//    private String user_no;
//    private String user_pass;
//    private String user_tel_num;
//    private String user_name;
//    private String user_addr;
//    private String user_email;
//
//    public User() {
//        super();
//    }
//    public User(String userName, String password) {
//        super();
//        this.user_no = userName;
//        this.user_pass = password;
//    }
//    public User(String user_no,String user_name,String user_tel_num,String user_addr,String user_email){
//        super();
//        this.user_addr=user_addr;
//        this.user_email=user_email;
//        this.user_name=user_name;
//        this.user_no=user_no;
//        this.user_tel_num=user_tel_num;
//    }
//    public User(String user_no,String user_name,String user_tel_num,String user_pass,String user_addr,String user_email){
//        super();
//        this.user_addr=user_addr;
//        this.user_email=user_email;
//        this.user_pass=user_pass;
//        this.user_name=user_name;
//        this.user_no=user_no;
//        this.user_tel_num=user_tel_num;
//    }
}
