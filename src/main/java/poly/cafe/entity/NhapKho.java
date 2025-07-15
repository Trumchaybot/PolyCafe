package poly.cafe.entity;

import java.util.Date;

public class NhapKho {
    private int id;
    private String userName;
    private int vatLieuId;
    private int soLuong;
    private Date ngayNhap;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getVatLieuId() {
        return vatLieuId;
    }

    public void setVatLieuId(int vatLieuId) {
        this.vatLieuId = vatLieuId;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
}
