package poly.cafe.dao;

import poly.cafe.entity.Suppliers;

public interface SuppliersDAO extends CrudDAO<Suppliers, Integer> {
    Suppliers findByPhone(String phoneNumber);
}
