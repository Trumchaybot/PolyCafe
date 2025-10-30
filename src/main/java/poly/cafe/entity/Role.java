package poly.cafe.entity;

public class Role {
    private int roleId;
    private String role;
    private String roleName;
    private float hourlySalary;

    public Role() {}

    public Role(int roleId, String role, String roleName, float hourlySalary) {
        this.roleId = roleId;
        this.role = role;
        this.roleName = roleName;
        this.hourlySalary = hourlySalary;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public float getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(float hourlySalary) {
        this.hourlySalary = hourlySalary;
    }
    @Override
public String toString() {
    return role;
}
// @Override
//public String roleNameToString() {
//    return roleName;
//}
}
