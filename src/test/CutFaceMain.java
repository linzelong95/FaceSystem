package test;


import java.io.File;
import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class CutFaceMain {
	static{
	      // ‘ÿ»Îopencvµƒø‚
	  	System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	    }
	public static void main(String[] args) throws IOException{

//		
//		Mat suoImage = new Mat();
//        Imgproc.resize(image, suoImage, new Size(image.cols()/4,image.rows()/4), 0, 0, Imgproc.INTER_LINEAR);
//        Imgproc.resize(image, kuoImage, new Size(image.cols()*2,image.rows()*2), 0, 0, Imgproc.INTER_LINEAR);
//        

		String path="D:/pictest/test3/";
		String pathout="D:/pictest/test4/";
		File file=new File(path);
		File[] fs=file.listFiles();
		for(int i=0;i<fs.length;i++){
			//String out=pathout+i+".jpg";
			String out=pathout+fs[i].getName();
			String imgPath=path+fs[i].getName();
//			BufferedImage buf=ImageIO.read(new File(imgPath));
//			ImageIO.write(buf, "jpg", new File(out));
			Mat image=Imgcodecs.imread(imgPath);
			Mat img=new CutFace().detectFace(image);
		    Imgcodecs.imwrite(out,img);
		}

		
		
//		Mat grayImage = new Mat();
//		Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_RGB2GRAY);
//		Imgcodecs.imwrite("D:/picresult/lzltest_huiduhua.jpg", grayImage);
//	    Mat afterImage = new Mat();
//	    List<Mat> mv = new ArrayList<Mat>();
//	    Core.split(grayImage, mv);
//	    for (int i = 0; i < grayImage.channels(); i++){
//	      Imgproc.equalizeHist(mv.get(i), mv.get(i));
//	    }
//	    Core.merge(mv, afterImage); 
//	    Imgcodecs.imwrite("D:/picresult/lzltest_zhifang.jpg", afterImage);
		
		
	}	
}

