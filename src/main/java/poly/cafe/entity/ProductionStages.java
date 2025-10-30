package poly.cafe.entity;

public class ProductionStages {
    private int productionStagesId;
    private String productionStagesName;

    public ProductionStages() {
    }

    public ProductionStages(int productionStagesId, String productionStagesName) {
        this.productionStagesId = productionStagesId;
        this.productionStagesName = productionStagesName;
    }

    public int getProductionStagesId() {
        return productionStagesId;
    }

    public void setProductionStagesId(int productionStagesId) {
        this.productionStagesId = productionStagesId;
    }

    public String getProductionStagesName() {
        return productionStagesName;
    }

    public void setProductionStagesName(String productionStagesName) {
        this.productionStagesName = productionStagesName;
    }
    @Override
    public String toString() {
        return productionStagesName;
    }

}
