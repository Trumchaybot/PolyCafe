package poly.cafe.dao;

import java.util.List;
import poly.cafe.entity.ProductDetails;

public interface ProductDetailsDAO extends CrudDAO<ProductDetails, Integer> {
    public List<ProductDetails> findByProductId(int productId);
}
