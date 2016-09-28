package tp1;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.NewImage;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class TP1_Image_Grey implements PlugInFilter {

	@Override
	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();
		
		ImagePlus res = NewImage.createByteImage(
				"Image Grisée", 
				w, 
				h, 
				1, 
				NewImage.FILL_BLACK);
		ImageProcessor ipRes = res.getProcessor();
		
		for(int y = 0; y<h;y++)
		{
			for (int x=0;x < w;x++)
			{
				int pixel = ip.getPixel(x, y);
				int r = (pixel & 0x00ff00) >> 16;
				int g = (pixel & 0x00ff00) >> 8;
				int b = (pixel & 0x0000ff);			
				int greyColor = (int) (0.3 * r + 0.59 * g + 0.11 * b);
				ipRes.putPixel(x, y, greyColor);
			}
		}
		
		res.show();
		res.updateAndDraw();
	}

	@Override
	public int setup(String arg0, ImagePlus arg1) {
		if (arg0.equals("about")){
			IJ.showMessage("Image grisée");
			return PlugInFilter.DONE;
		}
		return PlugInFilter.DOES_RGB;
	}
}
