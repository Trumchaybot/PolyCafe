package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.Recruitment;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.RecruitmentDAO;

public class RecruitmentDAOImpl implements RecruitmentDAO {

    final String INSERT_SQL = "INSERT INTO Recruitment (position, description, requirement, quantity, start_date, end_date, status, UserName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Recruitment SET position=?, description=?, requirement=?, quantity=?, start_date=?, end_date=?, status=?, UserName=? WHERE recruitment_id=?";
    final String DELETE_SQL = "DELETE FROM Recruitment WHERE recruitment_id=?";
    final String SELECT_ALL_SQL = "SELECT * FROM Recruitment";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Recruitment WHERE recruitment_id=?";

    @Override
    public Recruitment create(Recruitment entity) {
        XJdbc.executeUpdate(INSERT_SQL,
            entity.getPosition(),
            entity.getDescription(),
            entity.getRequirement(),
            entity.getQuantity(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getStatus(),
            entity.getUserName()
        );
        return entity;
    }

    @Override
    public void update(Recruitment entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
            entity.getPosition(),
            entity.getDescription(),
            entity.getRequirement(),
            entity.getQuantity(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getStatus(),
            entity.getUserName(),
            entity.getRecruitmentId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Recruitment> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Recruitment findById(Integer id) {
        List<Recruitment> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<Recruitment> selectBySql(String sql, Object... args) {
        List<Recruitment> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                Recruitment entity = new Recruitment();
                entity.setRecruitmentId(rs.getInt("recruitment_id"));
                entity.setPosition(rs.getString("position"));
                entity.setDescription(rs.getString("description"));
                entity.setRequirement(rs.getString("Recruitment"));
                entity.setQuantity(rs.getInt("quantity"));
                entity.setStartDate(rs.getDate("start_date"));
                entity.setEndDate(rs.getDate("end_date"));
                entity.setStatus(rs.getString("status"));
                entity.setUserName(rs.getString("UserName"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
