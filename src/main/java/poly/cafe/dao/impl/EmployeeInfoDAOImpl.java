package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import poly.cafe.entity.Role;
import poly.cafe.entity.EmployeeInfo;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.EmployeeInfoDAO;

public class EmployeeInfoDAOImpl implements EmployeeInfoDAO {

    final String INSERT_SQL = 
    "INSERT INTO EmployeeInfo (UserName, PassWord, FullName, Gender, NationalID, BirthDate, Address, PhoneNumber, email, role_id, photo, Status) " +
    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

final String UPDATE_SQL = 
    "UPDATE EmployeeInfo " +
    "SET PassWord=?, FullName=?, Gender=?, NationalID=?, BirthDate=?, Address=?, PhoneNumber=?, email=?, role_id=?, photo=?, Status=? " +
    "WHERE UserName=?";

final String DELETE_SQL = 
    "DELETE FROM EmployeeInfo WHERE UserName=?";

final String SELECT_ALL_SQL = "SELECT * FROM v_EmployeeInfo";
final String SELECT_BY_ID_SQL = "SELECT * FROM v_EmployeeInfo WHERE UserName=?";
final String SELECT_FULLNAME_BY_USERNAME_SQL = "SELECT FullName FROM EmployeeInfo WHERE UserName = ?";
final String SELECT_BY_USERNAME_SQL = "SELECT nv.*, r.role_ID, r.role_name "
                + // thêm dấu cách sau 'r.role'
                "FROM EmployeeInfo nv "
                + "LEFT JOIN Role r ON nv.role_ID = r.role_ID "
                + "WHERE nv.UserName = ?";
final String UPDATE_PASSWORD_SQL = "UPDATE EmployeeInfo SET PassWord = ? WHERE UserName = ? AND Email = ?";

    @Override
    public EmployeeInfo create(EmployeeInfo entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getUserName(),
                entity.getPassWord(),
                entity.getFullName(),
                entity.getGender(),
                entity.getNationalID(),
                entity.getBirthDate()== null ? null : new java.sql.Date(entity.getBirthDate().getTime()),
                entity.getAddress(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getRole() == null ? null : entity.getRole().getRoleId(),
                entity.getPhoto(),
                entity.getStatus());
        return entity;
    }

    public void update(EmployeeInfo entity) {
        try {
            XJdbc.executeUpdate(UPDATE_SQL,
                    entity.getPassWord(), // Password
                    entity.getFullName(), // HoTen
                    entity.getGender(), // GioiTinh (boolean)
                    entity.getNationalID(), // CCCD
                    new java.sql.Date(entity.getBirthDate().getTime()), // NgaySinh (java.sql.Date)
                    entity.getAddress(), // DiaChi
                    entity.getPhoneNumber(), // SDT
                    entity.getEmail(), // Email
                    entity.getRole().getRoleId(), // role_ID (int)
                    entity.getPhoto(), // Photo
                    entity.getStatus(), // TrangThai (boolean)
                    entity.getUserName() // WHERE UserName = ?
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<EmployeeInfo> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public EmployeeInfo findById(String id) {
        List<EmployeeInfo> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<EmployeeInfo> selectBySql(String sql, Object... args) {
        List<EmployeeInfo> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                EmployeeInfo nv = new EmployeeInfo();
                nv.setUserName(rs.getString("UserName"));
                nv.setPassWord(rs.getString("PassWord"));
                nv.setFullName(rs.getString("FullName"));
                nv.setGender(rs.getString("Gender"));
                nv.setNationalID(rs.getString("NationalID"));
                nv.setBirthDate(rs.getDate("BirthDate"));
                nv.setAddress(rs.getString("Address"));
                nv.setPhoneNumber(rs.getString("PhoneNumber"));
                nv.setEmail(rs.getString("email"));

                Role role = new Role();
                role.setRoleId(rs.getInt("role_id"));
                role.setRoleName(rs.getString("role_name"));
                nv.setRole(role);

                nv.setPhoto(rs.getString("photo"));
                nv.setStatus(rs.getString("Status"));
                list.add(nv);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public EmployeeInfo findByUserName(String username) {
        
        try {
            ResultSet rs = XJdbc.executeQuery(SELECT_BY_USERNAME_SQL, username);
            if (rs.next()) {
                Role role = new Role();
                role.setRoleId(rs.getInt("role_ID"));
                role.setRoleName(rs.getString("role_name"));

                EmployeeInfo nv = new EmployeeInfo();
                nv.setUserName(rs.getString("UserName"));
                nv.setPassWord(rs.getString("PassWord"));
                nv.setFullName(rs.getString("FullName"));
                nv.setGender(rs.getString("Gender"));
                nv.setPhoneNumber(rs.getString("PhoneNumber"));
                nv.setEmail(rs.getString("Email"));
                nv.setRole(role);
                nv.setPhoto(rs.getString("photo"));

                nv.setStatus(rs.getString("Status"));

                return nv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
public String findFullNameByUserName(String username) {
    
    try {
        ResultSet rs = XJdbc.executeQuery(SELECT_FULLNAME_BY_USERNAME_SQL, username);
        if (rs.next()) {
            return rs.getString("FullName");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

@Override
public void updatePassWord(EmployeeInfo entity) {
        try {
            XJdbc.executeUpdate(UPDATE_PASSWORD_SQL,
                    entity.getPassWord(), // Password
                    entity.getUserName(), // WHERE UserName = ?
                    entity.getEmail()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
