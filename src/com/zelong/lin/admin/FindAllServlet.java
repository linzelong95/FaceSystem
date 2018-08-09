package com.zelong.lin.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="FindAllServlet",urlPatterns={"/FindAllServlet"})
public class FindAllServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int pageNum=1;
		int currentPage=5;
		String pageNum_1=request.getParameter("pageNum");
		
		if(pageNum_1!=null){
			pageNum=Integer.parseInt(pageNum_1);
		}
		HttpSession session=(HttpSession)request.getSession();
		if(session.getAttribute("adusername")!=null){
			UserService service=new UserService();
			PageBean pb=service.findAll(pageNum,currentPage);
			session.setAttribute("pb", pb);
			response.sendRedirect(request.getContextPath()+"/showUser.jsp");
			//return;
		}else{
			response.sendRedirect(request.getContextPath()+"/logout.jsp");
		}
		
	}
}
