package test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

public class ImgPrint {
	private static final int HASH_SIZE=16;
	public byte[] getImgPrint(BufferedImage src){
		//归一化为16*16
		BufferedImage hashImage = new BufferedImage(HASH_SIZE, HASH_SIZE,  
                BufferedImage.TYPE_3BYTE_BGR); 
         Graphics g = hashImage.getGraphics();
         try{
             g.drawImage(src.getScaledInstance(HASH_SIZE, HASH_SIZE, Image.SCALE_SMOOTH), 0, 0, null);
         }finally{
             g.dispose();
         }
         
         //图像灰度化
         BufferedImage grayImage;
         if(hashImage.getType()==BufferedImage.TYPE_BYTE_GRAY){
        	 grayImage=hashImage;
         }else{
             // 图像转灰
             grayImage = new BufferedImage(hashImage.getWidth(), hashImage.getHeight(),  
                     BufferedImage.TYPE_BYTE_GRAY);
             new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null).filter(hashImage, grayImage);     
         }
         
        byte[] matrixGray = (byte[]) grayImage.getData().getDataElements(0, 0, HASH_SIZE, HASH_SIZE, null); 
        
        //图像二值化
        byte[] dst = matrixGray.clone();
        long sum=0;
        for(byte b:matrixGray)sum+=(long)b&0xff;// 将数组元素转为无符号整数
        int mean= (int) (Math.round((float)sum/matrixGray.length));
        for(int i=0;i<dst.length;i++){
            // 将数组元素转为无符号整数再比较
            dst[i]=(byte) (((int)dst[i]&0xff)>=mean?1:0);
        }
		return dst;
	}
	
	public double jugeImg(BufferedImage buf,BufferedImage buf2){
		int sameCount=0;
		byte[] waitCompare=getImgPrint(buf);
		byte[] fromSql=getImgPrint(buf2);
		if(waitCompare.length==fromSql.length){
			for(int i=0;i<fromSql.length;i++){
	            if(waitCompare[i]==fromSql[i]){
	            	sameCount++;
	            }
	        }
		}
		return sameCount*1.0/fromSql.length;
	}
}
