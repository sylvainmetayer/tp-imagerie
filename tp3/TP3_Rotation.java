package tp3;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class TP3_Rotation implements PlugInFilter{
	
	public void run(ImageProcessor ip) {
		
GenericDialog gd = new GenericDialog( "Facteur d’echelle", IJ.getInstance());
		
		gd.addSlider( "facteur", 0.0, 10.0, 2.0 );
		gd.showDialog();
		if ( gd.wasCanceled() ) {
			IJ.error( "PlugIn  cancelled" );
			return;
		}
		
		double angle = (double)gd.getNextNumber();
		
		int w = ip.getWidth();
		int h = ip.getHeight();

		
		ImagePlus result = NewImage.createByteImage
		("Rotation" ,w,h, 1, NewImage.FILL_BLACK);
		ImageProcessor ipr = result.getProcessor();
		
		int dl = w/2;
		int dh = h/2;
		angle = Math.toRadians(angle);
		
		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				double xx = ((x - dl) * Math.cos(angle) - (y - dh) * Math.sin(angle) + dl);
				double yy = ((x - dl) * Math.sin(angle) + (y - dh) * Math.cos(angle) + dl);
				
				if((xx >= 0) && (xx < w) && (yy > 0) && (yy < h)) {
					ipr.putPixel( x, y, (int) ip.getInterpolatedPixel( xx, yy));
					//ipr.putPixel(x, y, ip.getPixel((int) xx, (int) yy));
				}
			}
		}
		
		
		result.show();
		result.updateAndDraw();
		
	}
	
	@Override
	public int setup(String arg0, ImagePlus arg1) {
		if (arg0.equals("about")){
			IJ.showMessage("Rotation d'image");
			return PlugInFilter.DONE;
		}
		return PlugInFilter.DOES_8G;
	}

}
