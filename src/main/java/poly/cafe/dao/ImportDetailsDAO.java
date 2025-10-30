/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao;

import java.util.List;
import poly.cafe.entity.ImportDetails;

/**
 *
 * @author admin
 */
public interface ImportDetailsDAO extends CrudDAO<ImportDetails, Integer> {
    public List<ImportDetails> findByImportId(int importId);
}
