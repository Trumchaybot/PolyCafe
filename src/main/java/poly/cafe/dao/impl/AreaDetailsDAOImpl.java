package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.AreaDetails;
import poly.cafe.entity.ProductionStages;
import poly.cafe.entity.Role;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.AreaDetailsDAO;

public class AreaDetailsDAOImpl implements AreaDetailsDAO {

    final String INSERT_SQL = "INSERT INTO AreaDetails (area_id, UserName, role_id, productionStages_id, assign_date) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE AreaDetails SET area_id = ?, UserName = ?, role_id = ?, productionStages_id = ?, assign_date = ? WHERE id = ?";
    final String DELETE_SQL = "DELETE FROM AreaDetails WHERE id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM AreaDetails";
    final String SELECT_BY_ID_SQL = "SELECT * FROM AreaDetails WHERE id = ?";
    final String FIND_BY_KHU_VUC_ID = "SELECT * FROM v_AreaDetails WHERE area_id = ?";
    final String FIND_BY_USERNAME = "SELECT * FROM AreaDetails WHERE UserName = ?";
    final String DELETE_BY_USERNAME_SQL = "DELETE FROM AreaDetails WHERE UserName = ?";

    @Override
    public AreaDetails create(AreaDetails entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getAreaId(),
                entity.getUserName(),
                entity.getRoleId(),
                entity.getProductionStagesId(),
                entity.getAssignDate());
        return entity;
    }

    @Override
    public void update(AreaDetails entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getAreaId(),
                entity.getUserName(),
                entity.getRoleId(),
                entity.getProductionStagesId(),
                entity.getAssignDate(),
                entity.getId());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<AreaDetails> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public AreaDetails findById(Integer id) {
        List<AreaDetails> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<AreaDetails> selectBySql(String sql, Object... args) {
        List<AreaDetails> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                AreaDetails entity = new AreaDetails();
                entity.setId(rs.getInt("id"));
                entity.setAreaId(rs.getInt("area_id"));
                entity.setUserName(rs.getString("UserName"));
                entity.setRoleId(rs.getInt("role_id"));
                entity.setProductionStagesId(rs.getInt("productionStages_id"));
                entity.setAssignDate(rs.getDate("assign_date"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<AreaDetails> findByKhuVucId(int khuVucId) {
        List<AreaDetails> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(FIND_BY_KHU_VUC_ID, khuVucId)) {
            while (rs.next()) {
                AreaDetails entity = new AreaDetails();
                entity.setId(rs.getInt("id"));
                entity.setAreaId(rs.getInt("area_id"));
                entity.setUserName(rs.getString("UserName"));
                entity.setRoleId(rs.getInt("role_id"));
                entity.setProductionStagesId(rs.getInt("productionStages_id"));
                entity.setAssignDate(rs.getDate("assign_date"));

                // Map Role
                Role role = new Role();
                role.setRoleId(rs.getInt("role_id"));
                role.setRoleName(rs.getString("role_name"));
                entity.setRole(role);

                // Map ProductionStages
                ProductionStages productionStages = new ProductionStages();
                productionStages.setProductionStagesId(rs.getInt("productionStages_id"));
                productionStages.setProductionStagesName(rs.getString("productionStages_name"));
                entity.setProductionStage(productionStages);

                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public AreaDetails findByUsername(String username) {
        List<AreaDetails> list = selectBySql(FIND_BY_USERNAME, username);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void deleteByUsername(String username) {
        XJdbc.executeUpdate(DELETE_BY_USERNAME_SQL, username);
    }
}
