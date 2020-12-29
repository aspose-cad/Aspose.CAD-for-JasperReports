package com.aspose.cad.jasperreports;

import com.aspose.cad.jasperreports.bmp.ASBmpExportConfigurationImpl;
import com.aspose.cad.jasperreports.bmp.ASBmpExporter;
import com.aspose.cad.jasperreports.bmp.BitmapCompression;
import com.aspose.cad.jasperreports.common.SmoothingMode;
import com.aspose.cad.jasperreports.common.TextRenderingHintEnum;
import com.aspose.cad.jasperreports.config.ASBatchExporterOutputImpl;
import com.aspose.cad.jasperreports.config.ASExportInputImpl;
import com.aspose.cad.jasperreports.config.ASExporterOutputImpl;
import com.aspose.cad.jasperreports.gif.ASGifExportConfigurationImpl;
import com.aspose.cad.jasperreports.gif.ASGifExporter;
import com.aspose.cad.jasperreports.jpg.ASJpegExportConfigurationImpl;
import com.aspose.cad.jasperreports.jpg.ASJpegExporter;
import com.aspose.cad.jasperreports.jpg.JpegCompressionColorMode;
import com.aspose.cad.jasperreports.jpg2000.ASJpeg2000ExportConfigurationImpl;
import com.aspose.cad.jasperreports.jpg2000.ASJpeg2000Exporter;
import com.aspose.cad.jasperreports.jpg2000.Jpeg2000CodecEnum;
import com.aspose.cad.jasperreports.pdf.*;
import com.aspose.cad.jasperreports.png.ASPngExportConfigurationImpl;
import com.aspose.cad.jasperreports.png.ASPngExporter;
import com.aspose.cad.jasperreports.png.PngColorType;
import com.aspose.cad.jasperreports.psd.*;
import com.aspose.cad.jasperreports.svg.ASSvgExportConfigurationImpl;
import com.aspose.cad.jasperreports.svg.ASSvgExporter;
import com.aspose.cad.jasperreports.tiff.ASTiffExportConfigurationImpl;
import com.aspose.cad.jasperreports.tiff.ASTiffExporter;
import com.aspose.cad.jasperreports.tiff.enums.TiffCompressionsEnum;
import com.aspose.cad.jasperreports.tiff.enums.TiffExpectedFormatEnum;
import com.aspose.cad.jasperreports.wmf.ASWmfExportConfigurationImpl;
import com.aspose.cad.jasperreports.wmf.ASWmfExporter;
import net.sf.jasperreports.engine.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Examples {

    public void loadShapesReport() throws JRException {
        String reportPath = "reports/ShapesReport.jrxml";
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(reportPath);
        if(stream == null){
            throw new RuntimeException("Can't load report: " + reportPath);
        }

        JasperReport jasperReport = null;
        try {
            jasperReport = JasperCompileManager.compileReport(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JREmptyDataSource());

        String outputDirectory = "output";
        File directory = new File(outputDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }

        wmfExample(jasperPrint, new File("output/shapesExample.wmf"));
        svgExample(jasperPrint, new File("output/shapesExample.svg"));
        jpeg2000Example(jasperPrint, new File("output/shapesExample.jp2"));
        psdExample(jasperPrint, new File("output/shapesExample.psd"));
        tiffExample(jasperPrint, new File("output/shapesExample.tiff"));
        pngExample(jasperPrint, new File("output/shapesExample.png"));
        jpegExample(jasperPrint, new File("output/shapesExample.jpg"));
        gifExample(jasperPrint, new File("output/shapesExample.gif"));
        bmpExample(jasperPrint, new File("output/shapesExample.bmp"));
        pdfExample(jasperPrint, new File("output/shapesExample.pdf"));
    }

    public void batchExportExample() throws JRException {
        ASJpegExporter jpegExporter = new ASJpegExporter();

        ASJpegExportConfigurationImpl jpegExportConfiguration = new ASJpegExportConfigurationImpl();
        jpegExportConfiguration.setQuality(35);

        jpegExporter.setConfiguration(jpegExportConfiguration);

        URL reportUrl = this.getClass().getClassLoader().getResource("reports/NoPageBreakReport.jrprint");
        ASExportInputImpl exporterInput = new ASExportInputImpl(reportUrl);
        jpegExporter.setExporterInput(exporterInput);

        String outputDirectory = "batchOutput";

        File directory = new File(outputDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }

        ASBatchExporterOutputImpl batchExporterOutput = new ASBatchExporterOutputImpl(outputDirectory, "jpegBatchExport_page_");
        jpegExporter.setExporterOutput(batchExporterOutput);

        jpegExporter.exportReport();
    }

    public void multipageExportExample() throws JRException {
        ASTiffExporter exporter = new ASTiffExporter();

        ASTiffExportConfigurationImpl tiffExportConfiguration = new ASTiffExportConfigurationImpl(TiffExpectedFormatEnum.TiffDeflateRgb);
        tiffExportConfiguration.setSmoothingMode(SmoothingMode.HighSpeed);
        tiffExportConfiguration.setDocumentName("Multipage");
        tiffExportConfiguration.setCompression(TiffCompressionsEnum.Jpeg);
        exporter.setConfiguration(tiffExportConfiguration);

        URL reportUrl = this.getClass().getClassLoader().getResource("reports/NoPageBreakReport.jrprint");
        ASExportInputImpl exporterInput = new ASExportInputImpl(reportUrl);
        exporter.setExporterInput(exporterInput);

        String outputDirectory = "multipageOutput";

        File directory = new File(outputDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }

        ASExporterOutputImpl exporterOutput = new ASExporterOutputImpl("multipageOutput/MultipageExample.tiff");
        exporterOutput.setMultipageExport(true);
        exporter.setExporterOutput(exporterOutput);

        exporter.exportReport();
    }


    public void wmfExample(JasperPrint jasperPrint, File destFile) throws JRException {
        ASWmfExporter wmfExporter = new ASWmfExporter();
        ASWmfExportConfigurationImpl wmfExportConfiguration = new ASWmfExportConfigurationImpl();
        wmfExporter.setConfiguration(wmfExportConfiguration);

        ASExportInputImpl exporterInput = new ASExportInputImpl(jasperPrint);
        wmfExporter.setExporterInput(exporterInput);

        ASExporterOutputImpl exporterOutput = new ASExporterOutputImpl(destFile);
        wmfExporter.setExporterOutput(exporterOutput);

        wmfExporter.exportReport();
    }

    public void svgExample(JasperPrint jasperPrint, File destFile) throws JRException {
        ASSvgExporter svgExporter = new ASSvgExporter();
        ASSvgExportConfigurationImpl svgExportConfiguration = new ASSvgExportConfigurationImpl();
        svgExportConfiguration.setEmbedFonts(true);

        svgExporter.setConfiguration(svgExportConfiguration);

        ASExportInputImpl exporterInput = new ASExportInputImpl(jasperPrint);
        svgExporter.setExporterInput(exporterInput);

        ASExporterOutputImpl exporterOutput = new ASExporterOutputImpl(destFile);
        svgExporter.setExporterOutput(exporterOutput);

        svgExporter.exportReport();
    }

    public void jpeg2000Example(JasperPrint jasperPrint, File destFile) throws JRException {
        ASJpeg2000Exporter jpeg2000Exporter = new ASJpeg2000Exporter();
        ASJpeg2000ExportConfigurationImpl jpeg2000ExportConfiguration = new ASJpeg2000ExportConfigurationImpl();
        jpeg2000ExportConfiguration.setIrreversible(true);
        jpeg2000ExportConfiguration.setCodec(Jpeg2000CodecEnum.J2K);
        jpeg2000Exporter.setConfiguration(jpeg2000ExportConfiguration);

        ASExportInputImpl exporterInput = new ASExportInputImpl(jasperPrint);
        jpeg2000Exporter.setExporterInput(exporterInput);

        ASExporterOutputImpl exporterOutput = new ASExporterOutputImpl(destFile);
        jpeg2000Exporter.setExporterOutput(exporterOutput);

        jpeg2000Exporter.exportReport();
    }

    public void psdExample(JasperPrint jasperPrint, File destFile) throws JRException {
        ASPsdExporter psdExporter = new ASPsdExporter();
        ASPsdExportConfigurationImpl psdExportConfiguration = new ASPsdExportConfigurationImpl();
        psdExportConfiguration.setColorMode(ColorModesEnum.Rgb);
        psdExportConfiguration.setPsdVersion(PsdVersionEnum.Psd);
        psdExportConfiguration.setCompressionMethod(CompressionMethodEnum.Raw);
        psdExporter.setConfiguration(psdExportConfiguration);

        ASExportInputImpl exporterInput = new ASExportInputImpl(jasperPrint);
        psdExporter.setExporterInput(exporterInput);

        ASExporterOutputImpl exporterOutput = new ASExporterOutputImpl(destFile);
        psdExporter.setExporterOutput(exporterOutput);

        psdExporter.exportReport();
    }

    public void tiffExample(JasperPrint jasperPrint, File destFile) throws JRException {
        ASTiffExporter tiffExporter = new ASTiffExporter();
        ASTiffExportConfigurationImpl tiffExportConfiguration = new ASTiffExportConfigurationImpl(TiffExpectedFormatEnum.TiffDeflateRgb);
        tiffExportConfiguration.setArtist("John Smith");
        tiffExportConfiguration.setDateTime("12.08.2020");
        tiffExportConfiguration.setCompression(TiffCompressionsEnum.AdobeDeflate);
        tiffExporter.setConfiguration(tiffExportConfiguration);

        ASExportInputImpl exporterInput = new ASExportInputImpl(jasperPrint);
        tiffExporter.setExporterInput(exporterInput);

        ASExporterOutputImpl exporterOutput = new ASExporterOutputImpl(destFile);
        tiffExporter.setExporterOutput(exporterOutput);

        tiffExporter.exportReport();
    }

    public void pngExample(JasperPrint jasperPrint, File destFile) throws JRException {
        ASPngExporter pngExporter = new ASPngExporter();
        ASPngExportConfigurationImpl pngExportConfiguration = new ASPngExportConfigurationImpl();
        pngExportConfiguration.setColorType(PngColorType.Grayscale);
        pngExportConfiguration.setBitDepth((byte) 8);
        pngExporter.setConfiguration(pngExportConfiguration);

        ASExportInputImpl exporterInput = new ASExportInputImpl(jasperPrint);
        pngExporter.setExporterInput(exporterInput);

        ASExporterOutputImpl exporterOutput = new ASExporterOutputImpl(destFile);
        pngExporter.setExporterOutput(exporterOutput);

        pngExporter.exportReport();
    }

    public void jpegExample(JasperPrint jasperPrint, File destFile) throws JRException {
        ASJpegExporter jpegExporter = new ASJpegExporter();
        ASJpegExportConfigurationImpl jpegExportConfiguration = new ASJpegExportConfigurationImpl();
        jpegExportConfiguration.setQuality(70);
        jpegExportConfiguration.setColorType(JpegCompressionColorMode.Rgb);
        jpegExportConfiguration.setSmoothingMode(SmoothingMode.HighSpeed);
        jpegExporter.setConfiguration(jpegExportConfiguration);

        ASExportInputImpl exporterInput = new ASExportInputImpl(jasperPrint);
        jpegExporter.setExporterInput(exporterInput);

        ASExporterOutputImpl exporterOutput = new ASExporterOutputImpl(destFile);
        jpegExporter.setExporterOutput(exporterOutput);

        jpegExporter.exportReport();
    }

    public void gifExample(JasperPrint jasperPrint, File destFile) throws JRException {
        ASGifExporter gifExporter = new ASGifExporter();
        ASGifExportConfigurationImpl gifExportConfiguration = new ASGifExportConfigurationImpl();
        gifExportConfiguration.setDoPaletteCorrection(true);
        gifExportConfiguration.setInterlaced(true);
        gifExporter.setConfiguration(gifExportConfiguration);

        ASExportInputImpl exporterInput = new ASExportInputImpl(jasperPrint);
        gifExporter.setExporterInput(exporterInput);

        ASExporterOutputImpl exporterOutput = new ASExporterOutputImpl(destFile);
        gifExporter.setExporterOutput(exporterOutput);

        gifExporter.exportReport();
    }

    public void bmpExample(JasperPrint jasperPrint, File destFile) throws JRException {
        ASBmpExporter bmpExporter = new ASBmpExporter();
        ASBmpExportConfigurationImpl bmpExportConfiguration = new ASBmpExportConfigurationImpl();
        bmpExportConfiguration.setCompression(BitmapCompression.Rgb);
        bmpExportConfiguration.setBitsPerPixel(16);
        bmpExportConfiguration.setTextRenderingHintEnum(TextRenderingHintEnum.AntiAlias);
        bmpExporter.setConfiguration(bmpExportConfiguration);

        ASExportInputImpl exporterInput = new ASExportInputImpl(jasperPrint);
        bmpExporter.setExporterInput(exporterInput);

        ASExporterOutputImpl exporterOutput = new ASExporterOutputImpl(destFile);
        bmpExporter.setExporterOutput(exporterOutput);

        bmpExporter.exportReport();
    }

    public void pdfExample(JasperPrint jasperPrint, File destFile) throws JRException {
        ASPdfExporter pdfExporter = new ASPdfExporter();
        ASPdfExportConfigurationImpl pdfExportConfiguration = new ASPdfExportConfigurationImpl();
        pdfExportConfiguration.setAuthor("John Smith");
        pdfExportConfiguration.setSubject("pdf export example");
        pdfExportConfiguration.setTitle("Report");
        pdfExportConfiguration.setImageCompression(ImageCompressionEnum.Ccitt4);
        pdfExportConfiguration.setJpegQuality(90);
        pdfExportConfiguration.setPdfCompliance(PdfComplianceEnum.Pdf15);
        pdfExportConfiguration.setTextCompression(TextCompressionEnum.Flate);
        pdfExporter.setConfiguration(pdfExportConfiguration);

        ASExportInputImpl exporterInput = new ASExportInputImpl(jasperPrint);
        pdfExporter.setExporterInput(exporterInput);

        ASExporterOutputImpl exporterOutput = new ASExporterOutputImpl(destFile);
        pdfExporter.setExporterOutput(exporterOutput);

        pdfExporter.exportReport();
    }

    public void zipBatchOutput() throws JRException {
        ASJpegExporter jpegExporter = new ASJpegExporter();

        ASJpegExportConfigurationImpl jpegExportConfiguration = new ASJpegExportConfigurationImpl();
        jpegExportConfiguration.setQuality(35);

        jpegExporter.setConfiguration(jpegExportConfiguration);

        URL reportUrl = this.getClass().getClassLoader().getResource("reports/NoPageBreakReport.jrprint");
        ASExportInputImpl exporterInput = new ASExportInputImpl(reportUrl);
        jpegExporter.setExporterInput(exporterInput);

        ASBatchExporterOutputImpl batchExporterOutput = new ASBatchExporterOutputImpl(new File("zipOutput.zip"), "jpegBatchExport_page_");
        jpegExporter.setExporterOutput(batchExporterOutput);

        jpegExporter.exportReport();
    }
}
