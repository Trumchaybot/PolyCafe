package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.TongChiDAO;
import poly.cafe.entity.TongChi;
import poly.cafe.util.XJdbc;

public class TongChiDAOImpl implements TongChiDAO {

    final String SELECT_ALL = "SELECT * FROM TongChi";
    final String SELECT_BY_ID = "SELECT * FROM TongChi WHERE ID = ?";
    final String INSERT_SQL = "INSERT INTO TongChi (Thang, Nam, TongChiTieu, TienLuong, TienNhapKho) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE TongChi SET Thang=?, Nam=?, TongChiTieu=?, TienLuong=?, TienNhapKho=? WHERE ID=?";
    final String DELETE_SQL = "DELETE FROM TongChi WHERE ID=?";

    @Override
    public TongChi create(TongChi entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getThang(),
                entity.getNam(),
                entity.getTongChiTieu(),
                entity.getTienLuong(),
                entity.getTienNhapKho()
        );
        return entity;
    }

    @Override
    public void update(TongChi entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getThang(),
                entity.getNam(),
                entity.getTongChiTieu(),
                entity.getTienLuong(),
                entity.getTienNhapKho(),
                entity.getID()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<TongChi> findAll() {
        return selectBySql(SELECT_ALL);
    }

    @Override
    public TongChi findById(Integer id) {
        List<TongChi> list = selectBySql(SELECT_BY_ID, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<TongChi> selectBySql(String sql, Object... args) {
        List<TongChi> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                TongChi tc = new TongChi();
                tc.setID(rs.getInt("ID"));
                tc.setThang(rs.getInt("Thang"));
                tc.setNam(rs.getInt("Nam"));
                tc.setTongChiTieu(rs.getDouble("TongChiTieu"));
                tc.setTienLuong(rs.getDouble("TienLuong"));
                tc.setTienNhapKho(rs.getDouble("TienNhapKho"));
                list.add(tc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
