package com.zelong.lin.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="EditServlet",urlPatterns={"/EditServlet"})
public class EditServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session=(HttpSession)request.getSession();
		if(session.getAttribute("adusername")!=null){
			int id=Integer.parseInt(request.getParameter("id"));
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String imgsrc=request.getParameter("imgsrc");
			UserService service=new UserService();
			service.edit(id,username,password,imgsrc);
			response.sendRedirect(request.getContextPath()+"/FindAllServlet");
		}else{
			response.sendRedirect(request.getContextPath()+"/logout.jsp");
		}
	}
}
