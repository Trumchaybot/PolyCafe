package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.SanLuongDAO;
import poly.cafe.entity.SanLuong;
import poly.cafe.util.XJdbc;

public class SanLuongDAOImpl implements SanLuongDAO {

    String insertSql = "INSERT INTO SanLuong (ID, UserName, San_pham_ID, So_luong) VALUES (?, ?, ?, ?)";
    String updateSql = "UPDATE SanLuong SET UserName = ?, San_pham_ID = ?, So_luong = ? WHERE ID = ?";
    String deleteSql = "DELETE FROM SanLuong WHERE ID = ?";
    String selectAllSql = "SELECT * FROM SanLuong";
    String selectByIdSql = "SELECT * FROM SanLuong WHERE ID = ?";

    @Override
    public SanLuong create(SanLuong entity) {
        XJdbc.executeUpdate(
            insertSql,
            entity.getId(),
            entity.getUserName(),
            entity.getSanPhamId(),
            entity.getSoLuong()
        );
        return entity;
    }

    @Override
    public void update(SanLuong entity) {
        XJdbc.executeUpdate(
            updateSql,
            entity.getUserName(),
            entity.getSanPhamId(),
            entity.getSoLuong(),
            entity.getId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<SanLuong> findAll() {
        return selectBySql(selectAllSql);
    }

    @Override
    public SanLuong findById(Integer id) {
        List<SanLuong> list = selectBySql(selectByIdSql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<SanLuong> selectBySql(String sql, Object... args) {
        List<SanLuong> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                SanLuong sl = new SanLuong();
                sl.setId(rs.getInt("ID"));
                sl.setUserName(rs.getString("UserName"));
                sl.setSanPhamId(rs.getInt("San_pham_ID"));
                sl.setSoLuong(rs.getInt("So_luong"));
                list.add(sl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
