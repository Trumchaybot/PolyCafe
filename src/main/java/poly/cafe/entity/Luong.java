package poly.cafe.entity;

import java.math.BigDecimal;

public class Luong {
    private Integer ID;
    private String UserName;
    private Integer Thang;
    private Integer Nam;
    private BigDecimal TongGioLam;
    private Integer TongSanPham;
    private BigDecimal Tong_luong;

    // Getter - Setter đầy đủ
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public Integer getThang() {
        return Thang;
    }

    public void setThang(Integer Thang) {
        this.Thang = Thang;
    }

    public Integer getNam() {
        return Nam;
    }

    public void setNam(Integer Nam) {
        this.Nam = Nam;
    }


    public Integer getTongSanPham() {
        return TongSanPham;
    }

    public void setTongSanPham(Integer TongSanPham) {
        this.TongSanPham = TongSanPham;
    }

    public BigDecimal getTongGioLam() {
        return TongGioLam;
    }

    public void setTongGioLam(BigDecimal TongGioLam) {
        this.TongGioLam = TongGioLam;
    }

    public BigDecimal getTong_luong() {
        return Tong_luong;
    }

    public void setTong_luong(BigDecimal Tong_luong) {
        this.Tong_luong = Tong_luong;
    }

}
