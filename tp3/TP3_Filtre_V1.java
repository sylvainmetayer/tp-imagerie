package tp3;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class TP3_Filtre_V1 implements PlugInFilter {
	public  void  run(ImageProcessor  ip){
		int w = ip.getWidth();
		int h = ip.getHeight();
	
		ImagePlus result = NewImage.createByteImage("Filtrage v1", w, h, 1,NewImage.FILL_BLACK);
		ImageProcessor  ipR = result.getProcessor();

		int  [][]  masque = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
		int n = 1;
		int S;
		//  taille  du demi -masque

		//....

		for(int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				S = ip.getPixel(x-1, y-1) + ip.getPixel(x-1, y) + ip.getPixel(x-1, y+1)
				+ip.getPixel(x, y-1) + ip.getPixel(x, y) + ip.getPixel(x,  y+1)
				+ ip.getPixel(x+1, y-1) + ip.getPixel(x+1, y) + ip.getPixel(x+1, y+1);
				ipR.putPixel(x, y, S/9);
			}
		}

		result.show();
		result.updateAndDraw ();
	}
	
	@Override
	public int setup(String arg0, ImagePlus arg1) {
		if (arg0.equals("about")){
			IJ.showMessage("Filtre v1");
			return PlugInFilter.DONE;
		}
		return PlugInFilter.DOES_8G;
	}

}
