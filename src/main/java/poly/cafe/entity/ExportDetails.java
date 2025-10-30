package poly.cafe.entity;

public class ExportDetails {
    private int id;
    private int exportId;
    private int materialId;
    private Materials material;
    private int quantity;
    private double totalAmount;

    public ExportDetails() {
    }

    public ExportDetails(int id, int exportId, int materialId, Materials material, int quantity, double totalAmount) {
        this.id = id;
        this.exportId = exportId;
        this.materialId = materialId;
        this.material = material;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExportId() {
        return exportId;
    }

    public void setExportId(int exportId) {
        this.exportId = exportId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public Materials getMaterial() {
        return material;
    }

    public void setMaterial(Materials material) {
        this.material = material;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    
}
