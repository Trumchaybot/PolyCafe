/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

import java.util.Date;

/**
 *
 * @author admin
 */
public class OTP {
    private int idOTP;
    private String userName;
    private String email;
    private String otpCode;
    private Date createdAt;
    private Date expiredAt;

    public OTP() {
    }

    public OTP(int idOTP, String userName, String email, String otpCode, Date createdAt, Date expiredAt) {
        this.idOTP = idOTP;
        this.userName = userName;
        this.email = email;
        this.otpCode = otpCode;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
    }

    

    public int getIdOTP() {
        return idOTP;
    }

    public void setIdOTP(int idOTP) {
        this.idOTP = idOTP;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }
}
