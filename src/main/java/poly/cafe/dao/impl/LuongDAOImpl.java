package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.LuongDAO;
import poly.cafe.entity.Luong;
import poly.cafe.util.XJdbc;

public class LuongDAOImpl implements LuongDAO {

    final String INSERT_SQL = "INSERT INTO Luong (ID, UserName, Thang, Nam, Tong_luong) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Luong SET UserName = ?, Thang = ?, Nam = ?, Tong_luong = ? WHERE ID = ?";
    final String DELETE_SQL = "DELETE FROM Luong WHERE ID = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Luong";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Luong WHERE ID = ?";

    @Override
    public Luong create(Luong entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getID(),
                entity.getUserName(),
                entity.getThang(),
                entity.getNam(),
                entity.getTong_luong());
        return entity;
    }

    @Override
    public void update(Luong entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getUserName(),
                entity.getThang(),
                entity.getNam(),
                entity.getTong_luong(),
                entity.getID());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public Luong findById(Integer id) {
        List<Luong> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Luong> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    private List<Luong> selectBySql(String sql, Object... args) {
        List<Luong> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                Luong entity = new Luong();
                entity.setID(rs.getInt("ID"));
                entity.setUserName(rs.getString("UserName"));
                entity.setThang(rs.getInt("Thang"));
                entity.setNam(rs.getInt("Nam"));
                entity.setTong_luong(rs.getBigDecimal("Tong_luong"));
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Object[]> findAllWithHoTen() {
    List<Object[]> list = new ArrayList<>();
    String sql = """
        SELECT l.*, nv.Ho_ten 
        FROM Luong l
        JOIN ThongTinNV nv ON l.UserName = nv.UserName
    """;
    try (ResultSet rs = XJdbc.executeQuery(sql)) {
        while (rs.next()) {
            Object[] row = {
                rs.getInt("ID"),
                rs.getString("UserName"),
                rs.getInt("Thang"),
                rs.getInt("Nam"),
                rs.getDouble("TongGioLam"),
                rs.getInt("TongSanPham"),
                rs.getDouble("Tong_luong"),
                rs.getString("Ho_ten")  // Thêm Họ tên
            };
            list.add(row);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
@Override
public List<Luong> findByCondition(String userName, Integer thang, Integer nam) {
    String sql = "SELECT * FROM Luong WHERE "
               + "(UserName = ? OR ? IS NULL) "
               + "AND (Thang = ? OR ? IS NULL) "
               + "AND (Nam = ? OR ? IS NULL)";
    try {
        return XJdbc.getBeanList(Luong.class, sql,
            userName, userName,
            thang, thang,
            nam, nam
        );
    } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
}
}
