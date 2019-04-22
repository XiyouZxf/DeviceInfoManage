package zxf.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author
 */
@Getter
@Setter
@Component
public class User {
    private int id;
    private String user_no;
    private String user_pass;
    private String user_tel_num;
    private String user_name;
    private String user_addr;
    private String user_email;

    public User() {
        super();
    }
    public User(String userName, String password) {
        super();
        this.user_no = userName;
        this.user_pass = password;
    }
    public User(String user_no,String user_name,String user_tel_num,String user_addr,String user_email){
        super();
        this.user_addr=user_addr;
        this.user_email=user_email;
        this.user_name=user_name;
        this.user_no=user_no;
        this.user_tel_num=user_tel_num;
    }
    public User(String user_no,String user_name,String user_tel_num,String user_pass,String user_addr,String user_email){
        super();
        this.user_addr=user_addr;
        this.user_email=user_email;
        this.user_pass=user_pass;
        this.user_name=user_name;
        this.user_no=user_no;
        this.user_tel_num=user_tel_num;
    }


}
