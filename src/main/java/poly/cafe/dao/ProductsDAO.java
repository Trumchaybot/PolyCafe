package poly.cafe.dao;

import java.util.List;
import poly.cafe.entity.Products;

public interface ProductsDAO extends CrudDAO<Products, Integer> {
     int insertAndReturnId(Products entity);
     List<Products> findKho();
     void updateKho (Products entity);
}
