package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String path="D:/pictest/cutFace/";
		String pathout="D:/pictest/cut_face/";
		File file=new File(path);
		File[] fs=file.listFiles();
		for(int i=0;i<fs.length;i++){
			String out=pathout+i+".jpg";
			String imgPath=path+fs[i].getName();
			BufferedImage buf=ImageIO.read(new File(imgPath));
			ImageIO.write(buf, "jpg", new File(out));
		}
		
	}

}
