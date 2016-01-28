/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmicromanager;

/**
 *
 * @author Bahar
 */


import static ij.IJ.run;
import ij.ImagePlus;
import ij.io.FileSaver;
import ij.io.Opener;
import ij.plugin.ImageCalculator;
import ij.process.ImageProcessor;




public class ProcessingTest {
    
    public static void main(String[] args) {
        // TODO code application logic here
        ImagePlus img = null;
        Opener op = new Opener();
        img = op.openImage("E:/Photobleaching/7/image1-0.tif");
        ImageProcessor im = img.getProcessor().duplicate();
        //im.medianFilter();
        //im.findEdges();
        im.setAutoThreshold("Triangle dark");
        ImagePlus plus = new ImagePlus("image-0.tif", im);
        run(plus, "Convert to Mask", "");
        run(plus, "Dilate", "");
        run(plus, "Fill Holes", "");
        run(plus, "Erode", "");
        run(plus, "Watershed", "");
        run(plus, "Analyze Particles...", "size=120-900 circularity=0.70-1.00 show=Outlines display exclude clear summarize");
        
        ImageCalculator ic = new ImageCalculator();
        ImagePlus finalImg = ic.run("Multiply create 32-bit", plus, img);
        
        //float[][] mask = plus.getProcessor().getFloatArray();
        //float[][] image = im.getFloatArray();
        //int hight = mask.length;
        //int width = mask[0].length;
        //float[][] intensityImg = new float[hight][width];
        //for (int i = 0; i < hight; i++)
        //{
        //    for (int j = 0; j < width; j++)
        //    {
        //        intensityImg[i][j] = (mask[i][j]/255)*image[i][j];
        //    }
        //}
        
       // ImagePlus finalImg = intensityImg;
        FileSaver fs = new FileSaver(finalImg);
        fs.saveAsTiff("E:/Photobleaching/7processed/image1-0.tif");
        
        FileSaver fs2 = new FileSaver(plus);
        fs2.saveAsTiff("E:/Photobleaching/7processed/image1-00.tif");
    }
    
}
