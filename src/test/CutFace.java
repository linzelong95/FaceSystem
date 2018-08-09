package test;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;


public class CutFace {
	static{
        // 载入opencv的库
    	System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
    }

	public double calcArea(Rect rect){  
        return rect.width*rect.height;  
    } 
    public Mat detectFace(Mat image) {
        //String xmlfilePath=getClass().getResource("lbpcascade_frontalface_improved.xml").getPath().substring(1);
        //String xmlfilePath="E:/face68/lbpcascade_frontalface_improved.xml";
        //String xmlfilePath="E:/face68/haarcascade_frontalface_alt_tree.xml";
    	String xmlfilePath="E:/face68/haarcascade_frontalface_alt.xml";
    	//String xmlfilePath="E:/face68/cascade.xml";
        CascadeClassifier faceDetector = new CascadeClassifier(xmlfilePath);      
        //图像缩放，线性
        Mat resizeImage = new Mat();
        if(image.width()>1000 && image.height()>1000){
        	Imgproc.resize(image, resizeImage, new Size(image.cols()/2,image.rows()/2), 0, 0, Imgproc.INTER_LINEAR);
        }else{
        	resizeImage=image;
        }
        
        Mat grayImage = new Mat();
	    Imgproc.cvtColor(resizeImage, grayImage, Imgproc.COLOR_RGB2GRAY);
//	    Mat afterImage = new Mat();
//	    List<Mat> mv = new ArrayList<Mat>();
//	    Core.split(grayImage, mv);
//	    for (int i = 0; i < grayImage.channels(); i++){
//	      Imgproc.equalizeHist(mv.get(i), mv.get(i));
//	    }
//	    Core.merge(mv, afterImage); 
	    // 在图片中检测人脸
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(grayImage, faceDetections);
        Rect[] rects = faceDetections.toArray();
        Rect maxRect=new Rect(0,0,0,0);
        //System.out.println(rects.length);
	        for (Rect rect : rects){    
//	        	Imgproc.rectangle(grayImage, new Point(rect.x, rect.y),
//	                    new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 255, 255));   
	        	if(calcArea(maxRect)<calcArea(rect))  {  
	                maxRect=rect;  
	            }
	        } 
	        
	        //更改方框大小
//	        double d=0.65;
//	        int w=(int)(maxRect.width*d);
//	        int h=(int)(maxRect.height*(d+0.05));
//	        int xx=(int)(maxRect.x*0.98+(maxRect.width-w)*1.0/2);
//	        int yy=(int)(maxRect.y*1.08+(maxRect.height-h)*1.0/2);
//	        maxRect=new Rect(xx,yy,w,h);
	        
	        //double mark_d=1.2;
	        double mark_d=2.0;
	        int mark_w=(int)(maxRect.width*mark_d);
	        int mark_h=(int)(maxRect.height*(mark_d+0.3));
	        int mark_xx=(int)(maxRect.x-(mark_w-maxRect.width)*1.0/2);	        
	        int mark_yy=(int)(maxRect.y-(mark_h-maxRect.height)*1.0/2);
	        mark_w=(mark_w<grayImage.width())?mark_w:grayImage.width();
	        mark_h=(mark_h<grayImage.height())?mark_h:grayImage.height();
	        mark_xx=(mark_w+mark_xx>grayImage.width())?0:(mark_xx>0)?mark_xx:0;
	        mark_yy=(mark_h+mark_yy>grayImage.height())?0:(mark_yy>0)?mark_yy:0;
	        maxRect=new Rect(mark_xx,mark_yy,mark_w,mark_h);
	        
	        
	        Mat roi_img = new Mat(grayImage,maxRect); 
	        return roi_img;
	    	//roi_img.copyTo(tmp_img);
	        //return grayImage;
      
 
    }  
}
