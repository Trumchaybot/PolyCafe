package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.NhapKhoDAO;
import poly.cafe.entity.NhapKho;
import poly.cafe.util.XJdbc;

public class NhapKhoDAOImpl implements NhapKhoDAO {

    String insertSql = "INSERT INTO NhapKho (ID, UserName, Vat_lieu_ID, So_luong, Ngay_nhap) VALUES (?, ?, ?, ?, ?)";
    String updateSql = "UPDATE NhapKho SET UserName = ?, Vat_lieu_ID = ?, So_luong = ?, Ngay_nhap = ? WHERE ID = ?";
    String deleteSql = "DELETE FROM NhapKho WHERE ID = ?";
    String selectAllSql = "SELECT * FROM NhapKho";
    String selectByIdSql = "SELECT * FROM NhapKho WHERE ID = ?";

    @Override
    public NhapKho create(NhapKho entity) {
        XJdbc.executeUpdate(
            insertSql,
            entity.getId(),
            entity.getUserName(),
            entity.getVatLieuId(),
            entity.getSoLuong(),
            entity.getNgayNhap()
        );
        return entity;
    }

    @Override
    public void update(NhapKho entity) {
        XJdbc.executeUpdate(
            updateSql,
            entity.getUserName(),
            entity.getVatLieuId(),
            entity.getSoLuong(),
            entity.getNgayNhap(),
            entity.getId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<NhapKho> findAll() {
        return selectBySql(selectAllSql);
    }

    @Override
    public NhapKho findById(Integer id) {
        List<NhapKho> list = selectBySql(selectByIdSql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<NhapKho> selectBySql(String sql, Object... args) {
        List<NhapKho> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                NhapKho nk = new NhapKho();
                nk.setId(rs.getInt("ID"));
                nk.setUserName(rs.getString("UserName"));
                nk.setVatLieuId(rs.getInt("Vat_lieu_ID"));
                nk.setSoLuong(rs.getInt("So_luong"));
                nk.setNgayNhap(rs.getDate("Ngay_nhap"));
                list.add(nk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
