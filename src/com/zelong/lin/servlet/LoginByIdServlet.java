package com.zelong.lin.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zelong.lin.dao.User;
import com.zelong.lin.utils.MySql;

@WebServlet(name="LoginByIdServlet",urlPatterns={"/LoginByIdServlet"})
public class LoginByIdServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username=request.getParameter("user");
		String password=request.getParameter("pwd");
		String sql="select * from user where username=?";
		//创建数据库
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=MySql.getConn();
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next()){
				String user=rs.getString("username");
				String pwd=rs.getString("password");
				if(user.equals(username)&&pwd.equals(password)){
					User person=new User();
					person.setUsername(username);
					person.setPassword(password);
					HttpSession session=request.getSession();
					session.setAttribute("username", person.getUsername());
					session.setAttribute("password", person.getPassword());
					if(user.equals("admin")&&pwd.equals("admin")){
						response.sendRedirect(request.getContextPath()+"/adminWelcome.jsp");
					}else{
						response.sendRedirect(request.getContextPath()+"/Welcome.jsp");
					}					
				}else{
					request.setAttribute("msg", "密码有误!");
					request.getRequestDispatcher("/LoginById.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("msg", "用户不存在!");
				request.getRequestDispatcher("/LoginById.jsp").forward(request, response);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySql.closeConn(rs, ps, con);		
		}			
	}
}
