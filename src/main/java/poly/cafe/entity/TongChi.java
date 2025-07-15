package poly.cafe.entity;

public class TongChi {
    private int ID;
    private int Thang;
    private int Nam;
    private double TongChiTieu;
    private double TienLuong;
    private double TienNhapKho;

    // Getters & Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getThang() {
        return Thang;
    }

    public void setThang(int Thang) {
        this.Thang = Thang;
    }

    public int getNam() {
        return Nam;
    }

    public void setNam(int Nam) {
        this.Nam = Nam;
    }

    public double getTongChiTieu() {
        return TongChiTieu;
    }

    public void setTongChiTieu(double TongChiTieu) {
        this.TongChiTieu = TongChiTieu;
    }

    public double getTienLuong() {
        return TienLuong;
    }

    public void setTienLuong(double TienLuong) {
        this.TienLuong = TienLuong;
    }

    public double getTienNhapKho() {
        return TienNhapKho;
    }

    public void setTienNhapKho(double TienNhapKho) {
        this.TienNhapKho = TienNhapKho;
    }
}
