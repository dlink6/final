package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Util {
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt !=null)
				pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs !=null)
				rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(Connection conn) {
		try {
			if(conn !=null)
				conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			 conn.rollback();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
