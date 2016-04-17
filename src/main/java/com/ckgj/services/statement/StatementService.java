package com.ckgj.services.statement;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhaok_000 on 4/17/2016.
 */
@Service
public class StatementService {
    @Autowired
    private final StatementRepository statementRepository;

    @Autowired
    public  StatementService(StatementRepository statementRepository){
        this.statementRepository = statementRepository;
    }

    public void parseeXlsx(InputStream inputStream) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//        XSSFCell cell = null;
        XSSFSheet sheet = workbook.getSheet("报表");
        double d = sheet.getRow(2).getCell(3).getNumericCellValue();
        System.out.println(sheet.getRow(2).getCell(3).getNumericCellValue());
        System.out.println(sheet.getRow(2).getCell(4).getStringCellValue());
    }
}
