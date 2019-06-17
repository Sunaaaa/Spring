package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//conn 만들고 반납하는 친구여

public class JDBCUtil {
	public static Connection getConnection() {
		Connection con = null;
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("C://lib/dbinfo.txt"));
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String pw = p.getProperty("pw");
			String driver = p.getProperty("driver");
			
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pw);		
			
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("jdbc driver 확인 요망");
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("dbinfo 파일 확인 요망");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return con;
	}

	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
