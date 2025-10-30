package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.Attendance;
import poly.cafe.util.XJdbc;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import poly.cafe.dao.AttendanceDAO;

public class AttendanceDAOImpl implements AttendanceDAO {

    final String INSERT_SQL = "INSERT INTO Attendance (UserName, work_date, checkin_time, checkout_time, total_hours) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Attendance SET UserName = ?, work_date = ?, checkin_time = ?, checkout_time = ?, total_hours = ? WHERE ID = ?";
    final String DELETE_SQL = "DELETE FROM Attendance WHERE ID = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Attendance";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Attendance WHERE ID = ?";
    final String SELECT_BY_USER_AND_DATE_SQL = "SELECT TOP 1 * FROM Attendance WHERE UserName = ? AND work_date = ? ORDER BY ID DESC";

    @Override
    public Attendance create(Attendance entity) {
        Float tongGio = tinhTongGioLam(entity.getCheckinTime(), entity.getCheckoutTime());
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getUserName(),
                Date.valueOf(entity.getWorkDate()),
                entity.getCheckinTime() != null ? Time.valueOf(entity.getCheckinTime()) : null,
                entity.getCheckoutTime() != null ? Time.valueOf(entity.getCheckoutTime()) : null,
                tongGio);
        return entity;
    }

    @Override
    public void update(Attendance entity) {
        Float tongGio = tinhTongGioLam(entity.getCheckinTime(), entity.getCheckinTime());
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getUserName(),
                Date.valueOf(entity.getWorkDate()),
                entity.getCheckinTime() != null ? Time.valueOf(entity.getCheckinTime()) : null,
                entity.getCheckoutTime() != null ? Time.valueOf(entity.getCheckoutTime()) : null,
                tongGio,
                entity.getId());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Attendance> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Attendance findById(Integer id) {
        List<Attendance> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Attendance findByUserAndDate(String userName, LocalDate ngayLam) {
        List<Attendance> list = selectBySql(SELECT_BY_USER_AND_DATE_SQL, userName, Date.valueOf(ngayLam));
        return list.isEmpty() ? null : list.get(0);
    }

    private List<Attendance> selectBySql(String sql, Object... args) {
        List<Attendance> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                Attendance cc = new Attendance();
                cc.setId(rs.getInt("ID"));
                cc.setUserName(rs.getString("UserName"));
                cc.setWorkDate(rs.getDate("work_date").toLocalDate());

                Time gioVao = rs.getTime("checkin_time");
                Time gioRa = rs.getTime("checkout_time");
                cc.setCheckinTime(gioVao != null ? gioVao.toLocalTime() : null);
                cc.setCheckoutTime(gioRa != null ? gioRa.toLocalTime() : null);

                float tongGio = rs.getFloat("total_hours");
                cc.setTotalHour(tongGio);

                list.add(cc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private Float tinhTongGioLam(LocalTime vao, LocalTime ra) {
        if (vao != null && ra != null && ra.isAfter(vao)) {
            Duration duration = Duration.between(vao, ra);
            return duration.toMinutes() / 60f;
        }
        return null;
    }
}
