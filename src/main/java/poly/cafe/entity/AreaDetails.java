package poly.cafe.entity;

import java.util.Date;

public class AreaDetails {
    private int id;
    private int areaId;
    private String userName;
    private int roleId;
    private Role role;
    private int productionStagesId;
    private ProductionStages productionStage;
    private Date assignDate;

    public AreaDetails() {
    }

    public AreaDetails(int id, int areaId, String userName, int roleId, Role role, int productionStagesId, ProductionStages productionStage, Date assignDate) {
        this.id = id;
        this.areaId = areaId;
        this.userName = userName;
        this.roleId = roleId;
        this.role = role;
        this.productionStagesId = productionStagesId;
        this.productionStage = productionStage;
        this.assignDate = assignDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getProductionStagesId() {
        return productionStagesId;
    }

    public void setProductionStagesId(int productionStagesId) {
        this.productionStagesId = productionStagesId;
    }

    public ProductionStages getProductionStage() {
        return productionStage;
    }

    public void setProductionStage(ProductionStages productionStage) {
        this.productionStage = productionStage;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    
}
