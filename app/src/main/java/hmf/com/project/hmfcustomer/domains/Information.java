package hmf.com.project.hmfcustomer.domains;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by home on 6/14/2018.
 */

public class Information {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("consumerType")
    @Expose
    private String consumerType;
    @SerializedName("currentAddress")
    @Expose
    private String currentAddress;
    @SerializedName("deliveryAddress")
    @Expose
    private String deliveryAddress;
    @SerializedName("warehouseAddress")
    @Expose
    private String warehouseAddress;
    @SerializedName("photoUrl")
    @Expose
    private String photoUrl;
    @SerializedName("orders")
    @Expose
    private List<String> orders = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tenantId")
    @Expose
    private String tenantId;
    @SerializedName("tenantname")
    @Expose
    private String tenantname;
    @SerializedName("b2BInvestmentDetails")
    @Expose
    private B2BInvestmentDetails b2BInvestmentDetails;
    @SerializedName("b2CInvestmentDetails")
    @Expose
    private B2CInvestmentDetails b2CInvestmentDetails;

    @Override
    public String toString() {
        return "Information{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", consumerType='" + consumerType + '\'' +
                ", currentAddress='" + currentAddress + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", warehouseAddress='" + warehouseAddress + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", orders=" + orders +
                ", status='" + status + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", tenantname='" + tenantname + '\'' +
                ", b2BInvestmentDetails=" + b2BInvestmentDetails +
                ", b2CInvestmentDetails=" + b2CInvestmentDetails +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConsumerType() {
        return consumerType;
    }

    public void setConsumerType(String consumerType) {
        this.consumerType = consumerType;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantname() {
        return tenantname;
    }

    public void setTenantname(String tenantname) {
        this.tenantname = tenantname;
    }

    public B2BInvestmentDetails getB2BInvestmentDetails() {
        return b2BInvestmentDetails;
    }

    public void setB2BInvestmentDetails(B2BInvestmentDetails b2BInvestmentDetails) {
        this.b2BInvestmentDetails = b2BInvestmentDetails;
    }

    public B2CInvestmentDetails getB2CInvestmentDetails() {
        return b2CInvestmentDetails;
    }

    public void setB2CInvestmentDetails(B2CInvestmentDetails b2CInvestmentDetails) {
        this.b2CInvestmentDetails = b2CInvestmentDetails;
    }


}
