package tp3;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class TP3_Filtre_V2 implements PlugInFilter {
	public  void  run(ImageProcessor  ip){
		int w = ip.getWidth();
		int h = ip.getHeight();
	
		ImagePlus result = NewImage.createByteImage("Filtrage v2", w, h, 1,NewImage.FILL_BLACK);
		ImageProcessor  ipR = result.getProcessor();

		int  [][]  masque = {{1, 1, 1,1,1}, {1, 1, 1,1,1}, {1, 1, 1,1,1},{1, 1, 1,1,1},{1, 1, 1,1,1}};
		int n = 2; 
		
		int somme_coef = 0;
		
		for (int u = -n;u<=n;u++)
			for (int v=-n;v<=n;v++)
				somme_coef += masque[u+n][v+n];

		for(int y = n; y<h-n;y++) {
			for (int x = n;x<w-n;x++) {
				int s =0;
				for (int u=-n;u<=n;u++)
					for (int v=-n;v<=n;v++)
						s+= ip.getPixel(x+u, v+y) * masque[u+n][v+n];
				
				ipR.putPixel(x,y,s/somme_coef);
			}
		}

		result.show();
		result.updateAndDraw ();
	}
	
	@Override
	public int setup(String arg0, ImagePlus arg1) {
		if (arg0.equals("about")){
			IJ.showMessage("Filtre v2");
			return PlugInFilter.DONE;
		}
		return PlugInFilter.DOES_8G;
	}

}
