package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.Orders;
import poly.cafe.util.XJdbc;
import java.sql.Date;
import java.time.LocalDate;
import poly.cafe.entity.Customers;
import poly.cafe.dao.OrdersDAO;

public class OrdersDAOImpl implements OrdersDAO {

    final String INSERT_SQL = "INSERT INTO Orders (customer_id, address, total_amount, order_date, delivery_date, status) VALUES (?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Orders SET customer_id = ?, address = ?, total_amount = ?, order_date = ?, delivery_date = ?, status = ? WHERE order_id = ?";
    final String DELETE_SQL = "DELETE FROM Orders WHERE order_id = ?";
    final String DELETE_CHI_TIET_SQL = "DELETE FROM OrderDetails WHERE order_id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM v_Orders";
    final String SELECT_BY_ID_SQL = "SELECT * FROM v_Orders WHERE order_id = ?";
    final String SELECT_BY_DATE_RANGE_SQL = "SELECT * FROM v_Orders WHERE order_date BETWEEN ? AND ?";
    final String SELECT_DATE_AND_TRANG_THAI_SQL = "SELECT * FROM v_Orders WHERE order_date BETWEEN ? AND ? AND status = ?";


    @Override
    public Orders create(Orders entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getCustomer().getCustomerId(),
                entity.getAddress(),
                entity.getTotalAmount(),
                new Date(entity.getOrderDate().getTime()),
                new Date(entity.getDeliveryDate().getTime()),
                entity.getStatus()
        );
        return entity;
    }

    @Override
public void update(Orders entity) {
    XJdbc.executeUpdate(UPDATE_SQL,
            entity.getCustomer().getCustomerId(),
            entity.getAddress(),
            entity.getTotalAmount(),
            entity.getOrderDate() != null ? new Date(entity.getOrderDate().getTime()) : null,
            entity.getDeliveryDate() != null ? new Date(entity.getDeliveryDate().getTime()) : null,
            entity.getStatus(),
            entity.getOrderId()
    );
}


    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_CHI_TIET_SQL, id);
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Orders> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Orders findById(Integer id) {
        List<Orders> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    protected List<Orders> selectBySql(String sql, Object... args) {
        List<Orders> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                Orders dh = new Orders();
                dh.setOrderId(rs.getInt("order_id"));
                dh.setAddress(rs.getString("address"));
                dh.setTotalAmount(rs.getDouble("total_amount"));
                dh.setOrderDate(rs.getDate("order_date"));
                dh.setDeliveryDate(rs.getDate("delivery_date"));
                dh.setStatus(rs.getString("status"));

                // Map khách hàng
                Customers kh = new Customers();
                kh.setCustomerId(rs.getInt("customer_id"));
                kh.setCustomerName(rs.getString("customer_name"));
                kh.setPhoneNumber(rs.getString("PhoneNumber"));
                kh.setEmail(rs.getString("email"));
                kh.setBirthDate(rs.getDate("birthdate"));
                dh.setCustomer(kh);

                list.add(dh);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Orders> findByOrderDate(LocalDate fromDate, LocalDate toDate) {
        return selectBySql(
                SELECT_BY_DATE_RANGE_SQL,
                java.sql.Date.valueOf(fromDate),
                java.sql.Date.valueOf(toDate)
        );
    }

    @Override
    public int insertAndReturnId(Orders entity) {
        try {
            ResultSet rs = XJdbc.executeUpdateAndReturnGeneratedKeys(INSERT_SQL,
                    entity.getCustomer().getCustomerId(),
                    entity.getAddress(),
                    entity.getTotalAmount(),
                    entity.getOrderDate()!= null ? new Date(entity.getOrderDate().getTime()) : null,
                    entity.getDeliveryDate()!= null ? new Date(entity.getDeliveryDate().getTime()) : null,
                    entity.getStatus()
            );

            if (rs.next()) {
                return rs.getInt(1); // Lấy Don_hang_id vừa được tạo
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Hoặc ném lỗi nếu insert thất bại
    }

    public List<Orders> findByOrderDateAndStatus(LocalDate fromDate, LocalDate toDate, String status) {
        return selectBySql(SELECT_DATE_AND_TRANG_THAI_SQL,
                java.sql.Date.valueOf(fromDate),
                java.sql.Date.valueOf(toDate),
                status
        );
    }

}
