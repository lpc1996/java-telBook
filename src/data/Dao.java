package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Record;

public class Dao {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/telbook?characterEncoding=utf8&useSSL=true";
	private static final String USER = "root";
	private static final String PASS = "xx1602614lpc";
	private static Connection conn = null;
	
	public Dao() {
		try {
			if(conn == null) {//如果连接对象为空
				Class.forName(JDBC_DRIVER);//加载驱动类
				conn = (Connection)DriverManager.getConnection(URL,USER,PASS);//获得连接对象
			}
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println("连接失败 ");
		}
	}
	
	private static  ResultSet executeQuery(String sql) {
		try {
			if(conn == null) {
				new Dao();
			}
			if(conn != null)
				return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			else
				return null;
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			
		}	
	}
	
	private static int executeUpdata(String sql) {
		try {
			if(conn == null) {
				new Dao();
			}
			if(conn != null)
				return conn.createStatement().executeUpdate(sql);
			else
				return -1;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}finally {
			
		}
	}
	
	private static void close() {
		try {
			if(conn == null) {
				return;
			}else
				conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			conn = null;
		}
	}
	
	public int getRows() {
		int index = 0;
		String sql = "select * from telinfo;";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()) {
				index++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		Dao.close();
		return index;
	}
	
	public Record getValue(int arg0) {
		Record rec = null;
		int i = 0;
		String sql = "Select * from telinfo;";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()) {
				if(i == arg0) {
					if(rec == null)
						rec = new Record();
					rec.setId(rs.getInt("id"));
					rec.setName(rs.getString("name"));
					rec.setTel(rs.getString("tel"));
					break;
				}else {
					i++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		Dao.close();
		return rec;
	}
	
	public int insertData(Record rec) {
		int i = 0;
		String sql = "insert into telinfo values("+rec.getId()+",'"+rec.getName()+"','"+rec.getTel()+"')";
		try {
			i = Dao.executeUpdata(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
		Dao.close();
		return i;
	}
	
	public int upData(int id,Record rec) {
		int i = 0;
		String sql = "update telinfo set name='"+rec.getName()+"',tel='"+rec.getTel()+"' where id="+id;
		try {
			i = Dao.executeUpdata(sql);
			Dao.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return i;
	}
	
	public int deleteData(Record rec) {
		int i = 0;
		String sql = "delete from telinfo where id="+rec.getId();
		try {
			i = Dao.executeUpdata(sql);
			Dao.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return i;
	}
	public Record searchData(String str) {
		Record rec = null;
		int i = -1;
		String regular = "\\d{1,}";
		if(str.matches(regular)) {
			i = Integer.parseInt(str);
		}
		String sql = "select * from telinfo where id="+i+" or name='"+str+"' or tel='"+str+"'";
		ResultSet res = Dao.executeQuery(sql);
		try {
			rec = new Record();
			while(res.next()) {
				rec.setId(res.getInt("id"));
				rec.setName(res.getString("name"));
				rec.setTel(res.getString("tel"));
			}
			Dao.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return rec;
	}
	
	public int isInData(Record rec) {
		int i = -1;
		String sql = "Select id from telinfo where id="+rec.getId();
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()) {
				i = 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		Dao.close();
		return i;
	}
}
