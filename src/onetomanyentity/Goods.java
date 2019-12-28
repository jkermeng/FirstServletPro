package onetomanyentity;

import mtomentity.OrderDetails;

import java.util.List;
import java.util.Set;

public class Goods {
    private Integer gid;// 商品id
    private String gname;// 商品名称
    private Double gprice;// 商品单价
    private Integer gstock;// 商品库存
    private _Classification setClassfy;// fk

    // 关系
    private Set<OrderDetails> odetails;
    private Set<ShopCart> setShoCart;
    private GoodsPicture gp;

    public Goods() {
        super();
    }

    public Goods(String gname, Double gprice, Integer gstock, _Classification setClassfy) {
        super();
        this.gname = gname;
        this.gprice = gprice;
        this.gstock = gstock;
        this.setClassfy = setClassfy;
    }

    public Goods(Integer gid, String gname, Double gprice, Integer gstock, _Classification setClassfy) {
        super();
        this.gid = gid;
        this.gname = gname;
        this.gprice = gprice;
        this.gstock = gstock;
        this.setClassfy = setClassfy;
    }

    public Goods(Integer gid, String gname, Double gprice, Integer gstock) {
        super();
        this.gid = gid;
        this.gname = gname;
        this.gprice = gprice;
        this.gstock = gstock;
    }

    public Goods(Integer gid, String gname, Double gprice) {
        super();
        this.gid = gid;
        this.gname = gname;
        this.gprice = gprice;
    }

    public Goods(Integer gid) {
        super();
        this.gid = gid;
    }

    public GoodsPicture getGp() {
        return gp;
    }

    public void setGp(GoodsPicture gp) {
        this.gp = gp;
    }

    public Integer getGid() {
        return gid;
    }

    public String getGname() {
        return gname;
    }

    public Double getGprice() {
        return gprice;
    }

    public Integer getGstock() {
        return gstock;
    }

    public Set<OrderDetails> getOdetails() {
        return odetails;
    }

    public Set<ShopCart> getSetShoCart() {
        return setShoCart;
    }

    public _Classification getSetClassfy() {
        return setClassfy;
    }


    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public void setGprice(Double gprice) {
        this.gprice = gprice;
    }

    public void setGstock(Integer gstock) {
        this.gstock = gstock;
    }

    public void setOdetails(Set<OrderDetails> odetails) {
        this.odetails = odetails;
    }

    public void setSetShoCart(Set<ShopCart> setShoCart) {
        this.setShoCart = setShoCart;
    }

    public void setSetClassfy(_Classification setClassfy) {
        this.setClassfy = setClassfy;
    }


    @Override
    public String toString() {
        return "Goods{" +
                "gid=" + gid +
                ", gname='" + gname + '\'' +
                ", gprice=" + gprice +
                ", gstock=" + gstock +
                ", setClassfy=" + setClassfy +
                ", odetails=" + odetails +
                ", setShoCart=" + setShoCart +
                ", gp=" + gp +
                '}';
    }
}
