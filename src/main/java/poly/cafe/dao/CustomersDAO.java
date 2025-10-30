package poly.cafe.dao;

import poly.cafe.entity.Customers;

public interface CustomersDAO extends CrudDAO<Customers, Integer> {
    Customers findByPhone(String phoneNumber);

}
