package com.zelong.lin.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySql {
	public static final String driver = "com.mysql.jdbc.Driver";// 驱动
	public static final String url = "jdbc:mysql://localhost:3306/facesystem?characterEncoding=utf8&useSSL=true";// mysql固定的URL:jdbc:mysql://localhost:3306/数据库名
	public static final String user = "root";// 数据库的用户名
	public static final String pwd = "1234";// 数据库密码

	public static Connection getConn() {
		Connection con = null;
		try {
			// 加载mysql驱动器
			Class.forName(driver);
			// 建立数据库连接
			con = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			System.out.println("加载驱动器失败");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("注册驱动器失败");
			e.printStackTrace();
		}
		return con;
	}
	// 关闭连接
	     public static void closeConn(ResultSet rs,PreparedStatement ps,Connection con) {
	    	 if (rs!=null) {
	             try {
	                 rs.close();
	             } catch (SQLException e) {
	                 System.out.println("关闭连接失败！");
	                 e.printStackTrace();
	             }
	         }
	    	 if (ps!=null) {
	             try {
	                 ps.close();
	             } catch (SQLException e) {
	                 System.out.println("关闭连接失败！");
	                 e.printStackTrace();
	             }
	         }
	         if (con!=null) {
	             try {
	                 con.close();
	             } catch (SQLException e) {
	                 System.out.println("关闭连接失败！");
	                 e.printStackTrace();
	             }
	         }
	     }
}