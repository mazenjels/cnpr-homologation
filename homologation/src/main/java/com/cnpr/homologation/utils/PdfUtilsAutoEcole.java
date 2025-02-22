package com.cnpr.homologation.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.cnpr.homologation.models.CnprAutoEcole;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
public class PdfUtilsAutoEcole {

	private List<CnprAutoEcole> majList;
	private String title;
	private String departenement;
	private String typeMaj;

	public PdfUtilsAutoEcole(List<CnprAutoEcole> majList, String title, String departenement,
			String typeMaj) {
		// super();
		this.majList = majList;
		this.title = title;
		this.departenement = departenement;
		this.typeMaj = typeMaj;

	}

	private void writeTableHeader(PdfPTable table) {

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(9.0f);
		PdfPCell cell = new PdfPCell();
		// cell.setBackgroundColor(Color.BLUE);
		// cell.setPadding(5);


		/////////////////////////////////////////////////////////////

		// font.setColor(Color.WHITE);

		cell = new PdfPCell();
		cell.setPhrase(new Phrase("No.", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell();
		cell.setPhrase(new Phrase("CODE UNIQUE", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell();
		cell.setPhrase(new Phrase("DENOMINATION", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell();
		cell.setPhrase(new Phrase("PROMOTEUR", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell();
		cell.setPhrase(new Phrase("TELEPHONE", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell();
		cell.setPhrase(new Phrase("EMAIL", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell();
		cell.setPhrase(new Phrase("ADRESSE", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell();
		cell.setPhrase(new Phrase("COMMUNE", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell();
		cell.setPhrase(new Phrase("DISTRICT", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell();
		cell.setPhrase(new Phrase("PROVINCE", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(9.0f);
		PdfPCell cell = null;
		long count = 1;
		for (CnprAutoEcole maj : majList) {

			cell = new PdfPCell();
			cell.setPhrase(new Phrase(count + ""));
			table.addCell(cell);

			cell = new PdfPCell();
			cell.setPhrase(new Phrase(maj.getCodeUnique(), font));
			table.addCell(cell);

			cell = new PdfPCell();
			cell.setPhrase(new Phrase(maj.getDesignation(), font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell();
			cell.setPhrase(new Phrase(maj.getDirecteurGerant(), font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell();
			cell.setPhrase(new Phrase(maj.getPhone(), font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell();
			cell.setPhrase(new Phrase(maj.getEmail() + "", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell();
			cell.setPhrase(new Phrase(maj.getAdresseNumber() + ", "+maj.getAdresseAvenue() + "", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell();
			cell.setPhrase(new Phrase(maj.getCommune().getDesignation() + "", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setPhrase(new Phrase(maj.getDistrict().getDesignation() + "", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setPhrase(new Phrase(maj.getProvince().getDesignation()+ "", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			count++;
		}
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4.rotate());
		PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

		PdfHeader event = new PdfHeader("MINSITERE DES TRANSPORTS * " + title);
		writer.setPageEvent(event);

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

		// font.setColor(Color.BLUE);

		String currDir = "";

		String os = System.getProperty("os.name").toLowerCase();
		//if (os.contains("win")) {
			// Operating system is based on Windows
			currDir = "/cnpr/logo/cnpr.png";

			Image To_be_Added = Image.getInstance(currDir);
			// To_be_Added.setAbsolutePosition(0f, 0f);
			To_be_Added.setAlignment(Image.RIGHT);
			To_be_Added.scaleAbsolute(75, 75);

			 To_be_Added.setAlignment(Image.RIGHT | Image.TEXTWRAP);
			 To_be_Added.setBorder(Image.BOX);
			 To_be_Added.setBorderWidth(15);

			document.add(To_be_Added);
		//}

/////////////////////////////////////////////////////////////////////
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(33f);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.setWidths(new float[] { 10.0f });
		table.setSpacingAfter(30);

		PdfPCell cell = null;
		font.setSize(10);

		cell = new PdfPCell();
		cell.setPhrase(new Phrase("MINISTERE DES TRANSPORTS", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		table.addCell(cell);

		Image img = Image.getInstance(currDir);
		img.setAlignment(Image.ALIGN_CENTER);//
		img.scaleAbsolute(50f, 50f);
		cell = new PdfPCell(img);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		table.addCell(cell);

// table.addCell(createImageCell(currDir));

		cell = new PdfPCell();
		cell.setPhrase(new Phrase("", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		table.addCell(cell);

		cell = new PdfPCell();
		cell.setPhrase(new Phrase(departenement, font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		table.addCell(cell);
		document.add(table);

		font.setSize(18);
		Paragraph p = new Paragraph(title, font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);

		font.setSize(14);
		p = new Paragraph(typeMaj, font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);
///////////////////////////////////////////////////////////////////////

		table = new PdfPTable(10);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 0.5f, 1.0f, 2f, 3f, 1f, 1f, 3f, 2f,3f, 2f });
		table.setSpacingBefore(30);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}
//	public static ByteArrayOutputStream generatePdfStream(List<Map<String, Object>> queryResults)
//			throws DocumentException {
//		Document document = new Document();
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		PdfWriter.getInstance(document, outputStream);
//		document.open();
//		// Write column names
//		Map<String, Object> firstRow = queryResults.get(0);
//		for (String column : firstRow.keySet()) {
//			Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
//			Paragraph paragraph = new Paragraph(column, boldFont);
//			document.add(paragraph);
//		}
//		document.add(new Paragraph("\n"));
//		// Write data rows
//		for (Map<String, Object> row : queryResults) {
//			for (Object value : row.values()) {
//				Paragraph paragraph = new Paragraph(value.toString());
//				document.add(paragraph);
//			}
//			document.add(new Paragraph("\n"));
//		}
//		document.close();
//		return outputStream;
//	}
}
