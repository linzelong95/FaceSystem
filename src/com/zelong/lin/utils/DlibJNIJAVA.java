package com.zelong.lin.utils;

public class DlibJNIJAVA {
	static{
		System.load("E:/face68/DlibJNI.dll");
	}
	//路径不能带中文
	public native double[] getLandMarks(String dat,String img);


//	public static void main(String[] args){
//		String dat="E:/face68/shape_predictor_68_face_landmarks.dat";
//		String img="E:/FaceOfUser/lintianpei.png";
//		DlibJNIJAVA d=new DlibJNIJAVA();
//		double[] b=d.getLandMarks(dat, img);
//		System.out.println("start  "+b[46]);
//		if(b[46]!=0 && b[51]!=0){
//			//double eye1=Math.pow(Math.abs(b[44]-b[24]),2)+Math.pow(Math.abs(b[91]-b[111]),2);
//			//double eye2=Math.pow(Math.abs(b[46]-b[24]),2)+Math.pow(Math.abs(b[89]-b[111]),2);
//			//double mouth1=Math.pow(Math.abs(b[57]-b[8]),2)+Math.pow(Math.abs(b[78]-b[127]),2);
//			//double mouth2=Math.pow(Math.abs(b[51]-b[8]),2)+Math.pow(Math.abs(b[84]-b[127]),2);
//			//double eyedistance=mouth1/mouth2;
//			//double eyepercent=Math.sqrt(eyedistance);
//			//double mouthdistance=mouth1/mouth2;
//			//double mouthpercent=Math.sqrt(mouthdistance);
//			System.out.println(b[46]);
//			System.out.println(b[51]);
//		}else{
//			System.out.println("fail");
//		}
//						
//	}
}
