import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
public class SingleJasonJava {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, StreamWriteException, DatabindException, IOException {
		Class.forName("org.postgresql.Driver");
		Connection conn = null;
		JSONArray js = new JSONArray();
		ArrayList<CustomerDetails> a = new ArrayList<CustomerDetails>();
		String url = "jdbc:postgresql://localhost/Business?user=postgres&password=Taanu.rishu4";
		conn =DriverManager.getConnection(url);
		//object of statement class will help us to execute queries
		Statement st= conn.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM CustomersInfo WHERE location ='Asia'");
	//	rs.next();
		//rs.getString(1);
		//rs.getString(2);
		//rs.getInt(3);
		//rs.getString(4);
		//rs.next();
	while(rs.next()) {
		CustomerDetails cu=new CustomerDetails();
		cu.setCourseName(rs.getString(1));
		cu.setPurchaseDate(rs.getString(2));
		cu.setAmount(rs.getInt(3));
		cu.setLocation(rs.getString(4));
	a.add(cu);
		
		}
	for(int i=0; i<a.size();i++) {
	ObjectMapper o = new ObjectMapper();
	o.writeValue(new File("C:\\Users\\HP\\eclipse-workspace\\JsonJava\\customerInfo"+i+".json"),a.get(i) );
	Gson g = new Gson();
	String jsonString= g.toJson(a.get(i));
	js.add(jsonString);
	}
	JSONObject jo= new JSONObject();
	jo.put("data",js);
	//System.out.println(jo.toJSONString());
	String unescapeString=StringEscapeUtils.unescapeJava(jo.toJSONString());
	System.out.println(unescapeString);
	String str1= unescapeString.replace("\"{", "{");
	String str2= str1.replace("}\"", "}");
	System.out.println(str2);
	try(FileWriter file = new FileWriter("C:\\Users\\HP\\eclipse-workspace\\JsonJava\\SingleJson.json"))
	{file.write(str2);
	}
	
	conn.close();
	}
}
