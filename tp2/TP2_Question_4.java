package tp2;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class TP2_Question_4 implements PlugInFilter {
	
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
				int r = (niveau & 0x00ff00) >> 16;
				int g = (niveau & 0x00ff00) >> 8;
				int b = (niveau & 0x0000ff);
				double intensiteImage = 0.3 * r + 0.59*g + 0.11 * b;
				histogram[(int) intensiteImage]++; // Cette couleur a été detectée une fois. 
			}
		}
		
		int cumul =0;
		
		int[] lut = new int[histogram.length];
		
		// Histogramme cumulé
		for (int i = 0; i < histogram.length; i++) {
			cumul += histogram[i];
			lut[i] = cumul*255 / N; 
		}
		
		// Application de la formule du cours
		for (int x = 0; x < w; x++ ) {
			for (int y = 0; y < h; y++) {
				int pixel = ip.getPixel(x, y);
				int r = (pixel & 0x00ff00) >> 16;
				int g = (pixel & 0x00ff00) >> 8;
				int b = (pixel & 0x0000ff);
				int nR = lut[r];
				int nG = lut[g];
				int nB = lut[b];
				int[] values = {nR,nG,nB};
				ip.putPixel(x, y, values);
			}
		}
	}

	@Override
	public int setup(String arg0, ImagePlus arg1) {
		if (arg0.equals("about")){
			IJ.showMessage("TP2 Question 4 Histogramme Couleur");
			return PlugInFilter.DONE;
		}
		return PlugInFilter.DOES_RGB;
	}
}