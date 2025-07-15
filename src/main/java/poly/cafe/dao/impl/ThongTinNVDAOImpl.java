package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.ThongTinNVDAO;
import poly.cafe.entity.ThongTinNV;
import poly.cafe.util.XJdbc;

public class ThongTinNVDAOImpl implements ThongTinNVDAO {

    final String INSERT_SQL = "INSERT INTO ThongTinNV (ID, UserName, Ho_ten, Ngay_sinh, Dia_chi, SDT, Email, Ngay_vao_lam, role_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE ThongTinNV SET UserName=?, Ho_ten=?, Ngay_sinh=?, Dia_chi=?, SDT=?, Email=?, Ngay_vao_lam=?, role_ID=? WHERE ID=?";
    final String DELETE_SQL = "DELETE FROM ThongTinNV WHERE ID = ?";
    final String SELECT_ALL_SQL = "SELECT nv.*, u.Photo FROM ThongTinNV nv JOIN Users u ON nv.UserName = u.UserName";
    final String SELECT_BY_ID_SQL = "SELECT * FROM ThongTinNV WHERE ID = ?";
    final String SELECT_BY_USERNAME_SQL = "SELECT nv.*, u.Photo FROM ThongTinNV nv JOIN Users u ON nv.UserName = u.UserName WHERE nv.UserName = ?";

    @Override
    public ThongTinNV create(ThongTinNV entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getId(),
                entity.getUserName(),
                entity.getHoTen(),
                entity.getNgaySinh(),
                entity.getDiaChi(),
                entity.getSdt(),
                entity.getEmail(),
                entity.getNgayVaoLam(),
                entity.getRoleId()
        );
        return entity;
    }

    @Override
    public void update(ThongTinNV entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getUserName(),
                entity.getHoTen(),
                entity.getNgaySinh(),
                entity.getDiaChi(),
                entity.getSdt(),
                entity.getEmail(),
                entity.getNgayVaoLam(),
                entity.getRoleId(),
                entity.getId()
        );
    }
    
    @Override
    public List<ThongTinNV> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    // ✔️ Hàm phụ trợ để lấy theo username (dùng trong open())
    public ThongTinNV findByUserName(String username) {
        List<ThongTinNV> list = selectBySql(SELECT_BY_USERNAME_SQL, username);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<ThongTinNV> selectBySql(String sql, Object... args) {
    List<ThongTinNV> list = new ArrayList<>();
    try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
        while (rs.next()) {
            ThongTinNV entity = new ThongTinNV();
            entity.setId(rs.getInt("ID"));
            entity.setUserName(rs.getString("UserName"));
            entity.setHoTen(rs.getString("Ho_ten"));
            entity.setNgaySinh(rs.getDate("Ngay_sinh"));
            entity.setDiaChi(rs.getString("Dia_chi"));
            entity.setSdt(rs.getString("SDT"));
            entity.setEmail(rs.getString("Email"));
            entity.setNgayVaoLam(rs.getDate("Ngay_vao_lam"));
            entity.setRoleId(rs.getInt("role_ID"));
            entity.setPhoto(rs.getString("Photo")); // ✅ thêm cột Photo
            list.add(entity);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    @Override
    public void deleteById(Integer id) {
         XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public ThongTinNV findById(Integer id) {
        List<ThongTinNV> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public String getHoTenByUserName(String userName) {
        String hoTen = "";
        try (ResultSet rs = XJdbc.executeQuery(
            "SELECT Ho_ten FROM ThongTinNV WHERE UserName = ?", userName)) {
            if (rs.next()) {
                hoTen = rs.getString("Ho_ten");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoTen;
    }
}
