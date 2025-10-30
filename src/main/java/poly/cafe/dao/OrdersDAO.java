package poly.cafe.dao;

import java.time.LocalDate;
import java.util.List;
import poly.cafe.entity.Orders;

public interface OrdersDAO extends CrudDAO<Orders, Integer> {
    List<Orders> findByOrderDate(LocalDate fromDate, LocalDate toDate);
    int insertAndReturnId(Orders entity);
    List<Orders> findByOrderDateAndStatus(LocalDate fromDate, LocalDate toDate, String status);

}
