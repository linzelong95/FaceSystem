package com.zelong.lin.admin;

import com.zelong.lin.dao.User;

public class UserService {
	
	UserDao dao=new UserDao();

	public PageBean findAll(int pageNum,int currentPage) {
		// TODO Auto-generated method stub
		return dao.findAll(pageNum,currentPage);
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	public User findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	public void edit(int id, String username, String password, String imgsrc) {
		// TODO Auto-generated method stub
		dao.edit(id,username,password,imgsrc);
	}

	public void deleteAll(String[] id) {
		// TODO Auto-generated method stub
		dao.deleteAll(id);
	}

	public PageBean simpleSelect(int pageNum, int currentPage, String field, String msg) {
		// TODO Auto-generated method stub
		return dao.simpleSelect(pageNum, currentPage, field, msg);
	}

}
