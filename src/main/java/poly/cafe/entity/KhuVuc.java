package poly.cafe.entity;

public class KhuVuc {
    private int khuVucId;
    private String tenKhuVuc;
    private String moTa;

    public KhuVuc() {
    }

    public KhuVuc(int khuVucId, String tenKhuVuc, String moTa) {
        this.khuVucId = khuVucId;
        this.tenKhuVuc = tenKhuVuc;
        this.moTa = moTa;
    }

    public int getKhuVucId() {
        return khuVucId;
    }

    public void setKhuVucId(int khuVucId) {
        this.khuVucId = khuVucId;
    }

    public String getTenKhuVuc() {
        return tenKhuVuc;
    }

    public void setTenKhuVuc(String tenKhuVuc) {
        this.tenKhuVuc = tenKhuVuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
