package poly.cafe.dao;

import java.util.List;
import poly.cafe.entity.User;

public interface UserDAO extends CrudDAO<User, String> {
 List<String> getAllUserNames();
 List<Object[]> findAllWithRole();
 void deleteById(String userName);
}
