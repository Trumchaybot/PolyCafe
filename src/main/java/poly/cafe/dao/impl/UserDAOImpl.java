package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.UserDAO;
import poly.cafe.entity.User;
import poly.cafe.util.XJdbc;

public class UserDAOImpl implements UserDAO {

    final String INSERT_SQL = "INSERT INTO Users (UserName, Password, Enabled, role_ID, Photo) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Users SET Password = ?, Enabled = ?, role_ID = ?, Photo = ? WHERE UserName = ?";
    final String DELETE_SQL = "DELETE FROM Users WHERE UserName = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Users";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Users WHERE UserName = ?";

    @Override
    public User create(User entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getUserName(),
                entity.getPassword(),
                entity.isEnabled(),
                entity.getRoleId(),
                entity.getPhoto()
        );
        return entity;
    }

    @Override
    public void update(User entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getPassword(),
                entity.isEnabled(),
                entity.getRoleId(),
                entity.getPhoto(),
                entity.getUserName()
        );
    }

    @Override
    public void deleteById(String userName) {
        XJdbc.executeUpdate(DELETE_SQL, userName);
    }

    @Override
    public User findById(String userName) {
        try (ResultSet rs = XJdbc.executeQuery(SELECT_BY_ID_SQL, userName)) {
            if (rs.next()) {
                return mapToUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(SELECT_ALL_SQL)) {
            while (rs.next()) {
                list.add(mapToUser(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private User mapToUser(ResultSet rs) throws Exception {
        User u = new User();
        u.setUserName(rs.getString("UserName"));
        u.setPassword(rs.getString("Password"));
        u.setEnabled(rs.getBoolean("Enabled"));
        u.setRoleId(rs.getInt("role_ID"));
        u.setPhoto(rs.getString("Photo"));
        return u;
    }

    @Override
    public List<String> getAllUserNames() {
        List<String> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery("SELECT UserName FROM Users")) {
            while (rs.next()) {
                list.add(rs.getString("UserName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Object[]> findAllWithRole() {
        List<Object[]> list = new ArrayList<>();
        String sql = """
            SELECT u.UserName, u.PassWord, u.Enabled, r.role, u.Photo
            FROM Users u
            JOIN role r ON u.role_ID = r.role_ID
        """;
        try (ResultSet rs = XJdbc.executeQuery(sql)) {
            while (rs.next()) {
                Object[] row = {
                    rs.getString("UserName"),
                    rs.getString("PassWord"),
                    rs.getInt("Enabled"),
                    rs.getString("role"),
                    rs.getString("Photo")
                };
                list.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
