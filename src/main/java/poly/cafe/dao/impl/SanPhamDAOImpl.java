package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.*;
import poly.cafe.dao.SanPhamDAO;
import poly.cafe.entity.SanPham;
import poly.cafe.util.XJdbc;

public class SanPhamDAOImpl implements SanPhamDAO {

    String insert = "INSERT INTO SanPham (San_pham_ID, Ten_san_Pham, Mo_ta) VALUES (?, ?, ?)";
    String update = "UPDATE SanPham SET Ten_san_Pham = ?, Mo_ta = ? WHERE San_pham_ID = ?";
    String delete = "DELETE FROM SanPham WHERE San_pham_ID = ?";
    String selectAll = "SELECT * FROM SanPham";
    String selectById = "SELECT * FROM SanPham WHERE San_pham_ID = ?";

    @Override
    public SanPham create(SanPham entity) {
        XJdbc.executeUpdate(insert, entity.getSanPhamId(), entity.getTenSanPham(), entity.getMoTa());
        return entity;
    }

    @Override
    public void update(SanPham entity) {
        XJdbc.executeUpdate(update, entity.getTenSanPham(), entity.getMoTa(), entity.getSanPhamId());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(delete, id);
    }

    @Override
    public List<SanPham> findAll() {
        List<SanPham> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(selectAll)) {
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setSanPhamId(rs.getInt("San_pham_ID"));
                sp.setTenSanPham(rs.getString("Ten_san_Pham"));
                sp.setMoTa(rs.getString("Mo_ta"));
                sp.setGiaTien(rs.getDouble("Gia_tien"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public SanPham findById(Integer id) {
        try (ResultSet rs = XJdbc.executeQuery(selectById, id)) {
            if (rs.next()) {
                return new SanPham(
                    rs.getInt("San_pham_ID"),
                    rs.getString("Ten_san_Pham"),
                    rs.getString("Mo_ta"),
                    rs.getDouble("Gia_tien")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
