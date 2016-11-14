import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;

public class Contours_ implements PlugInFilter {

    private int[][] convolution (ImageProcessor ip, int[][] masque){
	// TODO
    }


    // Calcule la norme du gradient de l'image.
    // Le gradient suivant y est calculé par convolution avec le masque masqueV (appel de la méthode convolution)
    // De même, le gradient suivant x est calculé par convolution avec le masque masqueH.
    // Les deux tableaux obtenus sont combinés pour calculer le tableau résultat
    private int[][] normeG(ImageProcessor ip, int[][] masqueV, int[][]masqueH){
	// TODO
    }
	
    // Crée une image de la norme du gradient (appelle la méthode normeG)
    private ImagePlus imageNormeGradient(ImageProcessor ip, int[][] masqueV, int[][]masqueH, String titre){
 	int w = ip.getWidth();
	int h = ip.getHeight();
	ImagePlus imageG = NewImage.createByteImage (titre, w, h, 1, NewImage.FILL_BLACK);
	ImageProcessor ipg = imageG.getProcessor();
	// TODO
	return imageG;
    }
	
    public ImagePlus imageNormeGradientPrewitt(ImageProcessor ip){
	// TODO
    }
	
    public ImagePlus imageNormeGradientSobel(ImageProcessor ip){
	// TODO
    }
	
    private ImagePlus imageNormeGradientDeriche(ImageProcessor ip, double alpha){
	int h = ip.getHeight();
	int w = ip.getWidth();
	FloatProcessor gx = new FloatProcessor(w, h);
	FloatProcessor gy = new FloatProcessor(w, h); 
	ByteProcessor gn = Deriche.deriche( ip, alpha, gx, gy ); // code fourni, alpha eest un coefficient de lissage
	ImagePlus imageG = new ImagePlus("Deriche" + alpha, gn);
	return imageG;
    }
	
    public void run(ImageProcessor ip){
	// affichage de l'image de la norme du gradient avec Prewitt
	ImagePlus prewitt = imageNormeGradientPrewitt(ip);
	prewitt.show();
	prewitt.updateAndDraw();
		
	// TODO
    }
		
    public int setup(String arg, ImagePlus imp){
	return DOES_8G;
    }
}
