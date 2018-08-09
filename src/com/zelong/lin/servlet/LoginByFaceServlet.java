package com.zelong.lin.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import com.zelong.lin.dao.User;
import com.zelong.lin.utils.Base64ToImg;
import com.zelong.lin.utils.BufImgToMat;
import com.zelong.lin.utils.DlibJNIJAVA;
import com.zelong.lin.utils.FaceDetection;
import com.zelong.lin.utils.ImgPrint;
import com.zelong.lin.utils.MatToBufImg;
import com.zelong.lin.utils.MySql;

@WebServlet(name="LoginByFaceServlet",urlPatterns={"/LoginByFaceServlet"})
public class LoginByFaceServlet extends HttpServlet {
	static{
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );//载入opencv的库
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String msg="";
		String[] imgbase64=request.getParameterValues("faceBase");
		if(imgbase64==null){
			msg="获取照片失败！";
		}else{
			double eyemaxDistance=0;
			double eyeminDistance=1;
			double mouthmaxDistance=0;
			double mouthminDistance=1;
			String dat="E:/face68/shape_predictor_68_face_landmarks.dat";
			DlibJNIJAVA jni=new DlibJNIJAVA();
			for(int i=0;i<imgbase64.length;i++){				
				//还原图片，存至BufferedImage
				BufferedImage img=Base64ToImg.Base64ToImage(imgbase64[i]);
				String path="E:/TempOfFace/"+i+".png";
				File file=new File(path);
				if(!file.exists()){
					file.mkdirs();
				}
				//存入临时图片，供c++程序调取
				ImageIO.write(img, "png", file);
				double[] b=jni.getLandMarks(dat, path);
				//当能定位人脸时
				if(b[24]!=0 && b[44]!=0 && b[46]!=0 && b[57]!=0 && b[51]!=0 && b[8]!=0){
					double eye1=Math.pow(Math.abs(b[44]-b[24]),2)+Math.pow(Math.abs(b[91]-b[111]),2);
					double eye2=Math.pow(Math.abs(b[46]-b[24]),2)+Math.pow(Math.abs(b[89]-b[111]),2);
					double mouth1=Math.pow(Math.abs(b[57]-b[8]),2)+Math.pow(Math.abs(b[78]-b[127]),2);
					double mouth2=Math.pow(Math.abs(b[51]-b[8]),2)+Math.pow(Math.abs(b[84]-b[127]),2);
					double eyedistance=eye1/eye2;
					double eyepercent=Math.sqrt(eyedistance);
					double mouthdistance=mouth1/mouth2;
					double mouthpercent=Math.sqrt(mouthdistance);
					if(eyemaxDistance<eyepercent){
						eyemaxDistance=eyepercent;
					}
					if(eyeminDistance>eyepercent){
						eyeminDistance=eyepercent;
					}
					if(mouthmaxDistance<mouthpercent){
						mouthmaxDistance=mouthpercent;
					}
					if(mouthminDistance>mouthpercent){
						mouthminDistance=mouthpercent;
					}
				}else{
					eyemaxDistance=0;
					eyeminDistance=1;
					mouthmaxDistance=0;
					mouthminDistance=1;
					break;
				}					
			}
			//理想情况下if(eyemaxDistance-eyeminDistance>=0.15 && mouthmaxDistance-mouthminDistance>=0.25)
			if(eyemaxDistance-eyeminDistance>0.1 || mouthmaxDistance-mouthminDistance>0.2){
				msg="pass";
			}else{
				msg="疑似非真人登录，请重试！";
			}
		}
		response.getWriter().print(msg);
	}
}











