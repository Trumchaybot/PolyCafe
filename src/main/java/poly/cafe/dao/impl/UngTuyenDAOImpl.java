package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.UngTuyenDAO;
import poly.cafe.entity.UngTuyen;
import poly.cafe.util.XJdbc;

public class UngTuyenDAOImpl implements UngTuyenDAO {

    String insertSql = "INSERT INTO UngTuyen (ID, TuyenDung_ID, Ho_ten, Ngay_sinh, SDT, Email, Vi_tri_ung_tuyen, Ngay_ung_tuyen, Trang_thai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE UngTuyen SET TuyenDung_ID = ?, Ho_ten = ?, Ngay_sinh = ?, SDT = ?, Email = ?, Vi_tri_ung_tuyen = ?, Ngay_ung_tuyen = ?, Trang_thai = ? WHERE ID = ?";
    String deleteSql = "DELETE FROM UngTuyen WHERE ID = ?";
    String selectAllSql = "SELECT * FROM UngTuyen";
    String selectByIdSql = "SELECT * FROM UngTuyen WHERE ID = ?";

    @Override
    public UngTuyen create(UngTuyen entity) {
        XJdbc.executeUpdate(
            insertSql,
            entity.getId(),
            entity.getTuyenDungId(),
            entity.getHoTen(),
            new java.sql.Date(entity.getNgaySinh().getTime()),
            entity.getSdt(),
            entity.getEmail(),
            entity.getViTriUngTuyen(),
            new java.sql.Date(entity.getNgayUngTuyen().getTime()),
            entity.getTrangThai()
        );
        return entity;
    }

    @Override
    public void update(UngTuyen entity) {
        XJdbc.executeUpdate(
            updateSql,
            entity.getTuyenDungId(),
            entity.getHoTen(),
            new java.sql.Date(entity.getNgaySinh().getTime()),
            entity.getSdt(),
            entity.getEmail(),
            entity.getViTriUngTuyen(),
            new java.sql.Date(entity.getNgayUngTuyen().getTime()),
            entity.getTrangThai(),
            entity.getId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<UngTuyen> findAll() {
        return selectBySql(selectAllSql);
    }

    @Override
    public UngTuyen findById(Integer id) {
        List<UngTuyen> list = selectBySql(selectByIdSql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<UngTuyen> selectBySql(String sql, Object... args) {
        List<UngTuyen> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                UngTuyen ut = new UngTuyen();
                ut.setId(rs.getInt("ID"));
                ut.setTuyenDungId(rs.getInt("TuyenDung_ID"));
                ut.setHoTen(rs.getString("Ho_ten"));
                ut.setNgaySinh(rs.getDate("Ngay_sinh"));
                ut.setSdt(rs.getString("SDT"));
                ut.setEmail(rs.getString("Email"));
                ut.setViTriUngTuyen(rs.getString("Vi_tri_ung_tuyen"));
                ut.setNgayUngTuyen(rs.getDate("Ngay_ung_tuyen"));
                ut.setTrangThai(rs.getString("Trang_thai"));
                list.add(ut);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
