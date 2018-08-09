package com.zelong.lin.dao;

public class User {
	private int id=0;
	private String username=null;
	private String password=null;
	private String imgsrc=null;
	private byte[] imgprint=null;
	public User(){}
	public User(int id, String username, String password, String imgsrc, byte[] imgprint) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.imgsrc = imgsrc;
		this.imgprint = imgprint;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public byte[] getImgprint() {
		return imgprint;
	}
	public void setImgprint(byte[] imgprint) {
		this.imgprint = imgprint;
	};	
}
