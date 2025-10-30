package poly.cafe.dao;

import java.time.LocalDate;
import poly.cafe.entity.Attendance;

public interface AttendanceDAO extends CrudDAO<Attendance, Integer> {
    // Có thể thêm các phương thức đặc thù nếu cần, ví dụ:
    // List<ChamCong> findByUser(String username);
    Attendance findByUserAndDate(String userName, LocalDate workDate);

}
