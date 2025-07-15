//package poly.cafe.util;
//
//import java.time.LocalDate;
//import java.util.Date;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@AllArgsConstructor
//@Data
//public class TimeRange {
//
//    private Date begin = new Date();
//    private Date end = new Date();
//    
//    private TimeRange(LocalDate begin, LocalDate end) {
//        this(java.sql.Date.valueOf(begin), java.sql.Date.valueOf(end));
//    }
//    
//    public static TimeRange today() {
//        LocalDate begin = LocalDate.now();
//        return new TimeRange(begin, begin.plusDays(1));
//    }
//    
//    public static TimeRange thisWeek() {
//        LocalDate now = LocalDate.now();
//        LocalDate begin = now.minusDays(now.getDayOfWeek().getValue() - 1);
//        return new TimeRange(begin, begin.plusDays(7));
//    }
//    
//    public static TimeRange thisMonth() {
//        LocalDate now = LocalDate.now();
//        LocalDate begin = now.withDayOfMonth(1);
//        return new TimeRange(begin, begin.plusDays(now.lengthOfMonth()));
//    }
//    
//    public static TimeRange thisQuarter() {
//        LocalDate now = LocalDate.now();
//        int firstMonth = now.getMonth().firstMonthOfQuarter().getValue();
//        LocalDate begin = now.withMonth(firstMonth).withDayOfMonth(1);
//        return new TimeRange(begin, begin.plusMonths(3));
//    }
//    
//    public static TimeRange thisYear() {
//        LocalDate now = LocalDate.now();
//        LocalDate begin = now.withMonth(1).withDayOfMonth(1);
//        return new TimeRange(begin, begin.plusMonths(12));
//    }
//}
package poly.cafe.util;

import java.time.LocalDate;
import java.util.Date;

public class TimeRange {

    private Date begin;
    private Date end;

    // Constructor dùng Date
    public TimeRange(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

    // Constructor dùng LocalDate
    private TimeRange(LocalDate begin, LocalDate end) {
        this(java.sql.Date.valueOf(begin), java.sql.Date.valueOf(end));
    }

    // Getters and Setters
    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    // Static methods để lấy khoảng thời gian mặc định
    public static TimeRange today() {
        LocalDate now = LocalDate.now();
        return new TimeRange(now, now.plusDays(1));
    }

    public static TimeRange thisWeek() {
        LocalDate now = LocalDate.now();
        LocalDate begin = now.minusDays(now.getDayOfWeek().getValue() - 1);
        return new TimeRange(begin, begin.plusDays(7));
    }

    public static TimeRange thisMonth() {
        LocalDate now = LocalDate.now();
        LocalDate begin = now.withDayOfMonth(1);
        return new TimeRange(begin, begin.plusDays(now.lengthOfMonth()));
    }

    public static TimeRange thisQuarter() {
        LocalDate now = LocalDate.now();
        int firstMonth = now.getMonth().firstMonthOfQuarter().getValue();
        LocalDate begin = now.withMonth(firstMonth).withDayOfMonth(1);
        return new TimeRange(begin, begin.plusMonths(3));
    }

    public static TimeRange thisYear() {
        LocalDate now = LocalDate.now();
        LocalDate begin = now.withMonth(1).withDayOfMonth(1);
        return new TimeRange(begin, begin.plusMonths(12));
    }
}
