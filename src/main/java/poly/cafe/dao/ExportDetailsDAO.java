/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao;

import java.util.List;
import poly.cafe.entity.ExportDetails;

/**
 *
 * @author admin
 */
public interface ExportDetailsDAO extends CrudDAO<ExportDetails, Integer> {
    public List<ExportDetails> findByExportId(int exportId);
}
