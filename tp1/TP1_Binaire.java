package tp1;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class TP1_Binaire implements PlugInFilter {

	@Override
	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();
		
		byte[] tpix = (byte[])ip.getPixels();
		
		w = ip.getWidth();
		h=ip.getHeight();

		for(int i = 0; i < w*h;i++)
		{
			if (tpix[i] >= (byte)255/2)
				tpix[i] = (byte) 0; //noir
			else
				tpix[i] = (byte) 255; //blanc
		}
	}

	@Override
	public int setup(String arg0, ImagePlus arg1) {
		if (arg0.equals("about")) {
			IJ.showMessage("Binaire");
			return PlugInFilter.DONE;
		}
			
		return PlugInFilter.DOES_8G;
	}
}
