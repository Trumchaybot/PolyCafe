package poly.cafe.dao;

import java.util.List;
import poly.cafe.entity.ChiTietKhuVuc;

public interface ChiTietKhuVucDAO extends CrudDAO<ChiTietKhuVuc, Integer> {
    // ✅ Thêm phương thức tìm chi tiết theo khu vực
    List<ChiTietKhuVuc> findByKhuVucId(int khuVucId);
}
