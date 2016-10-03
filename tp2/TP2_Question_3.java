package tp2;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class TP2_Question_3 implements PlugInFilter {
	
	@Override
	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();
		int N = w *h;
		
		// Histogramme initial.
		int[] histogram = new int[256];
		
		//int[] histogramme = ip.getHistogram(); // Fonctionnel
		
		for (int x = 0; x < w; x++ ) {
			for (int y = 0; y < h; y++) {
				int niveau = ip.getPixel(x, y);
				histogram[niveau]++; // Cette couleur a été detectée une fois. 
			}
		}
		
		int cumul =0;
		
		// Histogramme cumulé
		for (int i = 0; i < histogram.length; i++) {
			if (histogram[i] > 0) {
				cumul += histogram[i];
				histogram[i] = cumul*255 / N; 
			}
		}
		
		// Application de la formule du cours
		for (int x = 0; x < w; x++ ) {
			for (int y = 0; y < h; y++) {
				int pixel = ip.getPixel(x, y);
				ip.putPixel(x, y, histogram[pixel]);
			}
		}
	}

	@Override
	public int setup(String arg0, ImagePlus arg1) {
		if (arg0.equals("about")){
			IJ.showMessage("TP2 Question 3 Histogramme");
			return PlugInFilter.DONE;
		}
		return PlugInFilter.DOES_8G;
	}
}