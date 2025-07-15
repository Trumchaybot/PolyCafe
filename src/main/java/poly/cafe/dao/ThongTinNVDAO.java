package poly.cafe.dao;

import poly.cafe.entity.ThongTinNV;

public interface ThongTinNVDAO extends CrudDAO<ThongTinNV, Integer> {
    ThongTinNV findByUserName(String username);
    String getHoTenByUserName(String userName);
}
