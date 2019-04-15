package zxf.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author
 *
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
	public User() {
		super();
	}
	public User(String userName, String password) {
		super();
		this.user_no = userName;
		this.user_pass = password;
	}


}
