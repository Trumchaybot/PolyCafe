package poly.cafe.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class XDate {

    public static final String PATTERN_FULL = "dd/MM/yyyy HH:mm:ss";
    public static final String PATTERN_SHORT = "dd/MM/yyyy";
    private static final SimpleDateFormat formater = new SimpleDateFormat();

    // Lấy thời gian hiện tại
    public static Date now() {
        return new Date();
    }

    // Chuyển từ String sang Date với pattern tùy chọn
    public static Date parse(String dateTime, String pattern) {
        formater.applyLocalizedPattern(pattern);
        try {
            return formater.parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
    }

    // Chuyển từ String sang Date với pattern mặc định (MM/dd/yyyy)
    public static Date parse(String dateTime) {
        return parse(dateTime, PATTERN_SHORT);
    }

    // Chuyển từ Date sang String với pattern tùy chọn
    public static String format(Date dateTime, String pattern) {
        if (dateTime == null) {
            return "";
        }
        formater.applyPattern(pattern);
        return formater.format(dateTime);
    }

    // Chuyển từ Date sang String với pattern mặc định
    public static String format(Date dateTime) {
        return format(dateTime, PATTERN_SHORT);
    }

    // Cộng hoặc trừ ngày (số ngày dương để cộng, âm để trừ)
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    // Tính số ngày giữa 2 mốc thời gian
    public static long diffDays(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        return diff / (1000 * 60 * 60 * 24);
    }

    // Kiểm tra ngày có hợp lệ không
    public static boolean isValidDate(String dateStr, String pattern) {
        formater.applyPattern(pattern);
        formater.setLenient(false);
        try {
            formater.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // ====== HỖ TRỢ SWING: chọn ngày bằng lịch ======
    // Hiển thị dialog chọn ngày (JDateChooser)
    public static Date chooseDate(JComponent parent) {
        try {
            // Thử dùng JDateChooser (cần thư viện JCalendar)
            Class.forName("com.toedter.calendar.JDateChooser");
            com.toedter.calendar.JDateChooser chooser = new com.toedter.calendar.JDateChooser();
            chooser.setDateFormatString(PATTERN_SHORT);
            int option = JOptionPane.showConfirmDialog(parent, chooser, "Chọn ngày",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                return chooser.getDate();
            }
        } catch (ClassNotFoundException e) {
            // Nếu chưa cài JCalendar → dùng JSpinner thay thế
            SpinnerDateModel model = new SpinnerDateModel();
            JSpinner spinner = new JSpinner(model);
            spinner.setEditor(new JSpinner.DateEditor(spinner, PATTERN_SHORT));
            int option = JOptionPane.showConfirmDialog(parent, spinner, "Chọn ngày",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                return (Date) spinner.getValue();
            }
        }
        return null;
    }

    // Demo test nhanh
    public static void main(String[] args) {
        // Test parse và format
        Date date = XDate.parse("24/12/2024", "dd/MM/yyyy");
        String text = XDate.format(date, "dd/MMM/yyyy");
        System.out.println("Ngày format: " + text);

        // Test cộng ngày
        Date after7 = XDate.addDays(XDate.now(), 7);
        System.out.println("Sau 7 ngày: " + XDate.format(after7));

        // Test chọn ngày trên giao diện
        Date chosen = XDate.chooseDate(null);
        if (chosen != null) {
            System.out.println("Bạn chọn: " + XDate.format(chosen));
        } else {
            System.out.println("Không chọn ngày nào.");
        }
    }
}
