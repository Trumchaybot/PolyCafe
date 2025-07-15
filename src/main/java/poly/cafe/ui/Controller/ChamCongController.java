    package poly.cafe.ui.Controller;

import poly.cafe.entity.ChamCong;

public interface ChamCongController {
    
    // Dùng để lưu dữ liệu chấm công mới
    void insert();

    void vaoCa();
    void raCa();
    void fill(ChamCong chamCong);
    void open();
}
