/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao;

import java.time.LocalDate;
import java.util.List;
import poly.cafe.entity.DonHang;

/**
 *
 * @author admin
 */
public interface DonHangDAO extends CrudDAO<DonHang, Integer> {
     List<DonHang> findByNgayDat(LocalDate tuNgay, LocalDate denNgay);
}
