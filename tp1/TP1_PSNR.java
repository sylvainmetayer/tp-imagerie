package tp1;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

/**
 * Calculer le PSNR entre deux Image
 * 
 * @author smetayer
 *
 */
public class TP1_PSNR implements PlugInFilter {

	@Override
	public void run(ImageProcessor ip) {
		ImagePlus imgRef = new ImagePlus(""); // Le "" permet d'ouvrir un fileChooser
		ImageProcessor ipRef = imgRef.getProcessor();
		computePSNR(ip, ipRef);
	}
	
	/**
	 * Retourne le PSNR entre deux images.
	 * @param ip 
	 * @param ipRef 
	 * @return double
	 */
	double computePSNR(ImageProcessor ip, ImageProcessor ipRef) {
		int h = ip.getHeight();
		int w = ip.getWidth();
		int somme = 0;
		
		for(int y = 0; y<h;y++)
		{
			for (int x=0;x < w;x++)
			{
				double pixDiff = ip.getPixel(x, y) - ipRef.getPixel(x, y);
				somme += Math.sqrt(pixDiff); // différence de pixels au carré	
			}
		}
		
		double mse = (double)somme/ip.getPixelCount();
		
		double psnr = 10 * Math.log10(( ip.getMax() * ip.getMax() / mse));
		IJ.showMessage("PSNR : " + psnr) ; // Si résultat < 40, image compressée dégueulasse.
		return psnr;
	}

	@Override
	public int setup(String arg0, ImagePlus arg1) {
		if (arg0.equals("about")) {
			IJ.showMessage("PSNR");
			return PlugInFilter.DONE;
		}
			
		return PlugInFilter.DOES_8G;
	}
}
