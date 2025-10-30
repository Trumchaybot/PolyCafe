package poly.cafe.dao;

import java.util.List;
import poly.cafe.entity.TotalExpenses;

public interface TotalExpensesDAO extends CrudDAO<TotalExpenses, Integer> {
    // Có thể thêm các phương thức mở rộng nếu cần
    List<TotalExpenses> findByCondition(Integer month, Integer year);
}
