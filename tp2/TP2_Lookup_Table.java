package tp2;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class TP2_Lookup_Table implements PlugInFilter {

	/**
	 * A faire / rendre
	 * Algo de base sur NG
	 * Avec LUT
	 * Avec applyTable
	 * Comment faire en RGB ?
	 */
	
	@Override
	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();
		
		int min = 0,max = 0;
		
		int[] LUT = new int[256];
		
		for (int y = 0; y<h; y++) {
			for (int x = 0; x<w;x++) {
				int pixel = ip.getPixel(x, y);
				min = pixel < min ? pixel : min;
				max = pixel > max ? pixel : max;
			}
		}
		
		// Initialisation de la LUT
		for (int ng = 0; ng < 256; ng++) {
			LUT[ng] = (255 * (ng -min) ) / max - min;  
		}
		
		// Calcul de la transformation
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				ip.putPixelValue(x, y, LUT[ip.getPixel(x, y)]);
			}
		}
	}

	@Override
	public int setup(String arg0, ImagePlus arg1) {
		if (arg0.equals("about")){
			IJ.showMessage("Image LUT");
			return PlugInFilter.DONE;
		}
		return PlugInFilter.DOES_8G;
	}
}
