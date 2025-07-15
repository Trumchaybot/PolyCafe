package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.TongThuDAO;
import poly.cafe.entity.TongThu;
import poly.cafe.util.XJdbc;

public class TongThuDAOImpl implements TongThuDAO {

    String selectAll = "SELECT * FROM TongThu";
    String selectById = "SELECT * FROM TongThu WHERE ID = ?";
    String insertSql = "INSERT INTO TongThu (Thang, Nam, TongDoanhThu, TienDonHang, TienXuatKho) VALUES (?, ?, ?, ?, ?)";
    String updateSql = "UPDATE TongThu SET Thang=?, Nam=?, TongDoanhThu=?, TienDonHang=?, TienXuatKho=? WHERE ID=?";
    String deleteSql = "DELETE FROM TongThu WHERE ID=?";

    @Override
    public TongThu create(TongThu entity) {
        XJdbc.executeUpdate(insertSql,
            entity.getThang(),
            entity.getNam(),
            entity.getTongDoanhThu(),
            entity.getTienDonHang(),
            entity.getTienXuatKho()
        );
        return entity;
    }

    @Override
    public void update(TongThu entity) {
        XJdbc.executeUpdate(updateSql,
            entity.getThang(),
            entity.getNam(),
            entity.getTongDoanhThu(),
            entity.getTienDonHang(),
            entity.getTienXuatKho(),
            entity.getID()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<TongThu> findAll() {
        return selectBySql(selectAll);
    }

    @Override
    public TongThu findById(Integer id) {
        List<TongThu> list = selectBySql(selectById, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<TongThu> selectBySql(String sql, Object... args) {
        List<TongThu> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                TongThu tt = new TongThu();
                tt.setID(rs.getInt("ID"));
                tt.setThang(rs.getInt("Thang"));
                tt.setNam(rs.getInt("Nam"));
                tt.setTongDoanhThu(rs.getDouble("TongDoanhThu"));
                tt.setTienDonHang(rs.getDouble("TienDonHang"));
                tt.setTienXuatKho(rs.getDouble("TienXuatKho"));
                list.add(tt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
