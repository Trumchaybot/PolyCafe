package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.XuatKhoDAO;
import poly.cafe.entity.XuatKho;
import poly.cafe.util.XJdbc;

public class XuatKhoDAOImpl implements XuatKhoDAO {

    String insertSql = "INSERT INTO XuatKho (ID, UserName, Vat_lieu_ID, So_luong, Ngay_xuat) VALUES (?, ?, ?, ?, ?)";
    String updateSql = "UPDATE XuatKho SET UserName = ?, Vat_lieu_ID = ?, So_luong = ?, Ngay_xuat = ? WHERE ID = ?";
    String deleteSql = "DELETE FROM XuatKho WHERE ID = ?";
    String selectAllSql = "SELECT * FROM XuatKho";
    String selectByIdSql = "SELECT * FROM XuatKho WHERE ID = ?";

    @Override
    public XuatKho create(XuatKho entity) {
        XJdbc.executeUpdate(
            insertSql,
            entity.getId(),
            entity.getUserName(),
            entity.getVatLieuId(),
            entity.getSoLuong(),
            entity.getNgayXuat()
        );
        return entity;
    }

    @Override
    public void update(XuatKho entity) {
        XJdbc.executeUpdate(
            updateSql,
            entity.getUserName(),
            entity.getVatLieuId(),
            entity.getSoLuong(),
            entity.getNgayXuat(),
            entity.getId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<XuatKho> findAll() {
        return selectBySql(selectAllSql);
    }

    @Override
    public XuatKho findById(Integer id) {
        List<XuatKho> list = selectBySql(selectByIdSql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<XuatKho> selectBySql(String sql, Object... args) {
        List<XuatKho> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                XuatKho xk = new XuatKho();
                xk.setId(rs.getInt("ID"));
                xk.setUserName(rs.getString("UserName"));
                xk.setVatLieuId(rs.getInt("Vat_lieu_ID"));
                xk.setSoLuong(rs.getInt("So_luong"));
                xk.setNgayXuat(rs.getDate("Ngay_xuat"));
                list.add(xk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
