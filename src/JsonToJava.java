import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class JsonToJava {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, StreamWriteException, DatabindException, IOException {
		Class.forName("org.postgresql.Driver");
		Connection conn = null;
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
	}
	conn.close();
	}
}
