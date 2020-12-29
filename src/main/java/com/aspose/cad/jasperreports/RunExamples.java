package com.aspose.cad.jasperreports;

import net.sf.jasperreports.engine.JRException;

public class RunExamples {
    public static void main(String[] args) {
        Examples examples = new Examples();
        try {
            examples.loadShapesReport();
            examples.multipageExportExample();
            examples.batchExportExample();
            examples.zipBatchOutput();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
