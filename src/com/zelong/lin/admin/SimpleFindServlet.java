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

@WebServlet(name="SimpleFindServlet",urlPatterns={"/SimpleFindServlet"})
public class SimpleFindServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String field=request.getParameter("field");
		String msg=request.getParameter("msg");
		int pageNum=1;
		int currentPage=5;
		String pageNum_1=request.getParameter("pageNum");
		
		if(pageNum_1!=null){
			pageNum=Integer.parseInt(pageNum_1);
		}
		HttpSession session=(HttpSession)request.getSession();
		if(session.getAttribute("adusername")!=null){
			UserService service=new UserService();
			PageBean pb=service.simpleSelect(pageNum,currentPage,field,msg);
			session.setAttribute("pb", pb);
			session.setAttribute("field", field);
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath()+"/showSomeone.jsp");
			//return;
		}else{
			response.sendRedirect(request.getContextPath()+"/logout.jsp");
		}
	}
}
