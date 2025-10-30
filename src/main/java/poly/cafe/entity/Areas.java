package poly.cafe.entity;

public class Areas {

    private int areaId;
    private String areaName;
    private String description;
    private String status;

    public Areas() {
    }

    public Areas(int areaId, String areaName, String description, String status) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.description = description;
        this.status = status;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return areaName;
    }
}
