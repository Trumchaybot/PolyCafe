package poly.cafe.dao;

import java.util.List;
import poly.cafe.entity.TotalRevenue;

public interface TotalRevenueDAO extends CrudDAO<TotalRevenue, Integer> {
    // Có thể thêm các hàm riêng nếu cần
    List<TotalRevenue> findByCondition(Integer month, Integer year);
}
