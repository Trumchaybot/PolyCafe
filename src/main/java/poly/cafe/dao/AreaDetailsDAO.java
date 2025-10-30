package poly.cafe.dao;

import java.util.List;
import poly.cafe.entity.AreaDetails;

public interface AreaDetailsDAO extends CrudDAO<AreaDetails, Integer> {
    List<AreaDetails> findByKhuVucId(int areaId);
    AreaDetails findByUsername(String username);
    void deleteByUsername(String username);
}
