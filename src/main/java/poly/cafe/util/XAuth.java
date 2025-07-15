
package poly.cafe.util;

import poly.cafe.entity.User;

public class XAuth {
    public static User user;

    static {
        user = new User();
        user.setUserName("admin");
        user.setPassword("1");
        user.setEnabled(true);
        user.setRoleId(1); // 1 = quản lý
        user.setPhoto("trump.png");
    }
}
