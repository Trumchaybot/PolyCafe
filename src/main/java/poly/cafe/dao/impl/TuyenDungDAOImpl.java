package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.CrudDAO;
import poly.cafe.dao.TuyenDungDAO;
import poly.cafe.entity.TuyenDung;
import poly.cafe.util.XJdbc;

public class TuyenDungDAOImpl implements TuyenDungDAO {

    String insertSql = " INSERT INTO TuyenDung (ID, Vi_tri, Mo_ta, Yeu_cau, So_luong, Ngay_bat_dau, Ngay_ket_thuc, Trang_thai, UserName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE TuyenDung SET Vi_tri = ?, Mo_ta = ?, Yeu_cau = ?, So_luong = ?, Ngay_bat_dau = ?, Ngay_ket_thuc = ?, Trang_thai = ?, UserName = ? WHERE ID = ?";
    String deleteSql = "DELETE FROM TuyenDung WHERE ID = ?";
    String selectAllSql = "SELECT * FROM TuyenDung";
    String selectByIdSql = "SELECT * FROM TuyenDung WHERE ID = ?";

    @Override
    public TuyenDung create(TuyenDung entity) {
        XJdbc.executeUpdate(insertSql,
            entity.getID(),
            entity.getVi_tri(),
            entity.getMo_ta(),
            entity.getYeu_cau(),
            entity.getSo_luong(),
            new java.sql.Date(entity.getNgay_bat_dau().getTime()),
            new java.sql.Date(entity.getNgay_ket_thuc().getTime()),
            entity.getTrang_thai(),
            entity.getUserName()
        );
        return entity;
    }

    @Override
    public void update(TuyenDung entity) {
        XJdbc.executeUpdate(
            updateSql,
            entity.getVi_tri(),
            entity.getMo_ta(),
            entity.getYeu_cau(),
            entity.getSo_luong(),
            new java.sql.Date(entity.getNgay_bat_dau().getTime()),
            new java.sql.Date(entity.getNgay_ket_thuc().getTime()),
            entity.getTrang_thai(),
            entity.getUserName(),
            entity.getID()
        );
    }


    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<TuyenDung> findAll() {
        return selectBySql(selectAllSql);
    }

    @Override
    public TuyenDung findById(Integer id) {
        List<TuyenDung> list = selectBySql(selectByIdSql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<TuyenDung> selectBySql(String sql, Object... args) {
    List<TuyenDung> list = new ArrayList<>();
    try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
        while (rs.next()) {
            TuyenDung td = new TuyenDung();
            td.setID(rs.getInt("ID"));
            td.setVi_tri(rs.getString("Vi_tri"));
            td.setMo_ta(rs.getString("Mo_ta"));
            td.setYeu_cau(rs.getString("Yeu_cau")); 
            td.setSo_luong(rs.getInt("So_luong"));
            td.setNgay_bat_dau(rs.getDate("Ngay_bat_dau"));
            td.setNgay_ket_thuc(rs.getDate("Ngay_ket_thuc"));
            td.setTrang_thai(rs.getString("Trang_thai"));
            td.setUserName(rs.getString("UserName"));
            list.add(td);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
}
