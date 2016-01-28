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
public class ReferenceSignal {
    public double[] cosFunction(double amplitude, double frequency, double phi, int samplingTime, int wholeTime){
        double[] signal = new double[wholeTime/samplingTime];
        for (int n = 0; n<wholeTime/samplingTime; n++) 
        {
            signal[n] = amplitude * Math.cos(2*Math.PI*frequency*samplingTime*n);
        }
        return signal;
    }
    
}
