package Practice.ExcelToDB.service;

import Practice.ExcelToDB.entity.ExcelToDB;
import Practice.ExcelToDB.repository.ExcelRepository;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
    public class ExcelService {

//    String jdbcURL = "jdbc:mysql://localhost:3306/dev";
//    String username = "root";
//    String password = "";
//
//    Connection connection = null;

   @Autowired
    private ExcelRepository excelRepository;

    public void saveExcelToDB(File file) throws IOException {
        List<ExcelToDB> excelToDBList = new ArrayList<>();

        //Obtaining input from the Excel file
        FileInputStream fis = new FileInputStream(file);
        //Creating workbook instance that refers to the excel file
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        //Creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheetAt(0);

        int rowNum = sheet.getLastRowNum();
        for (int i = 1; i <= rowNum; i++) {
            ExcelToDB newBranch = new ExcelToDB();

            newBranch.setName(getCellData(sheet, "NAME", i));
            newBranch.setCode(getCellData(sheet, "CODE", i));
            newBranch.setCity(getCellData(sheet, "CITY", i));
            newBranch.setAddress(getCellData(sheet, "ADDRESS", i));
            newBranch.setEmail(getCellData(sheet, "EMAIL", i));
            newBranch.setPhone(getCellData(sheet, "PHONE", i));
            newBranch.setSwiftCode(getCellData(sheet, "SWIFT_BIC", i));

            String headOffice = getCellData(sheet, "HEAD_OFFICE", i);
            newBranch.setHeadOffice(headOffice.equals("1"));

            String regionalOffice = getCellData(sheet, "REGIONAL_OFFICE", i);
            newBranch.setRegionalOffice(regionalOffice.equals("1"));
            System.out.println(">>>>>>>>>>>>>>>ABOUT_TO<<<<<<<<<<<<<<<<< " + '\n' + newBranch );
            ExcelToDB createdBranch = excelRepository.save(newBranch);
            System.out.println(">>>>>>>>>>>>>>>AFTER<<<<<<<<<<<<<<<<< " + '\n' + createdBranch);
//            excelToDBList.add(createdBranch);
        }
//        return excelToDBList;
    }

//    connection = DriverManager.getConnection(jdbcURL, username, password);
//    connection.setAutoCommit(false);
//
//    String sql = "INSERT INTO BRANCHES (name, code, address, city, email_address, phone_number, head_office_flag, regional_office_flag, swift_bic_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//    PreparedStatement statement = connection.prepareStatement(sql);

    private static String getCellData(Sheet sheet, String columnName, int rowNumber) {
        Row row = sheet.getRow(0);
        Cell cell;
        int lastCellNumber = row.getLastCellNum();
        int columnNum = -1;
        for (int i = 0; i < lastCellNumber; i++) {
            if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(columnName)) {
                columnNum = i;
            }
        }
        row = sheet.getRow(rowNumber);
        cell = row.getCell(columnNum);
        return cell != null ? getCellStringValue(cell) : " ";
    }

    private static String getCellStringValue(Cell cell) {
        String value;
        switch (cell.getCellType()) {
            case NUMERIC:
                    value = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING:
                value = String.valueOf(cell.getStringCellValue());
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}
