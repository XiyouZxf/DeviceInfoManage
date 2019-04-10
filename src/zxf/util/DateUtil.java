package zxf.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private int user_id;
	private String user_no;
	private String user_name;
	private String user_pass;
	private String user_tel_num;
	private int    user_type;
	private String user_addr;
	private String user_email;
	private String head_path;

	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
	
	
	public static Date formatString(String str,String format) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
}
