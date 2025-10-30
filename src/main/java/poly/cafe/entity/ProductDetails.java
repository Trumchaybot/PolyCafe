package poly.cafe.entity;

public class ProductDetails {
    private int id;
    private int productId;
    private Materials material;
    private int materialId;
    private int quantity;

    public ProductDetails() {
    }

    public ProductDetails(int id, int productId, Materials material, int materialId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.material = material;
        this.materialId = materialId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Materials getMaterial() {
        return material;
    }

    public void setMaterial(Materials material) {
        this.material = material;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
