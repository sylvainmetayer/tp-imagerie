package tp1;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public  class  TP1_Mon_Script  implements  PlugInFilter {
	public  void  run(ImageProcessor  ip){
		ip.invert ();
	}
	
	public  int  setup(String arg , ImagePlus  imp){
	if (arg.equals("about")){
		IJ.showMessage("Inversion  video");
		return  DONE;
	}
	return  DOES_8G;
}
}
