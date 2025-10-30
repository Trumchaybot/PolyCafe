package poly.cafe.entity;

public class ImportDetails {
    private int id;
    private int importId;
    private int materialId;
    private Materials material;
    private int quantity;
    private double totalAmount;

    public ImportDetails() {
    }

    public ImportDetails(int id, int importId, int materialId, Materials material, int quantity, double totalAmount) {
        this.id = id;
        this.importId = importId;
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

    public int getImportId() {
        return importId;
    }

    public void setImportId(int importId) {
        this.importId = importId;
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
