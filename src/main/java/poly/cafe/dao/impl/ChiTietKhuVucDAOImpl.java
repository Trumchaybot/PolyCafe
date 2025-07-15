package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.ChiTietKhuVucDAO;
import poly.cafe.entity.ChiTietKhuVuc;
import poly.cafe.util.XJdbc;

public class ChiTietKhuVucDAOImpl implements ChiTietKhuVucDAO {

    final String INSERT_SQL = "INSERT INTO ChiTietKhuVuc (Khu_vuc_ID, UserName, Ngay_phan_cong) VALUES (?, ?, ?)";
    final String UPDATE_SQL = "UPDATE ChiTietKhuVuc SET Khu_vuc_ID=?, UserName=?, Ngay_phan_cong=? WHERE ID=?";
    final String DELETE_SQL = "DELETE FROM ChiTietKhuVuc WHERE ID=?";
    final String SELECT_ALL_SQL = "SELECT * FROM ChiTietKhuVuc";
    final String SELECT_BY_ID_SQL = "SELECT * FROM ChiTietKhuVuc WHERE ID=?";

    @Override
    public ChiTietKhuVuc create(ChiTietKhuVuc entity) {
        XJdbc.executeUpdate(INSERT_SQL, entity.getKhuVucId(), entity.getUserName(), entity.getNgayPhanCong());
        return entity;
    }

    @Override
    public void update(ChiTietKhuVuc entity) {
        XJdbc.executeUpdate(UPDATE_SQL, entity.getKhuVucId(), entity.getUserName(), entity.getNgayPhanCong(), entity.getId());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<ChiTietKhuVuc> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ChiTietKhuVuc findById(Integer id) {
        List<ChiTietKhuVuc> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
public List<ChiTietKhuVuc> findByKhuVucId(int khuVucId) {
    String sql = "SELECT * FROM ChiTietKhuVuc WHERE Khu_vuc_ID=?";
    return selectBySql(sql, khuVucId);
}


    private List<ChiTietKhuVuc> selectBySql(String sql, Object... args) {
        List<ChiTietKhuVuc> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                ChiTietKhuVuc entity = new ChiTietKhuVuc();
                entity.setId(rs.getInt("ID"));
                entity.setKhuVucId(rs.getInt("Khu_vuc_ID"));
                entity.setUserName(rs.getString("UserName"));
                entity.setNgayPhanCong(rs.getDate("Ngay_phan_cong"));
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
