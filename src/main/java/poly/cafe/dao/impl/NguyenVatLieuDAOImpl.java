package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.NguyenVatLieuDAO;
import poly.cafe.entity.NguyenVatLieu;
import poly.cafe.util.XJdbc;

public class NguyenVatLieuDAOImpl implements NguyenVatLieuDAO {

    String insertSql = "INSERT INTO NguyenVatLieu (Vat_lieu_ID, Ten_vat_lieu, don_vi, ,don_gia, so_luong_ton) VALUES (?, ?, ?, ?, ?)";
    String updateSql = "UPDATE NguyenVatLieu SET Ten_vat_lieu = ?, don_vi = ?, don_gia = ?, so_luong_ton = ? WHERE Vat_lieu_ID = ?";
    String deleteSql = "DELETE FROM NguyenVatLieu WHERE Vat_lieu_ID = ?";
    String selectAllSql = "SELECT * FROM NguyenVatLieu";
    String selectByIdSql = "SELECT * FROM NguyenVatLieu WHERE Vat_lieu_ID = ?";

    @Override
    public NguyenVatLieu create(NguyenVatLieu entity) {
        XJdbc.executeUpdate(
            insertSql,
            entity.getVatLieuId(),
            entity.getTenVatLieu(),
            entity.getDonVi(),
            entity.getDonGia(),
            entity.getSoLuongTon()
        );
        return entity;
    }

    @Override
    public void update(NguyenVatLieu entity) {
        XJdbc.executeUpdate(
            updateSql,
            entity.getTenVatLieu(),
            entity.getDonVi(),
            entity.getDonGia(),
            entity.getSoLuongTon(),
            entity.getVatLieuId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<NguyenVatLieu> findAll() {
        return selectBySql(selectAllSql);
    }

    @Override
    public NguyenVatLieu findById(Integer id) {
        List<NguyenVatLieu> list = selectBySql(selectByIdSql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<NguyenVatLieu> selectBySql(String sql, Object... args) {
        List<NguyenVatLieu> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                NguyenVatLieu nvl = new NguyenVatLieu();
                nvl.setVatLieuId(rs.getInt("Vat_lieu_ID"));
                nvl.setTenVatLieu(rs.getString("Ten_vat_lieu"));
                nvl.setDonVi(rs.getString("don_vi"));
                nvl.setDonGia(rs.getDouble("don_gia"));
                nvl.setSoLuongTon(rs.getInt("so_luong_ton"));
                list.add(nvl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
