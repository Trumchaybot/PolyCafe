/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

/**
 *
 * @author admin
 */
import java.time.LocalDate;
import java.util.Date;

public class DonHang {
    private int donHangId;
    private String tenKhachHang;
   private LocalDate ngayDat;
private LocalDate ngayGiao;
    private double tongTien;
    private String trangThai;

    public double getTongTien() {
        return tongTien;
    }

    // Getters and Setters
    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getDonHangId() {
        return donHangId;
    }

    public void setDonHangId(int donHangId) {
        this.donHangId = donHangId;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public LocalDate getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDate ngayDat) {
        this.ngayDat = ngayDat;
    }

    public LocalDate getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(LocalDate ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
    
