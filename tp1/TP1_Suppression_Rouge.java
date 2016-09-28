package tp1;
import java.awt.Color;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.NewImage;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class TP1_Suppression_Rouge implements PlugInFilter {

	@Override
	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();
		
		ImagePlus res = NewImage.createRGBImage(
				"Sans Rouge", 
				w, 
				h, 
				1, 
				NewImage.FILL_BLACK);
		ImageProcessor ipRes = res.getProcessor();
		
		int[] pixels = (int[]) ip.getPixels();
		int[] pixelsRes = (int[]) ipRes.getPixels();
		
		for (int i = 0; i<w*h; i++) {
			int r = 0;
			int g = (pixels[i] & 0x00ff00) >> 8;
			int b = (pixels[i] & 0x0000ff);
			Color c = new Color(r, g, b);
			pixelsRes[i] = c.getRGB();
		}
		
		res.show();
		res.updateAndDraw();
	}

	@Override
	public int setup(String arg0, ImagePlus arg1) {
		if (arg0.equals("about")){
			IJ.showMessage("Suppression Rouge");
			return PlugInFilter.DONE;
		}
		return PlugInFilter.DOES_RGB;
	}
}
