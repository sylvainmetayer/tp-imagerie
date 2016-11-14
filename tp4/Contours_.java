package tp4;

import ij.*;
import ij.process.*;
import ij.gui.*;
import ij.plugin.filter.*;

public class Contours_ implements PlugInFilter {

	private int[][] convolution(ImageProcessor ip, int[][] masque) {
		int w = ip.getWidth();
		int h = ip.getHeight();
		
		int n = masque.length / 2;

		int masqueResu[][] = new int[w][h];
		
		for (int y = n; y < h - n; y++) {
			for (int x = n; x < w - n; x++) {
				int s = 0;
				for (int u = -n; u <= n; u++)
					for (int v = -n; v <= n; v++) {
						s += ip.getPixel(x + u, v + y) * masque[u + n][v + n];
					}
						
				masqueResu[x][y] = s ;
			}
		}

		return masqueResu;
	}

	// Calcule la norme du gradient de l'image.
	// Le gradient suivant y est calculé par convolution avec le masque masqueV
	// (appel de la méthode convolution)
	// De même, le gradient suivant x est calculé par convolution avec le masque
	// masqueH.
	// Les deux tableaux obtenus sont combinés pour calculer le tableau résultat
	private int[][] normeG(ImageProcessor ip, int[][] masqueV, int[][] masqueH) {
		int w = ip.getWidth();
		int h = ip.getHeight();
		int[][] gradienV = convolution(ip, masqueV);
		int[][] gradienH = convolution(ip, masqueH);
		int[][] resultat = new int[w][h];

		for (int i = 0; i < ip.getWidth(); i++) {
			for (int j = 0; j < ip.getHeight(); j++) {
				resultat[i][j] = (int) Math.sqrt(gradienH[i][j] * gradienH[i][j] + gradienV[i][j] * gradienV[i][j]);
			}
				
		}

		return resultat;
	}

	// Crée une image de la norme du gradient (appelle la méthode normeG)
	private ImagePlus imageNormeGradient(ImageProcessor ip, int[][] masqueV, int[][] masqueH, String titre) {
		int w = ip.getWidth();
		int h = ip.getHeight();
		ImagePlus imageG = NewImage.createByteImage(titre, w, h, 1, NewImage.FILL_BLACK);
		ImageProcessor ipg = imageG.getProcessor();

		int[][] normeG = normeG(ip, masqueV, masqueH);

		for (int x = 0; x < w; x++)
			for (int y = 0; y < h; y++)
				ipg.putPixel(x, y, normeG[x][y]);

		return imageG;
	}

	public ImagePlus imageNormeGradientPrewitt(ImageProcessor ip) {

		int[][] masque1 = { { -1, 0, 1 }, { -1, 0, 1 }, { -1, 0, 1 } };
		int[][] masque2 = { { -1, -1, -1 }, { 0, 0, 0 }, { 1, 1, 1 } };

		return imageNormeGradient(ip, masque1, masque2, "Image norme gradien Prewitt");
	}

	public ImagePlus imageNormeGradientSobel(ImageProcessor ip) {
		int[][] masque1 = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
		int[][] masque2 = { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };

		return imageNormeGradient(ip, masque1, masque2, "Image norme gradien Sobel");
	}

	private ImagePlus imageNormeGradientDeriche(ImageProcessor ip, double alpha) {
		int h = ip.getHeight();
		int w = ip.getWidth();
		FloatProcessor gx = new FloatProcessor(w, h);
		FloatProcessor gy = new FloatProcessor(w, h);
		ByteProcessor gn = Deriche.deriche(ip, alpha, gx, gy); // code fourni,
																// alpha eest un
																// coefficient
																// de lissage
		ImagePlus imageG = new ImagePlus("Deriche" + alpha, gn);
		return imageG;
	}

	public void run(ImageProcessor ip) {
		// affichage de l'image de la norme du gradient avec Prewitt
		ImagePlus prewitt = imageNormeGradientPrewitt(ip);
		ImagePlus deriche = imageNormeGradientDeriche(ip, 0.2);
		ImagePlus sobel = imageNormeGradientSobel(ip);
		
		deriche.show();
		deriche.updateAndDraw();
		
		prewitt.show();
		prewitt.updateAndDraw();
		
		sobel.show();
		sobel.updateAndDraw();

		// TODO
	}

	public int setup(String arg, ImagePlus imp) {
		return DOES_8G;
	}
}
