/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmicromanager;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.io.Opener;
import ij.process.ImageProcessor;
import java.io.IOException;
import jxl.write.WriteException;

/**
 *
 * @author Bahar
 */
public class CellProcessManually {
    public static void main(String[] args) throws IOException, WriteException {
        // TODO code application logic here
        int cellNum = 0;
        double[] cTCF = new double[30];
        ImagePlus img = null;
        Opener op = new Opener();
        
            for(int i = 0; i<30; i++)
            {
                String num = Integer.toString(i);               
                img = op.openImage("E:/Photobleaching/Target/1/" + num + ".tif");

                ImageProcessor im = img.getProcessor().duplicate();
            //set region of interest for target cell
                im.setRoi(342, 668, 36, 36);
                double cellMean = im.getStatistics().mean;
                double cellArea = im.getStatistics().area;
                double cellIntensity = cellMean*cellArea;
    //--------------------------------------------------------------------------        
    //calculate Integrated Density of background for target cell area
    //--------------------------------------------------------------------------
                ImageProcessor background1 = img.getProcessor().duplicate();
            //set region of interest for background
                background1.setRoi(341, 591, 21, 20);
                double backgroundMean1 = background1.getStatistics().mean;
                double backgroundIntensity1 = backgroundMean1*cellArea;
        
                ImageProcessor background2 = img.getProcessor().duplicate();
            //set region of interest for background
                background2.setRoi(383, 641, 20, 20);
                double backgroundMean2 = background2.getStatistics().mean;
                double backgroundIntensity2 = backgroundMean2*cellArea;
        
                double backgroundIntensity = (backgroundIntensity1 + backgroundIntensity2)/2;
    // calculate corrected total cell fluorescence (CTCF)
                cTCF[cellNum] = cellIntensity - backgroundIntensity;
                cellNum = cellNum + 1;                
            }
        
        
        WriteToExcel write = new WriteToExcel();
        write.write(1, cellNum, cTCF);
   
    }
}
