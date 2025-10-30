package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.dao.OTPDAO;
import poly.cafe.entity.OTP;
import poly.cafe.util.XJdbc;

public class OTPDAOImpl implements OTPDAO {

    final String INSERT_SQL = "INSERT INTO OTP (UserName, Email, OTP_Code, CreatedAt, ExpiredAt) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE OTP SET UserName = ?, Email = ?, OTP_Code = ?, CreatedAt = ?, ExpiredAt = ? WHERE id_OTP = ?";
    final String DELETE_SQL = "DELETE FROM OTP WHERE id_OTP = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM OTP";
    final String SELECT_BY_ID_SQL = "SELECT * FROM OTP WHERE id_OTP = ?";
    final String SELECT_BY_USERNAME_SQL = "SELECT * FROM OTP WHERE UserName = ?";
    final String SELECT_BY_EMAIL_SQL = "SELECT * FROM OTP WHERE Email = ?";
    final String SELECT_LATEST_BY_USER_SQL = "SELECT TOP 1 * FROM OTP WHERE UserName = ? AND Email = ? ORDER BY CreatedAt DESC";


    @Override
    public OTP create(OTP entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getUserName(),
                entity.getEmail(),
                entity.getOtpCode(),
                entity.getCreatedAt(),
                entity.getExpiredAt()
        );
        return entity;
    }

    @Override
    public void update(OTP entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getUserName(),
                entity.getEmail(),
                entity.getOtpCode(),
                entity.getCreatedAt(),
                entity.getExpiredAt(),
                entity.getIdOTP()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public OTP findById(Integer id) {
        List<OTP> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public OTP findByUserName(String userName) {
        List<OTP> list = selectBySql(SELECT_BY_USERNAME_SQL, userName);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public OTP findByEmail(String email) {
        List<OTP> list = selectBySql(SELECT_BY_EMAIL_SQL, email);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<OTP> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    private List<OTP> selectBySql(String sql, Object... args) {
        List<OTP> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                OTP otp = new OTP();
                otp.setIdOTP(rs.getInt("id_OTP"));
                otp.setUserName(rs.getString("UserName"));
                otp.setEmail(rs.getString("Email"));
                otp.setOtpCode(rs.getString("OTP_Code"));
                otp.setCreatedAt(rs.getTimestamp("CreatedAt"));
                otp.setExpiredAt(rs.getTimestamp("ExpiredAt"));
                list.add(otp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    @Override
    public OTP findLatestByUser(String userName, String email) {
        List<OTP> list = selectBySql(SELECT_LATEST_BY_USER_SQL, userName, email);
        return list.isEmpty() ? null : list.get(0);
    }
}
