package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.Applications;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.ApplicationsDAO;

public class ApplicationsDAOImpl implements ApplicationsDAO {

    final String INSERT_SQL = "INSERT INTO Applications (recruitment_id, full_name, birthdate, PhoneNumber, email, applied_position, application_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Applications SET recruitment_id, full_name, birthdate, PhoneNumber, email, applied_position, application_date, status WHERE id=?";
    final String DELETE_SQL = "DELETE FROM Applications WHERE id=?";
    final String SELECT_ALL_SQL = "SELECT * FROM Applications";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Applications WHERE id=?";

    @Override
    public Applications create(Applications entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getRecruitmentId(),
                entity.getFullName(),
                entity.getBirthDate(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getAppliedPosition(),
                entity.getApplicationDate(),
                entity.getStatus());
        return entity;
    }

    @Override
    public void update(Applications entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getRecruitmentId(),
                entity.getFullName(),
                entity.getBirthDate(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getAppliedPosition(),
                entity.getApplicationDate(),
                entity.getStatus(),
                entity.getId());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Applications> findAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public Applications findById(Integer id) {
        List<Applications> list = selectBySQL(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<Applications> selectBySQL(String sql, Object... args) {
        List<Applications> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                Applications ut = new Applications();
                ut.setId(rs.getInt("id"));
                ut.setRecruitmentId(rs.getInt("recruitment_id"));
                ut.setFullName(rs.getString("full_name"));
                ut.setApplicationDate(rs.getDate("birthdate"));
                ut.setPhoneNumber(rs.getString("PhoneNumber"));
                ut.setEmail(rs.getString("email"));
                ut.setAppliedPosition(rs.getString("applied_position"));
                ut.setApplicationDate(rs.getDate("application_date"));
                ut.setStatus(rs.getString("status"));
                list.add(ut);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi truy vấn Applications", e);
        }
        return list;
    }
}
