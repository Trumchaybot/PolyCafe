package poly.cafe.entity;

public class User {
    private String userName;
    private String password;
    private boolean enabled;
    private String photo;
    private int roleId;

    // Constructor không tham số
    public User() {
        this.photo = "photo.png";
    }

    // Constructor đầy đủ tham số
    public User(String userName, String password, boolean enabled, int roleId, String photo) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.roleId = roleId;
        this.photo = (photo != null) ? photo : "photo.png";
    }

    // Getters và Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    // equals(), hashCode(), toString()
    @Override
    public String toString() {
        return "User{" +
                "username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", photo='" + photo + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (enabled != user.enabled) return false;
        if (roleId != user.roleId) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return photo != null ? photo.equals(user.photo) : user.photo == null;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + roleId;
        return result;
    }
}
