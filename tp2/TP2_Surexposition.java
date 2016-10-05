package tp2;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.NewImage;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class TP2_Surexposition implements PlugInFilter {
    
    @Override
    public int setup(String arg0, ImagePlus arg1) {
        if (arg0.equals("about")) {
            IJ.showMessage("Surexposition");
            return DONE;
        }
        return DOES_8G;
    }

    @Override
    public void run(ImageProcessor ip) {
        surexposition(ip);
    }
    
    
    void surexposition(ImageProcessor ip) {
        ImagePlus res = NewImage.createByteImage("Surexposition", ip.getWidth(), ip.getHeight(), 1, NewImage.FILL_BLACK);
        ImageProcessor ipRes = res.getProcessor();
        
        int w = ip.getWidth();
        int h = ip.getHeight();
        
        // Parcours des pixels de l'image.
        for(int y = 0; y < h; y++){
            for(int x = 0; x< w ; x++) {
            	int pixel = ip.getPixel(x, y);
            	
            	int resultat = Math.min(pixel + pixel, 255);
            	ipRes.putPixel(x, y, resultat);
            }
        }
        
        res.show();
        res.updateAndDraw();
    }
}