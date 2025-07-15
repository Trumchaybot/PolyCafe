package poly.cafe.dao.impl;

import poly.cafe.dao.RoleDAO;
import poly.cafe.entity.Role;
import poly.cafe.util.XJdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {

    private final String insertSql = "INSERT INTO role (role_ID, role) VALUES (?, ?)";
    private final String updateSql = "UPDATE role SET role = ? WHERE role_ID = ?";
    private final String deleteSql = "DELETE FROM role WHERE role_ID = ?";
    private final String findByIdSql = "SELECT * FROM role WHERE role_ID = ?";
    private final String findAllSql = "SELECT * FROM role";

    @Override
    public Role create(Role role) {
        XJdbc.executeUpdate(insertSql, role.getRoleId(), role.getRole());
        return role;
    }

    @Override
    public void update(Role role) {
        XJdbc.executeUpdate(updateSql, role.getRole(), role.getRoleId());
    }

    @Override
    public void deleteById(int id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public Role findById(int id) {
        try (ResultSet rs = XJdbc.executeQuery(findByIdSql, id)) {
            if (rs.next()) {
                return new Role(rs.getInt("role_ID"), rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Role> findAll() {
        List<Role> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(findAllSql)) {
            while (rs.next()) {
                list.add(new Role(rs.getInt("role_ID"), rs.getString("role")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
