/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.ui.manager.controller;

import poly.cafe.entity.AreaDetails;

/**
 *
 * @author admin
 */
public interface ChiTietKhuVucController{
    void fillToTable();
    void open();
    void checkAll(); // Tích chọn tất cả các hàng trên bảng
    void uncheckAll(); // Bỏ tích chọn tất cả các hàng trên bảng
    void deleteCheckedItems();
}

