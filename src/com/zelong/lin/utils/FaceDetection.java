package com.zelong.lin.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;



public class FaceDetection {
	static{
        // 载入opencv的库
    	System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
    }
    public static double calcArea(Rect rect){  
        return rect.width*rect.height;  
    } 

    public ArrayList<Mat> detectFace(Mat image) {
        //String xmlfilePath=getClass().getResource("lbpcascade_frontalface_improved.xml").getPath().substring(1);
    	String xmlfilePath="E:/face68/haarcascade_frontalface_alt.xml";
        CascadeClassifier faceDetector = new CascadeClassifier(xmlfilePath);   
        ArrayList<Mat> list=new ArrayList<Mat>();
        //图像缩放，线性
        Mat resizeImage = new Mat();
        if(image.width()>1000 && image.height()>1000){
        	Imgproc.resize(image, resizeImage, new Size(image.cols()/2,image.rows()/2), 0, 0, Imgproc.INTER_LINEAR);
        }else{
        	resizeImage=image;
        }
	    //图像灰度化,image为原图像，afterImage为灰度化图片
	    Mat grayImage = new Mat();
	    Imgproc.cvtColor(resizeImage, grayImage, Imgproc.COLOR_RGB2GRAY);
	   //直方图均衡化，图像增强，暗的变亮，亮的变暗。
	   Mat afterImage=grayImage;//使用均衡化则删掉
//	    Mat afterImage = new Mat();
//	    List<Mat> mv = new ArrayList<Mat>();
//	    Core.split(grayImage, mv);
//	    for (int i = 0; i < grayImage.channels(); i++){
//	      Imgproc.equalizeHist(mv.get(i), mv.get(i));
//	    }
//	    Core.merge(mv, afterImage); 
	    // 在图片中检测人脸
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(afterImage, faceDetections);
        Rect[] rects = faceDetections.toArray();
        if(rects.length>0){
        	Rect maxRect=new Rect(0,0,0,0);
	        for (Rect rect : rects){    
	            if(calcArea(maxRect)<calcArea(rect))  {  
	                maxRect=rect;  
	            }    
	        } 
	        
	      //缩小截取方框,这样截取的区域只包含人脸主要的部位，与lbpcascade_frontalface_improved.xml差不多
	        double d=0.65;
	        int w=(int)(maxRect.width*d);
	        int h=(int)(maxRect.height*d);
	        //效果好点
	        //int h=(int)(maxRect.height*(d+0.05));
	        int xx=(int)(maxRect.x*0.98+(maxRect.width-w)*1.0/2);
	        int yy=(int)(maxRect.y*1.08+(maxRect.height-h)*1.0/2);
	        Rect new_maxRect=new Rect(xx,yy,w,h);
	        
	      //扩大截取方框,这样截取的区域包含完整的人脸，用于人脸关键点定位，二次校验
	        //double mark_d=1.2;
	        double mark_d=1.5;
	        int mark_w=(int)(maxRect.width*mark_d);
	        int mark_h=(int)(maxRect.height*(mark_d+0.3));
	        int mark_xx=(int)(maxRect.x-(mark_w-maxRect.width)*1.0/2);	        
	        int mark_yy=(int)(maxRect.y-(mark_h-maxRect.height)*1.0/2);
	        mark_w=(mark_w<grayImage.width())?mark_w:grayImage.width();
	        mark_h=(mark_h<grayImage.height())?mark_h:grayImage.height();
	        mark_xx=(mark_w+mark_xx>grayImage.width())?0:(mark_xx>0)?mark_xx:0;
	        mark_yy=(mark_h+mark_yy>grayImage.height())?0:(mark_yy>0)?mark_yy:0;
	        Rect mark_maxRect=new Rect(mark_xx,mark_yy,mark_w,mark_h);
	        
	        Mat roi_img = new Mat(afterImage,new_maxRect); 
	    	Mat tmp_img = new Mat(); 
	    	Imgproc.resize(roi_img, tmp_img,new Size(50,50));//将人脸进行截图并保存
	    	//roi_img.copyTo(tmp_img);
	    	
	    	Mat mark_tmp_img = new Mat(image,mark_maxRect); 
	    	list.add(tmp_img);
	    	list.add(mark_tmp_img);
	        return list;
      } else{
//    	  	String modelPath=getClass().getResource("face.model").getPath().substring(1);
//	    	FaceNetwork fn = new FaceNetwork();
//	  		fn.loadModel(modelPath);
//	  		ScanImagePyramid sip = new ScanImagePyramid(afterImage,fn);
//			sip.setScaleStep(0.8f);
//			sip.setMinFaceSize(100);
//			sip.setMaxFaceSize(250);
//			sip.setScanStep(4);
//			sip.setFaceThreshod(0.6f);
//			Vector<FaceInfo> faces = sip.startScan();
//			//System.out.println(faces.size());
//			//System.out.println("facetector finished,get "+faces.size()+" faces ");
//			if(faces.size()>0){
//				FaceInfo maxFace=faces.firstElement();
//				//Rect rect2=new Rect(maxFace.getStartX(),maxFace.getStartY(),maxFace.getStopX(),maxFace.getStopY());
//				
//				Mat roi_img2 = afterImage.submat(maxFace.getStartY(),maxFace.getStopY(),maxFace.getStartX(),maxFace.getStopX());
//				
//				Mat tmp_img2 = new Mat();
//				Imgproc.resize(roi_img2, tmp_img2,new Size(50,50));
//				return tmp_img2;
    	  
	    	  //String xmlfilePath_1=getClass().getResource("haarcascade_frontalface_alt_tree.xml").getPath().substring(1);
//    	  		String xmlfilePath_1="E:/face68/lbpcascade_frontalface_improved.xml";
//	          CascadeClassifier faceDetector_1 = new CascadeClassifier(xmlfilePath_1);
//	          // 在图片中检测人脸
//	          MatOfRect faceDetections_1 = new MatOfRect();
//	          faceDetector_1.detectMultiScale(afterImage, faceDetections_1);
//	          Rect[] rects_1 = faceDetections_1.toArray();
//	          if(rects_1.length>0){
//	          	Rect maxRect_1=new Rect(0,0,0,0);
//	  	        for (Rect rect_1 : rects_1){    
//	  	            if(calcArea(maxRect_1)<calcArea(rect_1))  {  
//	  	                maxRect_1=rect_1;  
//	  	            }    
//	  	        } 
//	  	        Mat roi_img_1 = new Mat(afterImage,maxRect_1); 
//	  	    	Mat tmp_img_1 = new Mat(); 
//	  	    	Imgproc.resize(roi_img_1, tmp_img_1,new Size(50,50));//将人脸进行截图并保存
//	  	    	//roi_img.copyTo(tmp_img);
//	  	        return tmp_img_1;	       
//			}else{
//				return null;
//			}
    	  return null;
      }     
    }  
}
