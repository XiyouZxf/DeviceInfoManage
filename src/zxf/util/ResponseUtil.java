package zxf.util;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
@Component
public class ResponseUtil {

	public static void write(HttpServletResponse response,JSONObject jsonObject)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(jsonObject.toString());
		out.flush();
		out.close();
	}
}
