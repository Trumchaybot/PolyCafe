package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.ChiTietDonHangDAO;
import poly.cafe.entity.ChiTietDonHang;
import poly.cafe.util.XJdbc;

public class ChiTietDonHangDAOImpl implements ChiTietDonHangDAO {

    String insertSql = "INSERT INTO ChiTietDonHang (Chi_tiet_ID, Don_hang_ID, San_pham_ID, So_luong) VALUES (?, ?, ?, ?)";
    String updateSql = "UPDATE ChiTietDonHang SET Don_hang_ID = ?, San_pham_ID = ?, So_luong = ? WHERE Chi_tiet_ID = ?";
    String deleteSql = "DELETE FROM ChiTietDonHang WHERE Chi_tiet_ID = ?";
    String selectAllSql = "SELECT * FROM ChiTietDonHang";
    String selectByIdSql = "SELECT * FROM ChiTietDonHang WHERE Chi_tiet_ID = ?";

    @Override
    public ChiTietDonHang create(ChiTietDonHang entity) {
        XJdbc.executeUpdate(
            insertSql,
            entity.getChiTietId(),
            entity.getDonHangId(),
            entity.getSanPhamId(),
            entity.getSoLuong()
        );
        return entity;
    }

    @Override
    public void update(ChiTietDonHang entity) {
        XJdbc.executeUpdate(
            updateSql,
            entity.getDonHangId(),
            entity.getSanPhamId(),
            entity.getSoLuong(),
            entity.getChiTietId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<ChiTietDonHang> findAll() {
        return selectBySql(selectAllSql);
    }

    @Override
    public ChiTietDonHang findById(Integer id) {
        List<ChiTietDonHang> list = selectBySql(selectByIdSql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<ChiTietDonHang> selectBySql(String sql, Object... args) {
        List<ChiTietDonHang> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                ChiTietDonHang ctdh = new ChiTietDonHang();
                ctdh.setChiTietId(rs.getInt("Chi_tiet_ID"));
                ctdh.setDonHangId(rs.getInt("Don_hang_ID"));
                ctdh.setSanPhamId(rs.getInt("San_pham_ID"));
                ctdh.setSoLuong(rs.getInt("So_luong"));
                list.add(ctdh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
