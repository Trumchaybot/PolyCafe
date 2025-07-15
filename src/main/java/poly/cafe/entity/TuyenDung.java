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

public class TuyenDung {
    private int ID;
private String Vi_tri;
private String Mo_ta;
private String Yeu_cau;
private int So_luong;
private Date Ngay_bat_dau;
private Date Ngay_ket_thuc;
private String Trang_thai;
private String UserName;
// getters & setters đầy đủ

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getVi_tri() {
        return Vi_tri;
    }

    public void setVi_tri(String Vi_tri) {
        this.Vi_tri = Vi_tri;
    }

    public String getMo_ta() {
        return Mo_ta;
    }

    public void setMo_ta(String Mo_ta) {
        this.Mo_ta = Mo_ta;
    }

    public String getYeu_cau() {
        return Yeu_cau;
    }

    public void setYeu_cau(String Yeu_cau) {
        this.Yeu_cau = Yeu_cau;
    }

    public int getSo_luong() {
        return So_luong;
    }

    public void setSo_luong(int So_luong) {
        this.So_luong = So_luong;
    }

    public Date getNgay_bat_dau() {
        return Ngay_bat_dau;
    }

    public void setNgay_bat_dau(Date Ngay_bat_dau) {
        this.Ngay_bat_dau = Ngay_bat_dau;
    }

    public Date getNgay_ket_thuc() {
        return Ngay_ket_thuc;
    }

    public void setNgay_ket_thuc(Date Ngay_ket_thuc) {
        this.Ngay_ket_thuc = Ngay_ket_thuc;
    }

    public String getTrang_thai() {
        return Trang_thai;
    }

    public void setTrang_thai(String Trang_thai) {
        this.Trang_thai = Trang_thai;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

}
