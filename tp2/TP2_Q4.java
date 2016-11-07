package tp2;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class TP2_Q4 implements PlugInFilter {
	
	@Override
	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();
		int min = 0;
		int max = 0;
		int pixel;
		
		w = ip.getWidth();
		h=ip.getHeight();
		
		
		for(int x = 0; x < w; x++) {
			for(int y =0; y < h; y++) {
				pixel = ip.getPixel(x, y);
				min = pixel < min ? pixel : min;
				max = pixel > max ? pixel : max;
			}
		}
		
		// Calcul de la transformation
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				double pixelValue = ((255 * (ip.getPixel(i, j)- min )) /  max - min);
				ip.putPixelValue(i, j, pixelValue);
			}
		}

		}


	@Override
	public int setup(String arg0, ImagePlus arg1) {
		if (arg0.equals("about")) {
			IJ.showMessage("Histo couleur");
			return PlugInFilter.DONE;
		}
			
		return PlugInFilter.DOES_8G;
	}

}
