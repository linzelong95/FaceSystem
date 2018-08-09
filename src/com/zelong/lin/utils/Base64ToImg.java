package com.zelong.lin.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Base64ToImg {
	public static BufferedImage Base64ToImage(String imgBase64){
		byte[] imageBytes=javax.xml.bind.DatatypeConverter.parseBase64Binary(imgBase64);
		BufferedImage img=null;
		try {
			img = ImageIO.read(new ByteArrayInputStream(imageBytes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
}
