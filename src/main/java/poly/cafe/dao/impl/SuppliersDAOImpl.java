package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.Suppliers;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.SuppliersDAO;

public class SuppliersDAOImpl implements SuppliersDAO {

    final String INSERT_SQL = "INSERT INTO Suppliers (supplier_name, PhoneNumber, email, address) VALUES (?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Suppliers SET supplier_name = ?, PhoneNumber = ?, email = ?, address = ? WHERE supplier_id = ?";
    final String DELETE_SQL = "DELETE FROM Suppliers WHERE supplier_id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Suppliers";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Suppliers WHERE supplier_id = ?";
    final String SELECT_BY_PHONE_SQL = "SELECT * FROM Suppliers WHERE PhoneNumber = ?";

    @Override
    public Suppliers create(Suppliers entity) {
        XJdbc.executeUpdate(INSERT_SQL,
            entity.getSupplierName(),
            entity.getPhoneNumber(),
            entity.getEmail(),
            entity.getAddress());
        return entity;
    }

    @Override
    public void update(Suppliers entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
            entity.getSupplierName(),
            entity.getPhoneNumber(),
            entity.getEmail(),
            entity.getAddress(),
            entity.getSupplierId());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Suppliers> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Suppliers findById(Integer id) {
        List<Suppliers> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<Suppliers> selectBySql(String sql, Object... args) {
        List<Suppliers> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                Suppliers ncc = new Suppliers();
                ncc.setSupplierId(rs.getInt("supplier_id"));
                ncc.setSupplierName(rs.getString("supplier_name"));
                ncc.setPhoneNumber(rs.getString("PhoneNumber"));
                ncc.setEmail(rs.getString("email"));
                ncc.setAddress(rs.getString("address"));
                list.add(ncc);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
public Suppliers findByPhone(String phoneNumber) {
    List<Suppliers> list = selectBySql(SELECT_BY_PHONE_SQL, phoneNumber);
    return list.isEmpty() ? null : list.get(0);
}
}
