package poly.cafe.entity;

public class NguyenVatLieu {
    private int vatLieuId;
    private String tenVatLieu;
    private String donVi;
    private double donGia;
    private int soLuongTon;

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getVatLieuId() {
        return vatLieuId;
    }

    public void setVatLieuId(int vatLieuId) {
        this.vatLieuId = vatLieuId;
    }

    public String getTenVatLieu() {
        return tenVatLieu;
    }

    public void setTenVatLieu(String tenVatLieu) {
        this.tenVatLieu = tenVatLieu;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
}
    