package onetomanyentity;

import mtomentity.OrderDetails;
import onetooneentity.Users;

import java.util.Set;

public class Orders {
    private Integer oid;// 订单号
    private String ostart_time;// 订单时间
    private String ostatus;// 订单状态
    private Double ototal;// 订单总额
    // 关系
    private Users use;
    private OrderDetails setOrder;

    public Orders() {
        super();
    }

    public Orders(Integer oid) {
        super();
        this.oid = oid;
    }

    public Orders(Integer oid, String ostart_time, String ostatus, Double ototal) {
        super();
        this.oid = oid;
        this.ostart_time = ostart_time;
        this.ostatus = ostatus;
        this.ototal = ototal;
    }

    public Orders(String ostart_time, String ostatus, Double ototal, Users use) {
        this.ostart_time = ostart_time;
        this.ostatus = ostatus;
        this.ototal = ototal;
        this.use = use;
    }

    public Integer getOid() {
        return oid;
    }

    public String getOstart_time() {
        return ostart_time;
    }

    public String getOstatus() {
        return ostatus;
    }

    public Double getOtotal() {
        return ototal;
    }

    public Users getUse() {
        return use;
    }

    public OrderDetails getSetOrder() {
        return setOrder;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public void setOstart_time(String ostart_time) {
        this.ostart_time = ostart_time;
    }

    public void setOstatus(String ostatus) {
        this.ostatus = ostatus;
    }

    public void setOtotal(Double ototal) {
        this.ototal = ototal;
    }

    public void setUse(Users use) {
        this.use = use;
    }

    public void setSetOrder(OrderDetails setOrder) {
        this.setOrder = setOrder;
    }

    @Override
    public String toString() {
        return "Orders [oid=" + oid + ", ostart_time=" + ostart_time + ", ostatus=" + ostatus + ", ototal=" + ototal
                + ", use=" + use + ", setOrder=" + setOrder + "]";
    }

}
