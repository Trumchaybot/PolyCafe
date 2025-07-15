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
import java.time.LocalTime;

public class ChamCong {
    private int id;
    private String userName;
    private LocalDate ngayLamViec;
    private LocalTime gioVaoCa;
    private LocalTime gioRaCa;

    // Getters and Setters...

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

    public LocalDate getNgayLamViec() {
        return ngayLamViec;
    }

    public void setNgayLamViec(LocalDate ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    public LocalTime getGioVaoCa() {
        return gioVaoCa;
    }

    public void setGioVaoCa(LocalTime gioVaoCa) {
        this.gioVaoCa = gioVaoCa;
    }

    public LocalTime getGioRaCa() {
        return gioRaCa;
    }

    public void setGioRaCa(LocalTime gioRaCa) {
        this.gioRaCa = gioRaCa;
    }
}


