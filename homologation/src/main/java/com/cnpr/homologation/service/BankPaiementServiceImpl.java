package com.cnpr.homologation.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cnpr.homologation.models.BankPaiement;
import com.cnpr.homologation.models.CnprUser;
import com.cnpr.homologation.repository.BankPaiementRepository;

@Service
@Transactional
public class BankPaiementServiceImpl implements BankPaiementService {

	@Autowired
	BankPaiementRepository bankPaiementRepo;

	@Override
	public List<BankPaiement> getAllBankPaiement() {
		// TODO Auto-generated method stub
		return (List<BankPaiement>) bankPaiementRepo.findAll();
	}

	public List<BankPaiement> importBankPayment(List<MultipartFile> multipartfiles) {
		List<BankPaiement> uploadedBankPaiementList = new ArrayList<>();

		if (!multipartfiles.isEmpty()) {

			multipartfiles.forEach(multipartfile -> {
				try {
					@SuppressWarnings("resource")
					XSSFWorkbook workBook = new XSSFWorkbook(multipartfile.getInputStream());

					XSSFSheet sheet = workBook.getSheetAt(0);

					// looping through each row
					BankPaiement payment = null;
					for (int rowIndex = 0; rowIndex < getNumberOfNonEmptyCells(sheet, 0); rowIndex++) {
						// current row
						XSSFRow row = sheet.getRow(rowIndex);
						// skip the first row because it is a header row
						if (rowIndex ==0) {
							continue;
						}

						String reference = getValue(row.getCell(0)).toString();
						Long montant = Long.parseLong(row.getCell(1).getRawValue().toString());
						String devise = getValue(row.getCell(2)).toString();

						payment = new BankPaiement();
						payment.setReference(reference);
						payment.setMontant(montant);
						payment.setDevise(devise);

						uploadedBankPaiementList.add(payment);

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

		}
		return uploadedBankPaiementList;
	}

	private String getValue(Cell cell) {

		return cell.getStringCellValue();
	}

	public static int getNumberOfNonEmptyCells(XSSFSheet sheet, int columnIndex) {
		int numOfNonEmptyCells = 0;
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			if (row != null) {
				XSSFCell cell = row.getCell(columnIndex);
				if (cell != null && cell.getCellType() != CellType.BLANK) {
					numOfNonEmptyCells++;
				}
			}
		}
		return numOfNonEmptyCells;
	}
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 
	public void bulkInsertBanquePayment(List<BankPaiement> uploadedBanquePayementList, CnprUser user) {
		this.bankPaiementRepo.truncateTable();
        String sql = "INSERT INTO public.tb_cnpr_bank_payment (reference, montant, devise, active_status, created_by) VALUES (?, ?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, uploadedBanquePayementList, 100, (ps, ts) -> {
            ps.setString(1, ts.getReference());
            ps.setLong(2, ts.getMontant());
            ps.setString(3, ts.getDevise());
            ps.setBoolean(4, true);
            ps.setLong(5, user.getId());
           
        });
		
	}

}
