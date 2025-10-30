package poly.cafe.dao;

import java.util.List;
import poly.cafe.entity.Salary;

public interface SalaryDAO extends CrudDAO<Salary, Integer> {
    List<Salary> findByCondition(String userName, Integer month, Integer year);
}
