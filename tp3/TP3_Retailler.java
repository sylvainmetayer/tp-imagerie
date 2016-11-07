package tp3;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;


public class TP3_Retailler implements PlugInFilter {
	
	public void run(ImageProcessor ip) {
		
	//  dialogue  permettant  de fixer la  valeur  du  facteur d’echelle
		
		GenericDialog gd = new GenericDialog( "Facteur d’echelle", IJ.getInstance());
		
		gd.addSlider( "facteur", 0.0, 10.0, 2.0 );
		gd.showDialog();
		if ( gd.wasCanceled() ) {
			IJ.error( "PlugIn  cancelled" );
			return;
		}
		
		double ratio = (double)gd.getNextNumber();
		
		int w = ip.getWidth();
		int h = ip.getHeight();
		
		int _w = (int) ratio * w;
		int _h = (int) ratio * h;

		
		ImagePlus result = NewImage.createByteImage
		("Retailler" ,_w,_h, 1, NewImage.FILL_BLACK);

		ImageProcessor ipr = result.getProcessor();

		for(int y = 0; y < _h; y++ ) {
			for(int x = 0; x < _w; x++) {
				int xx = (int)(x/ratio);
				int yy = (int)(y/ratio);
				ipr.putPixel(x, y, ip.getPixel(xx, yy));
			}
		}

		result.show();
		result.updateAndDraw();
	}
	
	@Override
	public int setup(String arg0, ImagePlus arg1) {
		if (arg0.equals("about")){
			IJ.showMessage("Retailler");
			return PlugInFilter.DONE;
		}
		return PlugInFilter.DOES_8G;
	}

}
