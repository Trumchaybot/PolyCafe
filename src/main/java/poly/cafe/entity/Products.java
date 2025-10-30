package poly.cafe.entity;

public class Products {
    private int productId;
    private String productName;
    private String description;
    private double unitPrice;
    private int target;
    private double basicSalary;
    private double targetSalary;
    private int quantity;

    public Products() {}

    public Products(int productId, String productName, String description, double unitPrice, int target, double basicSalary, double targetSalary, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.target = target;
        this.basicSalary = basicSalary;
        this.targetSalary = targetSalary;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getTargetSalary() {
        return targetSalary;
    }

    public void setTargetSalary(double targetSalary) {
        this.targetSalary = targetSalary;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
public String toString() {
    return this.getProductName();
}

}
