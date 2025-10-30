package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.Customers;
import poly.cafe.util.XJdbc;
import java.sql.Date;
import poly.cafe.dao.CustomersDAO;

public class CustomersDAOImpl implements CustomersDAO {

    final String INSERT_SQL = "INSERT INTO Customers (customer_name, birthdate, PhoneNumber, email) VALUES (?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Customers SET customer_name = ?, birthdate = ?, PhoneNumber = ?, email = ? WHERE customer_id = ?";
    final String DELETE_SQL = "DELETE FROM Customers WHERE customer_id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Customers";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Customers WHERE customer_id = ?";
    final String SELECT_BY_PHONE_SQL = "SELECT * FROM Customers WHERE PhoneNumber = ?";


    @Override
    public Customers create(Customers entity) {
        XJdbc.executeUpdate(INSERT_SQL,
            entity.getCustomerName(),
            new Date(entity.getBirthDate().getTime()),
            entity.getPhoneNumber(),
            entity.getEmail()
        );
        return entity;
    }

    @Override
    public void update(Customers entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
            entity.getCustomerName(),
            new Date(entity.getBirthDate().getTime()),
            entity.getPhoneNumber(),
            entity.getEmail(),
            entity.getCustomerId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Customers> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Customers findById(Integer id) {
        List<Customers> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<Customers> selectBySql(String sql, Object... args) {
        List<Customers> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                Customers entity = new Customers();
                entity.setCustomerId(rs.getInt("customer_id"));
                entity.setCustomerName(rs.getString("customer_name"));
                entity.setBirthDate(rs.getDate("birthdate"));
                entity.setPhoneNumber(rs.getString("PhoneNumber"));
                entity.setEmail(rs.getString("email"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
public Customers findByPhone(String sdt) {
    List<Customers> list = selectBySql(SELECT_BY_PHONE_SQL, sdt);
    return list.isEmpty() ? null : list.get(0);
}

}
