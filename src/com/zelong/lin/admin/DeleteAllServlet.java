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

@WebServlet(name="DeleteAllServlet",urlPatterns={"/DeleteAllServlet"})
public class DeleteAllServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String[] id=request.getParameterValues("ck");
		HttpSession session=(HttpSession)request.getSession();
		if(session.getAttribute("adusername")!=null){
			UserService service=new UserService();
			service.deleteAll(id);
			response.sendRedirect(request.getContextPath()+"/FindAllServlet");
		}else{
			response.sendRedirect(request.getContextPath()+"/logout.jsp");
		}
	}
}
