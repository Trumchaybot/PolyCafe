package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.DonHangDAO;
import poly.cafe.entity.DonHang;
import poly.cafe.util.XJdbc;

public class DonHangDAOImpl implements DonHangDAO {

    String insertSql = "INSERT INTO DonHang (Don_hang_ID, Ten_khach_hang, Ngay_dat, Ngay_giao, Tong_tien, Trang_thai) VALUES (?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE DonHang SET Ten_khach_hang = ?, Ngay_dat = ?, Ngay_giao = ?, Tong_tien = ?, Trang_thai = ? WHERE Don_hang_ID = ?";
    String deleteSql = "DELETE FROM DonHang WHERE Don_hang_ID = ?";
    String selectAllSql = "SELECT * FROM DonHang";
    String selectByIdSql = "SELECT * FROM DonHang WHERE Don_hang_ID = ?";

    @Override
    public DonHang create(DonHang entity) {
        XJdbc.executeUpdate(
            insertSql,
            entity.getDonHangId(),
            entity.getTenKhachHang(),
            Date.valueOf(entity.getNgayDat()),
            Date.valueOf(entity.getNgayGiao()),
            entity.getTongTien(),
            entity.getTrangThai()
        );
        return entity;
    }

    @Override
    public void update(DonHang entity) {
        XJdbc.executeUpdate(
            updateSql,
            entity.getTenKhachHang(),
            Date.valueOf(entity.getNgayDat()),
            Date.valueOf(entity.getNgayGiao()),
            entity.getTongTien(),
            entity.getTrangThai(),
            entity.getDonHangId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<DonHang> findAll() {
        return selectBySql(selectAllSql);
    }

    @Override
    public DonHang findById(Integer id) {
        List<DonHang> list = selectBySql(selectByIdSql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<DonHang> selectBySql(String sql, Object... args) {
        List<DonHang> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                DonHang dh = new DonHang();
                dh.setDonHangId(rs.getInt("Don_hang_ID"));
                dh.setTenKhachHang(rs.getString("Ten_khach_hang"));
                dh.setNgayDat(rs.getDate("Ngay_dat").toLocalDate());
                dh.setNgayGiao(rs.getDate("Ngay_giao").toLocalDate());
                dh.setTongTien(rs.getDouble("Tong_tien"));
                dh.setTrangThai(rs.getString("Trang_thai"));
                list.add(dh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<DonHang> findByNgayDat(LocalDate tuNgay, LocalDate denNgay) {
        String sql = "SELECT * FROM DonHang WHERE Ngay_dat BETWEEN ? AND ?";
    return selectBySql(sql, Date.valueOf(tuNgay), Date.valueOf(denNgay));
    }
}
