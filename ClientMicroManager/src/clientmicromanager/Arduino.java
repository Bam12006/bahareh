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

public class Arduino {
    public void fadeLED(double fadingValue)
    {
        CMMCore setValue = new CMMCore();
        fadingValue = fadingValue + 0.05;
        if (fadingValue >= 0 || fadingValue <= 0.1)
        {
            fadingValue = fadingValue*2550;
        } else if (fadingValue < 0){
            fadingValue = 255;
        } else if (fadingValue > 0.1){
            fadingValue = 0;
        }
        int value = (int) fadingValue;
        String fadeValue = Integer.toString(value);
        try{
            setValue.loadSystemConfiguration("C:/Program Files/Micro-Manager-1.4/MMConfig_3.cfg");
            //core.loadDevice("Camera" ,"DemoCamera" , "DCam");
          
            setValue.setSerialPortCommand("COM3", fadeValue, "\n");
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(1);   
        }
    }
}
