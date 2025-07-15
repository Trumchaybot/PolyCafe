package poly.cafe.entity;

import java.sql.Date;

public class ChiTietKhuVuc {
    private int id;
    private int khuVucId;
    private String userName;
    private Date ngayPhanCong;

    public ChiTietKhuVuc() {
    }

    public ChiTietKhuVuc(int id, int khuVucId, String userName, Date ngayPhanCong) {
        this.id = id;
        this.khuVucId = khuVucId;
        this.userName = userName;
        this.ngayPhanCong = ngayPhanCong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKhuVucId() {
        return khuVucId;
    }

    public void setKhuVucId(int khuVucId) {
        this.khuVucId = khuVucId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getNgayPhanCong() {
        return ngayPhanCong;
    }

    public void setNgayPhanCong(Date ngayPhanCong) {
        this.ngayPhanCong = ngayPhanCong;
    }
}
