package poly.cafe.dao;

import poly.cafe.entity.Export;

public interface ExportDAO extends CrudDAO<Export, Integer> {
    int insertAndReturnId(Export entity);
}
