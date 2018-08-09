package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FaceMatch{
    public static void main(String[] args) throws IOException{
    	String path="E:/111/";
    	ImgPrint ip=new ImgPrint();
//    	double yuzhi=0.82;
		File file=new File(path);
		File[] fs=file.listFiles();
//		int noMatchNum=0;
//		int total=0;
//		int errorMatchNum=0;
		System.out.println("程序运行中，请等待。。。");
		for(int i=0;i<fs.length;i++){
			for(int j=i+1;j<fs.length;j++){

//				total+=1;
				String imgPathRow=path+fs[i].getName();
				String imgPathColn=path+fs[j].getName();
				BufferedImage bufRow=ImageIO.read(new File(imgPathRow));
				BufferedImage bufColn=ImageIO.read(new File(imgPathColn));
				double d=ip.jugeImg(bufRow, bufColn);
				
				System.out.println(fs[i].getName()+"-"+fs[j].getName()+":   "+d);
				
//				if(d<yuzhi){
//					noMatchNum+=1;
//				}else{
//					errorMatchNum+=1;
//				}
			}
			
		}
//		double noMatchRate=noMatchNum*1.0/total;
//		double errorMatchRate=errorMatchNum*1.0/total;
//		System.out.println("总数："+total);
//		System.out.println("不匹配数"+noMatchNum);
//		System.out.println("误匹配数"+errorMatchNum);
//		System.out.println("不匹配率"+noMatchRate);
//		System.out.println("误匹配率"+errorMatchRate);
    	

    }
    
}
