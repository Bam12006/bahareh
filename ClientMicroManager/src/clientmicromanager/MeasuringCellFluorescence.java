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
import mmcorej.CMMCore;
import ij.process.ImageProcessor;
import org.micromanager.utils.ImageUtils;

public class MeasuringCellFluorescence {
    
    public double measureIntensity(){
        //Make micromanager core object
        CMMCore core = new CMMCore();
        //corrected total cell fluorescence
        double cTCF = 0;                                         
        try{
            //load Camera from core object
            core.loadDevice("Camera" ,"DemoCamera" , "DCam");
            //Initial all devices for core object
            core.initializeAllDevices();
            //set desired exposure time(ms) to capture image
            core.setExposure(50);
            //capture an image using the camera
            core.snapImage();
            //make an array of image pixel values 
            byte[] specimen = (byte[])core.getImage();            
    //--------------------------------------------------------------------------
    //calculate  Integrated Density of cell
    //--------------------------------------------------------------------------
            ImageProcessor cell = ImageUtils.makeProcessor(core, specimen);
            //set region of interest for target cell
            cell.setRoi(246, 0, 20, 20);
            double cellMean = cell.getStatistics().mean;
            double cellArea = cell.getStatistics().area;
            double cellIntensity = cellMean*cellArea;
    //--------------------------------------------------------------------------        
    //calculate Integrated Density of background for target cell area
    //--------------------------------------------------------------------------
            ImageProcessor background = ImageUtils.makeProcessor(core, specimen);
            //set region of interest for background
            background.setRoi(246, 0, 20, 20);
            double backgroundMean = cell.getStatistics().mean;
            double backgroundIntensity = backgroundMean*cellArea;
            
    // calculate corrected total cell fluorescence (CTCF)
            cTCF = cellIntensity - backgroundIntensity; 
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return cTCF;  
    }
}
