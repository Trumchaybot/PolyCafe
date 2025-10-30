/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao;

import poly.cafe.entity.OTP;

/**
 *
 * @author admin
 */
public interface OTPDAO extends CrudDAO<OTP, Integer>{
    OTP findByUserName(String userName);
    OTP findByEmail(String email);
    OTP findLatestByUser(String userName, String email); 
}
