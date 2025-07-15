/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.ui.component.controller;

/**
 *
 * @author admin
 */
public interface QuanLyNhanVienController {
    void open();
    void fillToTable();
    void checkAll(); // Tích chọn tất cả các hàng trên bảng
    void uncheckAll(); // Bỏ tích chọn tất cả các hàng trên bảng
    void deleteCheckedItems();
    void edit();
}
