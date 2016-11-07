package tp2;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class TP2_Q3 implements PlugInFilter{
	

	@Override
	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();
		int pixel;
		
		int cumul = 0;
		
		int[] histo = ip.getHistogram();
		
		for(int i = 0; i < histo.length; i++) {
			if(histo[i] > 0) {
				cumul += histo[i];
				histo[i] = cumul;
			}
		}
		
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				pixel = ip.getPixel(x, y);
				ip.putPixel(x, y, pixel);
				
				
			}
		}
	}
		
		
		@Override
		public int setup(String arg0, ImagePlus arg1) {
			if (arg0.equals("about")) {
				IJ.showMessage("Question3");
				return PlugInFilter.DONE;
			}
				
			return PlugInFilter.DOES_8G;
		}
}
