package test;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.util.Arrays;

/**
 * 均值哈希实现图像指纹比较
 * @author guyadong
 *
 */
public final class FingerPrint {
    /**
     * 图像指纹的尺寸,将图像resize到指定的尺寸，来计算哈希数组 
     */
    private static final int HASH_SIZE=16;
    /**
     * 保存图像指纹的二值化矩阵
     */
    private final byte[] binaryzationMatrix;
    public FingerPrint (BufferedImage src){
        this(hashValue(src));
    }
    /**
     * 保存图像指纹的二值化矩阵
     */

    public FingerPrint(byte[] hashValue) {
        if(hashValue.length!=HASH_SIZE*HASH_SIZE)
            throw new IllegalArgumentException(String.format("length of hashValue must be %d",HASH_SIZE*HASH_SIZE ));
        this.binaryzationMatrix=hashValue;
    }
private static byte[] hashValue(BufferedImage src){
        BufferedImage hashImage = resize(src,HASH_SIZE,HASH_SIZE);
        byte[] matrixGray = (byte[]) toGray(hashImage).getData().getDataElements(0, 0, HASH_SIZE, HASH_SIZE, null); 
        return  binaryzation(matrixGray);
    }
    private static BufferedImage resize(Image src,int width,int height){
        BufferedImage result = new BufferedImage(width, height,  
                BufferedImage.TYPE_3BYTE_BGR); 
         Graphics g = result.getGraphics();
         try{
             g.drawImage(src.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
         }finally{
             g.dispose();
         }
        return result;      
    }
    private static BufferedImage toGray(BufferedImage src){
        if(src.getType()==BufferedImage.TYPE_BYTE_GRAY){
            return src;
        }else{
            // 图像转灰
            BufferedImage grayImage = new BufferedImage(src.getWidth(), src.getHeight(),  
                    BufferedImage.TYPE_BYTE_GRAY);
            new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null).filter(src, grayImage);
            return grayImage;       
        }
    }
    	//二值化
	    private static byte[] binaryzation(byte[]src){
        byte[] dst = src.clone();
        int mean=mean(src);
        for(int i=0;i<dst.length;++i){
            // 将数组元素转为无符号整数再比较
            dst[i]=(byte) (((int)dst[i]&0xff)>=mean?1:0);
        }
        return dst;

    }
	  private static  int mean(byte[] src){
        long sum=0;
        // 将数组元素转为无符号整数
        for(byte b:src)sum+=(long)b&0xff;
        return (int) (Math.round((float)sum/src.length));
    }
	/**
     * 比较指纹相似度
     * @param src
     * @return 
     * @see #compare(byte[], byte[])
     */
    public float compare(FingerPrint src){
        if(src.binaryzationMatrix.length!=this.binaryzationMatrix.length)
            throw new IllegalArgumentException("length of hashValue is mismatch");
        return compare(binaryzationMatrix,src.binaryzationMatrix);
    }
	/**
     * 判断两个数组相似度，数组长度必须一致否则抛出异常
     * @param f1
     * @param f2
     * @return 返回相似度(0.0~1.0)
     */
    private static float compare(byte[] f1,byte[] f2){
        if(f1.length!=f2.length)
            throw new IllegalArgumentException("mismatch FingerPrint length");
        int sameCount=0;
        for(int i=0;i<f1.length;++i){
            if(f1[i]==f2[i])++sameCount;
        }
        return (float)sameCount/f1.length;
    }
	
	
}