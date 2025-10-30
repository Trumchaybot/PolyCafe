package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.RoleDAO;
import poly.cafe.entity.Role;
import poly.cafe.util.XJdbc;

public class RoleDAOImpl implements RoleDAO {

    final String INSERT_SQL = "INSERT INTO Role (role, role_name, hourly_salary) VALUES (?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Role SET role = ?, role_name = ?, hourly_salary = ? WHERE role_id = ?";
    final String DELETE_SQL = "DELETE FROM Role WHERE role_id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Role";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Role WHERE role_id = ?";

    @Override
    public Role create(Role entity) {
        XJdbc.executeUpdate(INSERT_SQL, entity.getRole(), entity.getRoleName(), entity.getHourlySalary());
        return entity;
    }

    @Override
    public void update(Role entity) {
        XJdbc.executeUpdate(UPDATE_SQL, entity.getRole(), entity.getRoleName(), entity.getHourlySalary(), entity.getRoleId());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Role> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Role findById(Integer id) {
        List<Role> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<Role> selectBySql(String sql, Object... args) {
        List<Role> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                Role r = new Role();
                r.setRoleId(rs.getInt("role_id"));
                r.setRole(rs.getString("role"));
                r.setRoleName(rs.getString("role_name"));
                r.setHourlySalary(rs.getFloat("hourly_salary"));
                list.add(r);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
