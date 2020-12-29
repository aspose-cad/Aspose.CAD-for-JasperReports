package com.aspose.cad.jasperreports;

import net.sf.jasperreports.engine.JRException;
import org.junit.Test;

public class ExamplesTest {
    @Test
    public void loadShapesReport() throws JRException {
        Examples examplesTest = new Examples();
        examplesTest.loadShapesReport();
    }

    @Test
    public void batchExportExample() throws JRException {
        Examples examplesTest = new Examples();
        examplesTest.batchExportExample();
    }

    @Test
    public void multipageExportExample() throws JRException {
        Examples examples = new Examples();
        examples.multipageExportExample();
    }

    @Test
    public void zipBatchOutputExample() throws JRException {
        Examples examples = new Examples();
        examples.zipBatchOutput();
    }
}