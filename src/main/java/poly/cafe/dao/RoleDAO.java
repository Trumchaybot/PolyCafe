package poly.cafe.dao;

import poly.cafe.entity.Role;
import java.util.List;

public interface RoleDAO {
    Role create(Role role);
    void update(Role role);
    void deleteById(int id);
    Role findById(int id);
    List<Role> findAll();
}
