package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.KhuVucDAO;
import poly.cafe.entity.KhuVuc;
import poly.cafe.util.XJdbc;

public class KhuVucDAOImpl implements KhuVucDAO {

    final String INSERT_SQL = "INSERT INTO KhuVuc (Ten_khu_vuc, Mo_ta) VALUES (?, ?)";
    final String UPDATE_SQL = "UPDATE KhuVuc SET Ten_khu_vuc=?, Mo_ta=? WHERE Khu_vuc_ID=?";
    final String DELETE_SQL = "DELETE FROM KhuVuc WHERE Khu_vuc_ID=?";
    final String SELECT_ALL_SQL = "SELECT * FROM KhuVuc";
    final String SELECT_BY_ID_SQL = "SELECT * FROM KhuVuc WHERE Khu_vuc_ID=?";

    @Override
    public KhuVuc create(KhuVuc entity) {
        XJdbc.executeUpdate(INSERT_SQL, entity.getTenKhuVuc(), entity.getMoTa());
        return entity;
    }

    @Override
    public void update(KhuVuc entity) {
        XJdbc.executeUpdate(UPDATE_SQL, entity.getTenKhuVuc(), entity.getMoTa(), entity.getKhuVucId());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<KhuVuc> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhuVuc findById(Integer id) {
        List<KhuVuc> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<KhuVuc> selectBySql(String sql, Object... args) {
        List<KhuVuc> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                KhuVuc kv = new KhuVuc();
                kv.setKhuVucId(rs.getInt("Khu_vuc_ID"));
                kv.setTenKhuVuc(rs.getString("Ten_khu_vuc"));
                kv.setMoTa(rs.getString("Mo_ta"));
                list.add(kv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
