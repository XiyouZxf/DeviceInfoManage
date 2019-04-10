package zxf.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author
 *
 */


public class User {

	@Getter@Setter private int id;
	@Getter@Setter private String user_no;
	@Getter@Setter private String user_pass;
	@Getter@Setter private String user_tel_num;
	@Getter@Setter private String user_name;
	@Getter@Setter private String user_addr;

	public User() {
		super();
	}
	public User(String userName, String password) {
		this.user_no = userName;
		this.user_pass = password;
	}

}
