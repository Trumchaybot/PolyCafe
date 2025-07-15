package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.ChamCongDAO;
import poly.cafe.entity.ChamCong;
import poly.cafe.util.XJdbc;
import java.time.LocalDate;
import java.time.LocalTime;

public class ChamCongDAOImpl implements ChamCongDAO {

    final String INSERT_SQL = "INSERT INTO ChamCong (UserName, Ngay_lam_viec, Gio_vao_ca, Gio_ra_ca) VALUES (?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE ChamCong SET UserName = ?, Ngay_lam_viec = ?, Gio_vao_ca = ?, Gio_ra_ca = ? WHERE ID = ?";
    final String DELETE_SQL = "DELETE FROM ChamCong WHERE ID = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM ChamCong";
    final String SELECT_BY_ID_SQL = "SELECT * FROM ChamCong WHERE ID = ?";
    
    
    @Override
    public ChamCong create(ChamCong entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getUserName(),
                Date.valueOf(entity.getNgayLamViec()),
                entity.getGioVaoCa() != null ? Time.valueOf(entity.getGioVaoCa()) : null,
                entity.getGioRaCa() != null ? Time.valueOf(entity.getGioRaCa()) : null);
        return entity;
    }

    @Override
    public void update(ChamCong entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getUserName(),
                Date.valueOf(entity.getNgayLamViec()),
                entity.getGioVaoCa() != null ? Time.valueOf(entity.getGioVaoCa()) : null,
                entity.getGioRaCa() != null ? Time.valueOf(entity.getGioRaCa()) : null,
                entity.getId());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<ChamCong> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ChamCong findById(Integer id) {
        List<ChamCong> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    // ✅ Hàm tìm ca làm gần nhất (rất quan trọng cho nút Giờ ra ca + tự động fill)
    @Override
public ChamCong findByUserAndDate(String userName, LocalDate ngayLam) {
    String sql = "SELECT TOP 1 * FROM ChamCong WHERE UserName = ? AND Ngay_lam_viec = ? ORDER BY ID DESC";
    List<ChamCong> list = this.selectBySql(sql, userName, Date.valueOf(ngayLam));
    return list.isEmpty() ? null : list.get(0);
}


    private List<ChamCong> selectBySql(String sql, Object... args) {
        List<ChamCong> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                ChamCong cc = new ChamCong();
                cc.setId(rs.getInt("ID"));
                cc.setUserName(rs.getString("UserName"));
                cc.setNgayLamViec(rs.getDate("Ngay_lam_viec").toLocalDate());

                Time gioVao = rs.getTime("Gio_vao_ca");
                Time gioRa = rs.getTime("Gio_ra_ca");
                cc.setGioVaoCa(gioVao != null ? gioVao.toLocalTime() : null);
                cc.setGioRaCa(gioRa != null ? gioRa.toLocalTime() : null);
                
                list.add(cc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
