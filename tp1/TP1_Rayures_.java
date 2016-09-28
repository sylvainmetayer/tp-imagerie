package tp1;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class TP1_Rayures_ implements PlugInFilter{

	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();
		
		byte[] tpix = (byte[])ip.getPixels();
		
		w = ip.getWidth();
		h=ip.getHeight();
		
		/** 
		 * Préférable à la méthode ci-dessous 
		 */
		for(int i = 0; i < w*h;i++)
		{
			if ((i/w) % 2 == 0)
			{
				tpix[i] = (byte) 255;
			}
		}
		
		/*for(int y = 0; y<h;y=y+2)
		{
			for (int x=0;x < w;x++)
			{
				
				ip.putPixel(x, y, 255);
			}
		}*/
	}
	
	public int setup(String arg, ImagePlus imp) {
		if (arg.equals("about")){
			IJ.showMessage("Rayures blanches");
			return PlugInFilter.DONE;
		}
		return PlugInFilter.DOES_8G;
	}
}
