package test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import com.zelong.lin.utils.MatToBufImg;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class Base64Test {  
	static{
        // ����opencv�Ŀ�
    	System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
   }
    public static void main(String[] args) throws IOException  
    {  
    	
    	
    	String s=new Base64Test().GetImageStr();
    	System.out.print(s);
    	
    }  
    //ͼƬת����base64�ַ���  
    public String GetImageStr()  
    {//��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��  
        String imgFile = "D:\\888.jpg";//��������ͼƬ  
        InputStream in = null;  
        byte[] data = null;  
        //��ȡͼƬ�ֽ�����  
        try   
        {  
            in = new FileInputStream(imgFile);
            int count=0;
            while(count==0){
            	count=in.available();
            }
            data = new byte[count];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //���ֽ�����Base64����  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);//����Base64��������ֽ������ַ���  
    }  
      
    //base64�ַ���ת����ͼƬ  
    public boolean GenerateImage(String imgStr)  
    {   //���ֽ������ַ�������Base64���벢����ͼƬ  
        if (imgStr == null) //ͼ������Ϊ��  
            return false;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64����  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//�����쳣����  
                    b[i]+=256;  
                }  
            }  
            //����jpegͼƬ  
            String imgFilePath = "D:\\newnew.png";//�����ɵ�ͼƬ  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }  
    public void Base64ToImage(String imgBase64) throws IOException{
    	//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		byte[] imageBytes=javax.xml.bind.DatatypeConverter.parseBase64Binary(imgBase64);
    	BufferedImage img=ImageIO.read(new ByteArrayInputStream(imageBytes));
//    	File outputfile=new File("D:\\new.png");
//    	ImageIO.write(img, "png", outputfile);
    	Mat mat_img=BufImgToMat.getMat(img);
    	//Imgcodecs.imwrite("E:\\test.jpg", mat_img);
    	//return mat_img;
    	System.out.println("ִ�гɹ�");
    	BufferedImage buf_img=MatToBufImg.getImage(mat_img);
    	ImageIO.write(buf_img,"png", new File("E:\\test.png"));
    	
    }
}  