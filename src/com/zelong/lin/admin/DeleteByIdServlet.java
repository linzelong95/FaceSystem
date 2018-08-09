package com.zelong.lin.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="DeleteByIdServlet",urlPatterns={"/DeleteByIdServlet"})
public class DeleteByIdServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int id=Integer.parseInt(request.getParameter("id"));
		
		HttpSession session=(HttpSession)request.getSession();
		if(session.getAttribute("adusername")!=null){
			UserService service=new UserService();
			service.deleteById(id);
			response.sendRedirect(request.getContextPath()+"/FindAllServlet");
			return;
		}else{
			response.sendRedirect(request.getContextPath()+"/logout.jsp");
		}
	}
}
