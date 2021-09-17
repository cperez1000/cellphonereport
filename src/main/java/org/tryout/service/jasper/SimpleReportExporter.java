//except for #sendToPrinter method, most of this was taken from https://github.com/eugenp/tutorials/blob/master/libraries-2/src/main/java/com/baeldung/jasperreports

package org.tryout.service.jasper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.stereotype.Component;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SimpleReportExporter {

    private JasperPrint jasperPrint;

    public SimpleReportExporter() {
    }

    public SimpleReportExporter(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public void sendToPrinter() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
        printerJob.defaultPage(pageFormat);

        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        printRequestAttributeSet.add(MediaSizeName.NA_LETTER);
        printRequestAttributeSet.add(new Copies(1));

        JRPrintServiceExporter exporter = new JRPrintServiceExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        SimplePrintServiceExporterConfiguration printConfig = new SimplePrintServiceExporterConfiguration();
        printConfig.setPrintService(defaultPrintService);
        printConfig.setPrintServiceAttributeSet(defaultPrintService.getAttributes());
        printConfig.setPrintRequestAttributeSet(printRequestAttributeSet);
        printConfig.setDisplayPageDialog(false);
        printConfig.setDisplayPrintDialog(false);

        exporter.setConfiguration(printConfig);

        try {

            printerJob.setPrintService(defaultPrintService);
            exporter.exportReport();
        } catch (Exception ex) {
            Logger.getLogger(SimpleReportFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exportToPdf(String fileName, String author) {

        // print report to file
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fileName));

        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor(author);
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);
        try {
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(SimpleReportFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exportToXlsx(String fileName, String sheetName) {
        JRXlsxExporter exporter = new JRXlsxExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fileName));

        SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
        reportConfig.setSheetNames(new String[]{sheetName});

        exporter.setConfiguration(reportConfig);

        try {
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(SimpleReportFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exportToCsv(String fileName) {
        JRCsvExporter exporter = new JRCsvExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(fileName));

        try {
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(SimpleReportFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exportToHtml(String fileName) {
        HtmlExporter exporter = new HtmlExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(fileName));

        try {
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(SimpleReportFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
