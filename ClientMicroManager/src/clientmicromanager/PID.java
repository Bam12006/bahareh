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
public class PID{
    public double[] lightIntensity(double kPropotional, double kIntegral, double kDerivative , double newError,double oldError, double integralError){
        

        integralError = integralError + newError;
        //PID control formula:
        double input = kPropotional*newError + kIntegral*integralError + kDerivative*(newError - oldError);
        
        return new double[] {input, integralError};        
    }
}
