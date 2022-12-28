package Practice.ExcelToDB;

import Practice.ExcelToDB.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class ExcelToDbApplication implements CommandLineRunner {

    @Autowired
    private ExcelService excelService;

    public static void main(String[] args) {
        SpringApplication.run(ExcelToDbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        excelService.saveExcelToDB(new File("C:\\Users\\PASCHAL\\Desktop\\Java\\BRANCH.xls"));
    }
}
