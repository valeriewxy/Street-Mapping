/*
 * Name: Xuanyang Wang, Kehao Guo
 * NetID: xwang145, kguo
 * Project 4
 */

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessor {

	public static Image loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch (IOException e) { //handles the IOException
			System.err.println(e);
			return null;
		}
	}

}