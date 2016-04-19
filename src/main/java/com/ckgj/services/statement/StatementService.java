package com.ckgj.services.statement;

import com.ckgj.models.company.Company;
import com.ckgj.models.statement.Period;
import com.ckgj.models.statement.Statement;
import com.ckgj.models.statement.StatementSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StatementService {
    private static final Logger logger = LoggerFactory.getLogger(StatementService.class);
    @Autowired
    private final StatementRepository statementRepository;

    @Autowired
    public  StatementService(StatementRepository statementRepository){
        this.statementRepository = statementRepository;
    }

    public Optional<StatementSheet> getOneSheet(Long statementId) {
        Statement statement = statementRepository.findOne(statementId);
        if (statement == null) {
            return Optional.empty();
        } else {
            StatementSheet sheet = new StatementSheet(statement);
            return Optional.of(sheet);
        }
    }

    public Statement updateStatement(StatementSheet sheet) throws Exception {
        Statement statement = statementRepository.findOne(sheet.getId());
        statement.withCash(sheet.getCash()).withBankAccount(sheet.getBankAccount()).withRevenue(sheet.getRevenue())
                .withIncome(sheet.getIncome()).withCost(sheet.getCost()).withSupplierCost(sheet.getSupplierCost())
                .withSalaryCost(sheet.getSalaryCost()).withTaxCost(sheet.getTaxCost()).withReceivable(sheet.getReceivable())
                .withDebt(sheet.getDebt()).withSupplierDebt(sheet.getSupplierDebt()).withSalaryDebt(sheet.getSalaryDebt())
                .withTaxDebt(sheet.getTaxDebt()).withAsset(sheet.getAsset()).withLiability(sheet.getLiability())
                .withEquity(sheet.getEquity());
        statementRepository.save(statement);
        return statement;
    }

    public List<StatementSheet> sortedSheet(Company company) {
        Iterable<Statement> statements = statementRepository.findAllByCompany(company);
        List<StatementSheet> sheets = new ArrayList<>();
        for (Statement statement : statements) {
            sheets.add(new StatementSheet(statement));
        }
        sheets.sort(StatementSheet.StatementSheetComparator);
        return sheets;
    }

    public Statement saveStatement (InputStream inputStream, Company company, int year, int month) throws IOException, IllegalArgumentException {
        Period period = new Period(year, month);
        if (statementRepository.findOneByCompanyAndPeriod(company, period.getSqlStr()).isPresent()) {
            throw new IllegalArgumentException("该期报表已存在");
        }
        Statement statement = new Statement();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("报表");
            statement.withCash((float)sheet.getRow(2).getCell(3).getNumericCellValue())
                    .withBankAccount((float)sheet.getRow(3).getCell(3).getNumericCellValue())
                    .withRevenue((float)sheet.getRow(5).getCell(3).getNumericCellValue())
                    .withIncome((float)sheet.getRow(6). getCell(3).getNumericCellValue())
                    .withCost((float)sheet.getRow(7).getCell(3).getNumericCellValue())
                    .withSupplierCost((float)sheet.getRow(8).getCell(3).getNumericCellValue())
                    .withSalaryCost((float)sheet.getRow(9).getCell(3).getNumericCellValue())
                    .withTaxCost((float)sheet.getRow(10).getCell(3).getNumericCellValue())
                    .withReceivable((float)sheet.getRow(12).getCell(3).getNumericCellValue())
                    .withDebt((float)sheet.getRow(14).getCell(3).getNumericCellValue())
                    .withSupplierDebt((float)sheet.getRow(15).getCell(3).getNumericCellValue())
                    .withSalaryDebt((float)sheet.getRow(16).getCell(3).getNumericCellValue())
                    .withTaxDebt((float)sheet.getRow(17).getCell(3).getNumericCellValue())
                    .withAsset((float)sheet.getRow(18).getCell(3).getNumericCellValue())
                    .withLiability((float)sheet.getRow(20).getCell(3).getNumericCellValue())
                    .withEquity((float)sheet.getRow(21).getCell(3).getNumericCellValue());

        } catch (Exception e) {
            throw new IllegalArgumentException("上传文件不符合规范");
        }

        statement.withCompany(company).withPeriod(period.getSqlStr());

        statement.setDateCreated(new Date());
        statementRepository.save(statement);
        logger.info(String.format("Finish parse statement of %s(%s), and the id is %s.", company.getId(), period, statement.getId()));
        return statement;
    }

    public Statement deleteOne(Long id) {
        Statement statement = statementRepository.findOne(id);
        statementRepository.delete(statement);
        return statement;
    }
}
