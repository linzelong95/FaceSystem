package com.zelong.lin.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zelong.lin.dao.User;
import com.zelong.lin.utils.MySql;

public class UserDao {
	
	private List<User> list= new ArrayList<User>();
	PageBean pb=new PageBean();

	public PageBean findAll(int pageNum,int currentPage) {
		String sql="select * from user limit ?,?";
		//创建数据库
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=MySql.getConn();
			ps=con.prepareStatement(sql);
			ps.setInt(1, (pageNum-1)*currentPage);
			ps.setInt(2, currentPage);
			rs=ps.executeQuery();
			while(rs.next()){
				int id=rs.getInt("id");
				String username=rs.getString("username");
				String password=rs.getString("password");
				String imgsrc=rs.getString("imgsrc");
				byte[] imgprint=rs.getBytes("imgprint");
				User u=new User(id,username,password,imgsrc,imgprint);
				list.add(u);			
			}
			int totalCount=findCount();
			int totalPage=(int)Math.ceil(totalCount*1.0/currentPage);
			pb.setTotalPage(totalPage);
			pb.setTotalCount(totalCount);
			pb.setU(list);
			pb.setCurrentPage(currentPage);
			pb.setPageNum(pageNum);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySql.closeConn(rs, ps, con);			
		}
		return pb;
	}

	private int findCount() {
		int count=0;
		String sql="select count(*) from user";
		//创建数据库
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=MySql.getConn();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySql.closeConn(rs, ps, con);			
		}
		return count;
	}

	public void deleteById(int id) {
		String sql="delete from user where id=?";
		//创建数据库
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=MySql.getConn();
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySql.closeConn(rs, ps, con);			
		}
		
	}

	public User findById(int id) {
		String sql="select * from user where id=?";
		//创建数据库
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User u=null;
		try {
			con=MySql.getConn();
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){			
				String username=rs.getString("username");
				String password=rs.getString("password");
				String imgsrc=rs.getString("imgsrc");
				byte[] imgprint=rs.getBytes("imgprint");
				u=new User(id,username,password,imgsrc,imgprint);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySql.closeConn(rs, ps, con);
			
		}
		return u;
	}

	public void edit(int id, String username, String password, String imgsrc) {
		String sql="update user set username=?,password=?,imgsrc=? where id=?";
		//创建数据库
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=MySql.getConn();
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, imgsrc);
			ps.setInt(4, id);
			ps.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySql.closeConn(rs, ps, con);			
		}
		
	}

	public void deleteAll(String[] id) {
		String sql="delete from user where id=?";
		//创建数据库
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=MySql.getConn();
			con.setAutoCommit(false);
			ps=con.prepareStatement(sql);
			for(int i=0;i<id.length;i++){
				if(id[i].equals("1")){
					continue;
				}
				int num=Integer.parseInt(id[i]);
				ps.setInt(1, num);
				ps.addBatch();
			}
			ps.executeBatch();
			con.commit();
			con.setAutoCommit(true);			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySql.closeConn(rs, ps, con);	
		}
	}

	public PageBean simpleSelect(int pageNum, int currentPage, String field, String msg) {
		int i=0;
		String sql="select * from user where "+field+" like ? limit ?,?";
		//创建数据库
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=MySql.getConn();
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+msg+"%");
			ps.setInt(2, (pageNum-1)*currentPage);
			ps.setInt(3, currentPage);
			rs=ps.executeQuery();
			while(rs.next()){
				int id=rs.getInt("id");
				String username=rs.getString("username");
				String password=rs.getString("password");
				String imgsrc=rs.getString("imgsrc");
				byte[] imgprint=rs.getBytes("imgprint");
				User u=new User(id,username,password,imgsrc,imgprint);
				list.add(u);
				i++;
			}
			if(i==0){
				pb=null;
				return pb;
			}else{
				int totalCount=findBySelect(field, msg);
				int totalPage=(int)Math.ceil(totalCount*1.0/currentPage);
				pb.setTotalPage(totalPage);
				pb.setTotalCount(totalCount);
				pb.setU(list);
				pb.setCurrentPage(currentPage);
				pb.setPageNum(pageNum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySql.closeConn(rs, ps, con);			
		}
		return pb;
	}

	private int findBySelect(String field, String msg) {
		int count=0;
		String sql="select count(*) from user where "+field+" like ?";
		//创建数据库
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=MySql.getConn();
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+msg+"%");
			rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySql.closeConn(rs, ps, con);			
		}
		return count;
	}

}
