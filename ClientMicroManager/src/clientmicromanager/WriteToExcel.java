/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmicromanager;

import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author cholab01
 */
public class WriteToExcel {
    public void write(int col, int row, double[] data) throws IOException, WriteException{
        File f = new File("E:/Photobleaching/Target/1/excel9.xls");
        WritableWorkbook myexel = Workbook.createWorkbook(f);
        WritableSheet mysheet = myexel.createSheet("mySheet", 0);
        
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++){
                String stringData = Double.toString(data[j]);
                Label l = new Label(i, j, stringData);
                mysheet.addCell(l);
            }
        }
        myexel.write();
        myexel.close();
    }
}
