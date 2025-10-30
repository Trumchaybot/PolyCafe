package poly.cafe.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance {
    private int id;
    private String userName;
    private LocalDate workDate;
    private LocalTime checkinTime;
    private LocalTime checkoutTime;
    private float totalHour;

    public Attendance() {
    }

    public Attendance(int id, String userName, LocalDate workDate, LocalTime checkinTime, LocalTime checkoutTime, float totalHour) {
        this.id = id;
        this.userName = userName;
        this.workDate = workDate;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.totalHour = totalHour;
    }

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

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public LocalTime getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalTime checkinTime) {
        this.checkinTime = checkinTime;
    }

    public LocalTime getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(LocalTime checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public float getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(float totalHour) {
        this.totalHour = totalHour;
    }

    
}
