package poly.cafe.dao;

import java.time.LocalDate;
import poly.cafe.entity.ChamCong;

public interface ChamCongDAO extends CrudDAO<ChamCong, Integer> {
    // Có thể thêm các phương thức đặc thù nếu cần, ví dụ:
    // List<ChamCong> findByUser(String username);
    ChamCong findByUserAndDate(String userName, LocalDate ngayLamViec);

}
