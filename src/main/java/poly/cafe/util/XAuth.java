package poly.cafe.util;

import poly.cafe.entity.EmployeeInfo;

public class XAuth {
    private static EmployeeInfo currentUser = null;

    // Đăng nhập: lưu toàn bộ thông tin
    public static void login(EmployeeInfo user) {
        currentUser = user;
    }

    // Đăng xuất: xóa thông tin
    public static void logout() {
        currentUser = null;
    }

    // Kiểm tra đã đăng nhập chưa
    public static boolean isLogin() {
        return currentUser != null;
    }

    // Lấy toàn bộ đối tượng
    public static EmployeeInfo getUser() {
        return currentUser;
    }

    // Các hàm getter thông tin cụ thể
    public static String getUserName() {
        return isLogin() ? currentUser.getUserName() : null;
    }

    public static String getPassWord() {
        return isLogin() ? currentUser.getPassWord() : null;
    }

    public static String getHoTen() {
        return isLogin() ? currentUser.getFullName(): null;
    }

    public static String getGioiTinh() {
        return isLogin() ? currentUser.getGender(): null;
    }

    public static String getCCCD() {
        return isLogin() ? currentUser.getNationalID(): null;
    }

    public static java.util.Date getNgaySinh() {
        return isLogin() ? currentUser.getBirthDate(): null;
    }

    public static String getAddress() {
        return isLogin() ? currentUser.getAddress(): null;
    }

    public static String getPhoneNumber() {
        return isLogin() ? currentUser.getPhoneNumber(): null;
    }

    public static String getEmail() {
        return isLogin() ? currentUser.getEmail() : null;
    }

    public static int getRoleId() {
        return isLogin() && currentUser.getRole() != null ? currentUser.getRole().getRoleId() : -1;
    }
    
    public static String getRole() {
        return isLogin() && currentUser.getRole() != null ? currentUser.getRole().getRole() : null;
    }
    
    public static String getRoleName() {
        return isLogin() && currentUser.getRole() != null ? currentUser.getRole().getRoleName(): null;
    }

    public static String getPhoto() {
        return isLogin() ? currentUser.getPhoto(): null;
    }

    public static String getTrangThai() {
        return isLogin() ? currentUser.getStatus(): null;
    }
    public static boolean isCurrentUser(String userName) {
    return isLogin() && userName.equalsIgnoreCase(currentUser.getUserName());
}
}
