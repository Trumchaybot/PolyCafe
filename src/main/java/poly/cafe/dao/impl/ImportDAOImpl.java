package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.Suppliers;
import poly.cafe.entity.Import;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.ImportDAO;

public class ImportDAOImpl implements ImportDAO {

    final String INSERT_SQL = "INSERT INTO Import (UserName, supplier_id, total_amount, import_date) VALUES (?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Import SET UserName = ?, supplier_id = ?, total_amount = ?, import_date = ? WHERE import_id = ?";
    final String DELETE_SQL = "DELETE FROM Import WHERE import_id = ?";
    final String DELETE_CHI_TIET_SQL = "DELETE FROM ImportDetails WHERE import_id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM v_Import";
    final String SELECT_BY_ID_SQL = "SELECT * FROM v_Import WHERE import_id = ?";
    final String SELECT_BY_DATE_RANGE_SQL = "SELECT * FROM v_Import WHERE import_date BETWEEN ? AND ?";
    final String SELECT_DATE_AND_TRANG_THAI_SQL = "SELECT * FROM v_Import WHERE import_date BETWEEN ? AND ? AND userName = ?";



    @Override
    public Import create(Import entity) {
        XJdbc.executeUpdate(INSERT_SQL,
            entity.getUserName(),
            entity.getSupplierId(),
            entity.getTotalAmount(),
            entity.getImportDate()
        );
        return entity;
    }

    @Override
    public void update(Import entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
            entity.getUserName(),
            entity.getSupplierId(),
            entity.getTotalAmount(),
            entity.getImportDate(),
            entity.getImportId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_CHI_TIET_SQL, id);
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Import> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Import findById(Integer id) {
        List<Import> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<Import> selectBySql(String sql, Object... args) {
    List<Import> list = new ArrayList<>();
    try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
        while (rs.next()) {
            Import nk = new Import();
            nk.setImportId(rs.getInt("import_id"));
            nk.setUserName(rs.getString("UserName"));
            nk.setTotalAmount(rs.getDouble("total_amount"));
            nk.setImportDate(rs.getDate("import_date"));
            nk.setSupplierId(rs.getInt("supplier_id"));

            // Tạo NhaCungCap từ ResultSet
            Suppliers ncc = new Suppliers();
            ncc.setSupplierId(rs.getInt("supplier_id"));
            ncc.setSupplierName(rs.getString("supplier_name"));
            ncc.setPhoneNumber(rs.getString("PhoneNumber"));
            ncc.setEmail(rs.getString("email"));
            ncc.setAddress(rs.getString("Address"));

            nk.setSupplier(ncc); // Gán NCC vào NhapKho

            list.add(nk);
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return list;
}

    @Override
    public int insertAndReturnId(Import entity) {
        try {
            ResultSet rs = XJdbc.executeUpdateAndReturnGeneratedKeys(INSERT_SQL,
                entity.getUserName(),
                entity.getSupplier().getSupplierId(),
                entity.getTotalAmount(),
                entity.getImportDate()
            );

            if (rs.next()) {
                return rs.getInt(1); // Lấy Don_hang_id vừa được tạo
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Hoặc ném lỗi nếu insert thất bại
    }
    @Override
    public List<Import> findByImportDate(LocalDate fromDate, LocalDate toDate) {
        return selectBySql(
                SELECT_BY_DATE_RANGE_SQL,
                java.sql.Date.valueOf(fromDate),
                java.sql.Date.valueOf(toDate)
        );
    }
    @Override
    public List<Import> findByImportDateAndUsername(LocalDate fromDate, LocalDate toDate, String userName) {
        return selectBySql(SELECT_DATE_AND_TRANG_THAI_SQL,
                java.sql.Date.valueOf(fromDate),
                java.sql.Date.valueOf(toDate),
                userName
        );
    }
}
