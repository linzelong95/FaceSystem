package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sub{
    public static void main(String[] args) throws IOException{
    	ImgPrint ip=new ImgPrint();
    	BufferedImage buf=ImageIO.read(new File("E:/FaceOfUser/林泽龙5.png"));
    	BufferedImage buf1=ImageIO.read(new File("E:/FaceOfUser/林泽龙.png"));
    	BufferedImage buf2=ImageIO.read(new File("E:/FaceOfUser/林泽龙2.png"));
    	BufferedImage buf3=ImageIO.read(new File("E:/FaceOfUser/林泽龙3.png"));
    	BufferedImage buf4=ImageIO.read(new File("E:/FaceOfUser/林泽龙4.png"));
    	BufferedImage buf5=ImageIO.read(new File("E:/FaceOfUser/林泽龙5.png"));
    	double d1=ip.jugeImg(buf, buf1);
    	double d2=ip.jugeImg(buf, buf2);
    	double d3=ip.jugeImg(buf, buf3);
    	double d4=ip.jugeImg(buf, buf4);
    	double d5=ip.jugeImg(buf, buf5);
    	
    	System.out.println(d1);
    	System.out.println(d2);
    	System.out.println(d3);
    	System.out.println(d4);
    	System.out.println(d5);
    }
    
}
