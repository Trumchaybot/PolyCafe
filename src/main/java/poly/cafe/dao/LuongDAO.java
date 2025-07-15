package poly.cafe.dao;

import java.util.List;
import poly.cafe.entity.Luong;

public interface LuongDAO extends CrudDAO<Luong, Integer> {
    // Có thể mở rộng thêm phương thức đặc biệt nếu cần
    List<Object[]> findAllWithHoTen();
    List<Luong> findByCondition(String userName, Integer thang, Integer nam);

}
