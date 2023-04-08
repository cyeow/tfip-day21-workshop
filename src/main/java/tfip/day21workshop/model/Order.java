package tfip.day21workshop.model;

import org.joda.time.DateTime;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Order implements Jsonable {

    private Integer id;
    private DateTime orderDate;
    private DateTime shippingDate;
    private String shipName;
    private Double shippingFee;
    private Integer customerId;

    public Order() {
    }

    public Order(Integer id, DateTime orderDate, DateTime shippingDate, String shipName, Double shippingFee,
            Integer customerId) {
        this.id = id;
        this.orderDate = orderDate;
        this.shippingDate = shippingDate;
        this.shipName = shipName;
        this.shippingFee = shippingFee;
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }

    public DateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(DateTime shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", orderDate=" + orderDate + ", shippingDate=" + shippingDate + ", shipName="
                + shipName + ", shippingFee=" + shippingFee + ", customerId=" + customerId + "]";
    }

    @Override
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("id", getId())
                .add("order_date", getOrderDate().toString("yyyy-MM-dd"))
                .add("shipping_date", getShippingDate().toString("yyyy-MM-dd"))
                .add("ship_name", getShipName())
                .add("shipping_fee", getShippingFee())
                .add("customer_id", getCustomerId())
                .build();
    }

    public static Order create(SqlRowSet rs) {
        return new Order(
                rs.getInt("id"),
                DateTime.parse(rs.getString("order_date")),
                DateTime.parse(rs.getString("shipping_date")),
                rs.getString("ship_name"),
                rs.getDouble("shipping_fee"),
                rs.getInt("customer_id"));
    }

}
