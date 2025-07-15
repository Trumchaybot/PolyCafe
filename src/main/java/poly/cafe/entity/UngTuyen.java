/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

/**
 *
 * @author admin
 */
import java.util.Date;

public class UngTuyen {
    private int id;
    private int tuyenDungId;
    private String hoTen;
    private Date ngaySinh;
    private String sdt;
    private String email;
    private String viTriUngTuyen;
    private Date ngayUngTuyen;
    private String trangThai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTuyenDungId() {
        return tuyenDungId;
    }

    public void setTuyenDungId(int tuyenDungId) {
        this.tuyenDungId = tuyenDungId;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getViTriUngTuyen() {
        return viTriUngTuyen;
    }

    public void setViTriUngTuyen(String viTriUngTuyen) {
        this.viTriUngTuyen = viTriUngTuyen;
    }

    public Date getNgayUngTuyen() {
        return ngayUngTuyen;
    }

    public void setNgayUngTuyen(Date ngayUngTuyen) {
        this.ngayUngTuyen = ngayUngTuyen;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    
}
