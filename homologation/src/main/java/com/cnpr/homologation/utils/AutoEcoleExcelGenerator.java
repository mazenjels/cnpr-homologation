package com.cnpr.homologation.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;

import com.cnpr.homologation.models.CnprAutoEcole;

public class AutoEcoleExcelGenerator {

	private List<CnprAutoEcole> listTimeSheet;
	// private XSSFWorkbook workbook;
	private SXSSFWorkbook workbook1;
	// private XSSFSheet sheet;
	private SXSSFSheet sheet1;

	public AutoEcoleExcelGenerator(String title, List<CnprAutoEcole> listTimeSheet) {
		this.listTimeSheet = listTimeSheet;
		// workbook = new XSSFWorkbook();
		workbook1 = new SXSSFWorkbook(100);
	}

	private void writeHeader() {
		// sheet = workbook.createSheet("Alpha");
		sheet1 = (SXSSFSheet) workbook1.createSheet("Alpha");
		sheet1.setRandomAccessWindowSize(100);
		// Row row = sheet.createRow(0);
		// CellStyle style = workbook.createCellStyle();
		// XSSFFont font = workbook.createFont();

		SXSSFRow row = sheet1.createRow(0);
		CellStyle style1 = workbook1.createCellStyle();
		XSSFFont font1 = (XSSFFont) workbook1.createFont();

		SXSSFCell cell = (SXSSFCell) row.createCell(0);

		cell.setCellValue("ID  unique");

		cell = (SXSSFCell) row.createCell(1);
		cell.setCellValue("Denomination");

		cell = (SXSSFCell) row.createCell(2);
		cell.setCellValue("Date d'enregistrement");

		cell = (SXSSFCell) row.createCell(3);
		cell.setCellValue("Telephone");

		cell = (SXSSFCell) row.createCell(4);
		cell.setCellValue("Email");

		cell = (SXSSFCell) row.createCell(5);
		cell.setCellValue("Adresse");

	}

	private void createCell(SXSSFRow row, int columnCount, Object valueOfCell, CellStyle style) {
		// sheet1.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (valueOfCell instanceof Integer) {
			cell.setCellValue((Integer) valueOfCell);
		} else if (valueOfCell instanceof Long) {
			cell.setCellValue((Long) valueOfCell);
		} else if (valueOfCell instanceof String) {
			cell.setCellValue((String) valueOfCell);
		} else if (valueOfCell == null) {
			cell.setCellValue((""));
		} else {
			cell.setCellValue((Boolean) valueOfCell);
		}

		cell.setCellStyle(style);

	}

	private void write() {
		int rowCount = 1;
		// CellStyle style = workbook.createCellStyle();
		CellStyle style1 = workbook1.createCellStyle();
		// XSSFFont font = workbook.createFont();
		XSSFFont font1 = (XSSFFont) workbook1.createFont();
		// font.setFontHeight(14);
		// style.setFont(font);

		font1.setFontHeight(14);
		style1.setFont(font1);

		sheet1 = (SXSSFSheet) workbook1.createSheet("Prestations");
		sheet1.setRandomAccessWindowSize(100);
		SXSSFRow row = (SXSSFRow) sheet1.createRow(0);

		Iterator<CnprAutoEcole> it = listTimeSheet.stream().iterator();
		int rowNumber = 1;
		SXSSFCell cell = null;
		row = (SXSSFRow) sheet1.createRow(0);

		cell = (SXSSFCell) row.createCell(0);
		cell.setCellValue("ID  unique");

		cell = (SXSSFCell) row.createCell(1);
		cell.setCellValue("Denomination");

		cell = (SXSSFCell) row.createCell(2);
		cell.setCellValue("Promoteur");

		cell = (SXSSFCell) row.createCell(3);
		cell.setCellValue("Telephone");

		cell = (SXSSFCell) row.createCell(4);
		cell.setCellValue("Email");

		cell = (SXSSFCell) row.createCell(5);
		cell.setCellValue("Adresse");
		
		cell = (SXSSFCell) row.createCell(6);
		cell.setCellValue("Commune");
		
		cell = (SXSSFCell) row.createCell(7);
		cell.setCellValue("District");
		
		cell = (SXSSFCell) row.createCell(8);
		cell.setCellValue("Province");
		

		while (it.hasNext()) {
			CnprAutoEcole record = (CnprAutoEcole) it.next();
			row = (SXSSFRow) sheet1.createRow(rowNumber++);
				cell = (SXSSFCell) row.createCell(0);
				if(record.getCodeUnique()==null) {
					cell.setCellValue("EN attente");
				}else {
					cell.setCellValue(record.getCodeUnique());
				}
				

				cell = (SXSSFCell) row.createCell(1);
				cell.setCellValue(record.getDesignation());

				cell = (SXSSFCell) row.createCell(2);
				cell.setCellValue(record.getDirecteurGerant());

				cell = (SXSSFCell) row.createCell(3);
				cell.setCellValue(record.getPhone());

				cell = (SXSSFCell) row.createCell(4);
				cell.setCellValue(record.getEmail());

				cell = (SXSSFCell) row.createCell(5);
				cell.setCellValue(record.getAdresseNumber() + " " + record.getAdresseAvenue());
				
				cell = (SXSSFCell) row.createCell(6);
				cell.setCellValue(record.getCommune().getDesignation() );
				
				cell = (SXSSFCell) row.createCell(7);
				cell.setCellValue(record.getDistrict().getDesignation());
				
				cell = (SXSSFCell) row.createCell(8);
				cell.setCellValue(record.getProvince().getDesignation());

			
		}

	}

	public void generateExcelFile(HttpServletResponse response) throws IOException {
		// writeHeader();
		write();
		ServletOutputStream outputStream = response.getOutputStream();

		workbook1.write(outputStream);
		workbook1.close();
		outputStream.close();
	}
}
