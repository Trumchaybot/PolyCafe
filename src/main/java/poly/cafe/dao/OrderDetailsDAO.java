package poly.cafe.dao;

import java.util.List;
import poly.cafe.entity.OrderDetails;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails, Integer> {

    public List<OrderDetails> findByOrderId(int orderId);
}
