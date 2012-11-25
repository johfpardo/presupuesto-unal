/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */
package frontera;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import control.ControlSistema;
import entidad.Presupuesto;
import entidad.Rubro;
import entidad.Sistema;
import java.awt.Desktop;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PDFReport {

    ControlSistema controls = new ControlSistema();
    String np="";
    
    public static void main(String[] args) throws IOException, DocumentException {
        //inicializar();
        new PDFReport();
    }
    
    private static void inicializar() {
        Sistema.msistema.setPresupuesto(new ArrayList<Presupuesto>());
        ControlSistema controls = new ControlSistema();
        String datos[] = {"Primero", "20000000"};
        controls.crearPresupuesto(datos);
        String[] datosr = {"Computadores", "10000000"};
        controls.crearRubro(datosr, "Primero");
        datosr[0] = "Viajes";
        datosr[1] = "8540000";
        controls.crearRubro(datosr, "Primero");
    }

    public PDFReport() throws IOException, DocumentException {
        
        createPdf();
    }
    
    public PDFReport(String nombrepresupuesto) throws IOException, DocumentException {
        
        np=nombrepresupuesto;
        createPdf();
    }

    public void createPdf() throws IOException, DocumentException {

        String filename = "Reporte_";
        Object[] presupuesto = controls.getPresupuesto(np);
        if (presupuesto != null) {
            filename += np;
        }
        filename += ".pdf";
        Document document = new Document(PageSize.LEGAL.rotate(), 50, 50, 50, 50);
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        document.add(createReportTitle());
        document.add(createTable());
        document.close();
        try {
            File path = new File(filename);
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public PdfPTable createTable() {

        PdfPTable table = new PdfPTable(7);
        PdfPCell cell;
        Object[] presupuesto = controls.getPresupuesto(np);
        List<Object[]> rubros=controls.getRubro(np);
        cell = new PdfPCell(new Phrase("Descripción"));
        cell.setColspan(2);
        cell.setRowspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Presupuesto Aprobado"));
        cell.setRowspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Presupuesto Ejecutado"));
        cell.setRowspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Presupuesto por Ejecutar"));
        cell.setRowspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Porcentaje Ejecutado"));
        cell.setRowspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Porcentaje por Ejecutar"));
        cell.setRowspan(2);
        table.addCell(cell);
        
        DecimalFormat df = new DecimalFormat("$ #,##0.#");
        for (Object[] r : rubros) {
            cell = new PdfPCell(new Phrase(""+r[0]));
            cell.setColspan(2);
            table.addCell(cell);
            table.addCell(df.format(r[2]));
            table.addCell(df.format(r[3]));
            table.addCell(df.format(r[4]));
            table.addCell((float)r[3]*100/(float)r[2] +"%");
            table.addCell(100 -(float)r[3]*100/(float)r[2] +"%");
        }
        cell = new PdfPCell(new Phrase("Total Gastos"));
        cell.setColspan(2);
        table.addCell(cell);
        if (presupuesto != null) {
            table.addCell(df.format(presupuesto[2]));
            table.addCell(df.format(presupuesto[5]));
            table.addCell(df.format(presupuesto[6]));
            table.addCell((float)presupuesto[5]* 100/(float)presupuesto[6] +"%");
            table.addCell(100 - (float)presupuesto[5]* 100/(float)presupuesto[6] +"%");
        }
        return table;
    }

    private Element createReportTitle() throws IOException, DocumentException {

        Image img = Image.getInstance("src/resources/logo-unal.png");
        Paragraph p = new Paragraph(new Chunk(img, 0, -15, true));
        Paragraph t = new Paragraph("Universidad Nacional de Colombia - Sede Bogotá", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD));
        t.setAlignment(Element.ALIGN_CENTER);
        p.add(t);
        t = new Paragraph("Departamento de Ingeniería de Sistemas e Industrial", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD));
        t.setAlignment(Element.ALIGN_CENTER);
        p.add(t);
        t = new Paragraph("Ejecución Presupuestal Acumulada por Proyecto y Área", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD));
        t.setAlignment(Element.ALIGN_CENTER);
        p.add(t);
        //t = new Paragraph("Año "+controls.getPresupuesto(nombrepresupuesto).getFechaPlaneacion().getYear()+1900, new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD));
        t = new Paragraph("Año 2012", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD));
        t.setAlignment(Element.ALIGN_CENTER);
        p.add(t);
        p.add(new Paragraph(" "));

        return p;
    }
}
