package poly.cafe.dao;

import java.time.LocalDate;
import java.util.List;
import poly.cafe.entity.Import;

public interface ImportDAO extends CrudDAO<Import, Integer> {
    int insertAndReturnId(Import entity);
    List<Import> findByImportDate(LocalDate fromDate, LocalDate toDate);
    List<Import> findByImportDateAndUsername(LocalDate fromDate, LocalDate toDate, String userName);
}
