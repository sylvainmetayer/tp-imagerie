package tp2;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class TP2_Lookup_Table_Basique implements PlugInFilter {

	
	@Override
	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();
		
		int min = 0,max = 0;
				
		for (int y = 0; y<h; y++) {
			for (int x = 0; x<w;x++) {
				int pixel = ip.getPixel(x, y);
				min = pixel < min ? pixel : min;
				max = pixel > max ? pixel : max;
			}
		}
		
		for(int y = 0; y<h;y++)
		{
			for (int x=0;x < w;x++)
			{
				double pixelValue = ((255 * (ip.getPixel(x, y)- min )) /  max - min);
				ip.putPixelValue(x, y, pixelValue);
			}
		}
		
	}

	@Override
	public int setup(String arg0, ImagePlus arg1) {
		if (arg0.equals("about")){
			IJ.showMessage("Image LUT Basique");
			return PlugInFilter.DONE;
		}
		return PlugInFilter.DOES_8G;
	}
}
