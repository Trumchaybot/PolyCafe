package poly.cafe.entity;

import java.util.Objects;

public class SanPham {
    private int sanPhamId;
    private String tenSanPham;
    private String moTa;
    private double giaTien;

    // Constructor không tham số
    public SanPham() {
    }

    // Constructor đầy đủ
    public SanPham(int sanPhamId, String tenSanPham, String moTa, double giaTien) {
        this.sanPhamId = sanPhamId;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.giaTien = giaTien;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    // Getters and Setters
    public int getSanPhamId() {
        return sanPhamId;
    }

    public void setSanPhamId(int sanPhamId) {
        this.sanPhamId = sanPhamId;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    // toString
    @Override
    public String toString() {
        return tenSanPham;
    }

    // equals + hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SanPham)) return false;
        SanPham sanPham = (SanPham) o;
        return sanPhamId == sanPham.sanPhamId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sanPhamId);
    }
}
