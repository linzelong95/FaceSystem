package test;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;


public class CutManyFace {
	static{
        // 载入opencv的库
    	System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
    }


    public void detectFace(Mat image,String path,String pathout) {
        //String xmlfilePath=getClass().getResource("lbpcascade_frontalface_improved.xml").getPath().substring(1);
        String xmlfilePath="E:/face68/lbpcascade_frontalface_improved.xml";
        //String xmlfilePath="E:/face68/haarcascade_frontalface_alt_tree.xml";
    	//String xmlfilePath="E:/face68/haarcascade_frontalface_alt.xml";
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
int num=0;
        //System.out.println(rects.length);
	        for (Rect rect : rects){    
//	        	Imgproc.rectangle(grayImage, new Point(rect.x, rect.y),
//	                    new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 255, 255));   
	        	String out=pathout+num+path;
	        	Mat roi_img = new Mat(grayImage,rect); 
	        	Imgcodecs.imwrite(out,roi_img);
	        	num++;
	        } 
	        

	    	//roi_img.copyTo(tmp_img);
	        //return grayImage;
      
 
    }  
}
