    package poly.cafe.ui.Controller;

import poly.cafe.entity.Attendance;

public interface ChamCongController {
    
    // Dùng để lưu dữ liệu chấm công mới
    void insert();

    void vaoCa();
    void raCa();
    void fill(Attendance chamCong);
    void open();
}
