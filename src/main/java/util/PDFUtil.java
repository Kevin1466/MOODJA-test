package util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import model.Document;
import model.Employee;
import model.Manager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PDFUtil {
	public static byte[] content(Document document, Employee employee) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] result = null;
		Manager manager = document.getManager();
		com.itextpdf.text.Document doc = new com.itextpdf.text.Document(PageSize.A4, 30, 30, 70, 30);
		try {
			PdfWriter pdfWriter = PdfWriter.getInstance(doc, byteArrayOutputStream);
			doc.open();
			PdfContentByte pdfContentByte = pdfWriter.getDirectContent();
			Font font = new Font(Font.FontFamily.UNDEFINED, 25, Font.BOLD);
			String contentTitle = "Document " + document.getId() + " - " + employee.getId();
			Phrase header = new Phrase(contentTitle, font);
			ColumnText.showTextAligned(pdfContentByte, Element.ALIGN_CENTER, header, (doc.right() - doc.left()) / 2 + doc.leftMargin(), doc.top() + 10, 0);
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.getDefaultCell().setUseAscender(true);
			table.getDefaultCell().setUseDescender(true);
			table.setSpacingAfter(10f);
			table.setSpacingAfter(10f);
			int[] widths = {40, 60};
			table.setWidths(widths);
			table.addCell("Document ID");
			table.addCell(document.getId() + " ");
			table.addCell("Document Title");
			table.addCell(document.getTitle());
			table.addCell("Employee");
			table.addCell(employee.getFirstName() + employee.getLastName());
			table.addCell("Number");
			table.addCell(document.getNumber() + " ");
			table.addCell("Summary");
			table.addCell(document.getSummary() + " ");
			table.addCell("Content");
			table.addCell(document.getContent());
			table.addCell("Comment");
			table.addCell(document.getComment());
			table.addCell("Submit Date");
			table.addCell(document.getSubmitDate() + " ");
			table.addCell("Viewed Date");
			table.addCell(document.getViewDate() + " ");
			table.addCell("Manager");
			table.addCell(manager.getFirstName() + manager.getLastName());
			PdfPCell cell = defaultCell("Announcement");
			cell.setColspan(2);
			table.addCell(cell);
			cell = defaultCell("");
			cell.setColspan(2);
			table.addCell(cell);
			table.addCell(defaultCell("Signature"));
			doc.add(table);
			doc.close();
			result = byteArrayOutputStream.toByteArray();
			byteArrayOutputStream.close();
			pdfWriter.close();
		} catch (DocumentException documentException) {
			documentException.printStackTrace();
		} catch (IOException ioException){

		} finally {
			try {
				byteArrayOutputStream.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
		return result;
	}

	private static PdfPCell defaultCell(String s) {
		PdfPCell cell = new PdfPCell( new Paragraph(s));
		cell.setBorderColor(BaseColor.BLACK);
		cell.setPadding(10);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return cell;
	}
}
