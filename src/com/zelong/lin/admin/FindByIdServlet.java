package com.zelong.lin.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zelong.lin.dao.User;

@WebServlet(name="FindByIdServlet",urlPatterns={"/FindByIdServlet"})
public class FindByIdServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int id=Integer.parseInt(request.getParameter("id"));		//String password=request.getParameter("pwd");
		HttpSession session=(HttpSession)request.getSession();
		if(session.getAttribute("adusername")!=null){
			UserService service=new UserService();
			User u =service.findById(id);
			session.setAttribute("u", u);
			response.sendRedirect(request.getContextPath()+"/edit.jsp");
		}else{
			response.sendRedirect(request.getContextPath()+"/logout.jsp");
		}
	}
}
