package poly.cafe.dao;

import poly.cafe.entity.EmployeeInfo;

public interface EmployeeInfoDAO extends CrudDAO<EmployeeInfo, String> {
    EmployeeInfo findByUserName(String userName);
    String findFullNameByUserName(String username);
    void updatePassWord(EmployeeInfo entity);
}
