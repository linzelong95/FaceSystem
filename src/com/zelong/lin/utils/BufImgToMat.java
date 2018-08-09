package com.zelong.lin.utils;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;


public class BufImgToMat {
	static{ 
	  	System.loadLibrary( Core.NATIVE_LIBRARY_NAME );// ‘ÿ»Îopencvµƒø‚
	    }
    public static Mat getMat(BufferedImage in) throws IOException {
       if (in == null) {
              throw new IllegalArgumentException("image == null");
       }
       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
       ImageIO.write(in, "png", byteArrayOutputStream);
       byteArrayOutputStream.flush();
       return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);   
    }
}