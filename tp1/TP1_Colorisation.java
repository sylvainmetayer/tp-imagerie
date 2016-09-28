package tp1;
import java.awt.Color;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.ColorChooser;
import ij.gui.NewImage;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class TP1_Colorisation implements PlugInFilter {

	@Override
	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();
		
		float hue, saturation, brightness;
		
		ImagePlus res = NewImage.createRGBImage(
				"Colorisation", 
				w, 
				h, 
				1, 
				NewImage.FILL_BLACK);
		ImageProcessor ipRes = res.getProcessor();
		
		ColorChooser cc = new ColorChooser("Choisir la couleur prédominante", Color.BLUE, true);
		Color cRef = cc.getColor();
		
		if (cRef == null) {
			IJ.error("Annulation");
			return;
		}
		
		float[] hsbUserColor = null; 
		hsbUserColor = Color.RGBtoHSB(cRef.getRed(), cRef.getGreen(), cRef.getBlue(), hsbUserColor);
		
		// Valeur fixe choisie par l'utilisateur.
		hue = hsbUserColor[0];
		
		float[] hsbValsImg = null;
		for(int y = 0; y<h;y++)
		{
			for (int x=0;x < w;x++)
			{
				int pixel = ip.getPixel(x, y);
				int r = (pixel & 0x00ff00) >> 16;
				int g = (pixel & 0x00ff00) >> 8;
				int b = (pixel & 0x0000ff);	
				
				saturation = Color.RGBtoHSB(r, g, b, hsbValsImg)[1];
				brightness = Color.RGBtoHSB(r, g, b, hsbValsImg)[2];
				
				Color colorPixel = Color.getHSBColor(hue, saturation, brightness);
				ipRes.putPixel(x, y, colorPixel.getRGB());
			}
		}
		
		res.show();
		res.updateAndDraw();
	}

	@Override
	public int setup(String arg0, ImagePlus arg1) {
		if (arg0.equals("about")){
			IJ.showMessage("Colorisation");
			return PlugInFilter.DONE;
		}
		return PlugInFilter.DOES_8G;
	}
}
