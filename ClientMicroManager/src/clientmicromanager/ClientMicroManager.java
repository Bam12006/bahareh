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
public class ClientMicroManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int iteration = 5;
        double kPropotional = 1;
        double kIntegral = 1;
        double kDerivative = 1;
        double newFI;
        double[] reference;
        double newError;
        double oldError = 0;
        double integralError = 0;
        double amp = 0;
        double freq = 0;
        double phi = 0;
        int sampling = 0;
        int experimentTime = 0;
        double[] normalizedFI = new double[5];
        
        MeasuringCellFluorescence cell = new MeasuringCellFluorescence();
        PID pid = new PID();
        Arduino arduino = new Arduino();
        ReferenceSignal refSignal = new ReferenceSignal();
        double cFI0 = cell.measureIntensity();
        reference = refSignal.cosFunction(amp, freq, phi, sampling, experimentTime);
        normalizedFI[0] = 1;
        for (int i=1; i<iteration; i++)
        {
            double cFI = cell.measureIntensity();
            newFI = cFI/cFI0;
            newError = reference[i] - newFI;
            double[] bLI = pid.lightIntensity(kPropotional, kIntegral, kDerivative, newError, oldError, integralError);
            arduino.fadeLED(bLI[0]);
            oldError = newError;
            integralError = bLI[1];
            normalizedFI[i] = newFI;
        }
    }
}
