package com.zelong.lin.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class MatToBufImg {
	static{
	      // ‘ÿ»Îopencvµƒø‚
	  	System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	    }

    public static BufferedImage getImage(Mat amatrix) {
    	if(amatrix!=null){
            BufferedImage bufImage = null;
            try {
	            	MatOfByte mob = new MatOfByte();
	        		Imgcodecs.imencode(".png", amatrix, mob);
	                byte[] byteArray = mob.toArray();
            		InputStream in = new ByteArrayInputStream(byteArray);
                    bufImage = ImageIO.read(in);
            } catch (Exception e) {
                    e.printStackTrace();
                    return null;
            }
            	return bufImage;
    	}else{
    		return null;
    	}
    }
}
