package com.qiqi.model.VO;

/**
 * @date 2018/12/21 14:15
 */
public class ProductionModel {
    private static final long serialVersionUID = 3943336467951175834L;

    private String productName;

    private String productNumber;

    private String deviceType;

    private String productIcon;

    private String description;

    private boolean hide;

    private String appId;

    public ProductionModel() {
    }

    public ProductionModel(String productName, String productNumber, String deviceType, String appId) {
        this.productName = productName;
        this.productNumber = productNumber;
        this.deviceType = deviceType;
        this.appId = appId;
    }
    public String getProductName() {
        return productName;
    }

    public ProductionModel setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public ProductionModel setProductNumber(String productNumber) {
        this.productNumber = productNumber;
        return this;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public ProductionModel setDeviceType(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public ProductionModel setProductIcon(String productIcon) {
        this.productIcon = productIcon;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductionModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isHide() {
        return hide;
    }

    public ProductionModel setHide(boolean hide) {
        this.hide = hide;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public ProductionModel setAppId(String appId) {
        this.appId = appId;
        return this;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "SimpleDeviceModel{" +
                "productName='" + productName + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", productIcon='" + productIcon + '\'' +
                ", description='" + description + '\'' +
                ", hide=" + hide +
                ", appId='" + appId + '\'' +
                '}';
    }
}
