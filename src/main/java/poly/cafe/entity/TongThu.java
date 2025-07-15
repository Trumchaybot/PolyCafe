package poly.cafe.entity;

public class TongThu {
    private int ID;
    private int Thang;
    private int Nam;
    private double TongDoanhThu;
    private double TienDonHang;
    private double TienXuatKho;

    // Getter & Setter

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

    public double getTongDoanhThu() {
        return TongDoanhThu;
    }

    public void setTongDoanhThu(double TongDoanhThu) {
        this.TongDoanhThu = TongDoanhThu;
    }

    public double getTienDonHang() {
        return TienDonHang;
    }

    public void setTienDonHang(double TienDonHang) {
        this.TienDonHang = TienDonHang;
    }

    public double getTienXuatKho() {
        return TienXuatKho;
    }

    public void setTienXuatKho(double TienXuatKho) {
        this.TienXuatKho = TienXuatKho;
    }
}
