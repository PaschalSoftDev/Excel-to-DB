package Practice.ExcelToDB.repository;

import Practice.ExcelToDB.entity.ExcelToDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ExcelRepository extends JpaRepository<ExcelToDB, Long> {
    ExcelToDB findExcelToDBByCode(String branchCode);
    List<ExcelToDB> findAllByCity(String branchCity);
    ExcelToDB findExcelToDBByEmail(String branchEmail);
}
