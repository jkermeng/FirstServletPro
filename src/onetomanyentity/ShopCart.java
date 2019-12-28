package onetomanyentity;

import onetooneentity.Users;

import java.util.Set;

public class ShopCart {
    private Integer cid;// 购物车id
    private String gtime;// 时间
    private Integer gnumber;// 数量
    private Goods g;
    // 关系
    private Users use;
    private Set<Goods> setgoods;

    public ShopCart(String gtime, Integer gnumber) {
        super();
        this.gtime = gtime;
        this.gnumber = gnumber;
    }

    public Goods getG() {
        return g;
    }

    public void setG(Goods g) {
        this.g = g;
    }

    public String getGtime() {
        return gtime;
    }

    public Integer getGnumber() {
        return gnumber;
    }

    public void setGtime(String gtime) {
        this.gtime = gtime;
    }

    public void setGnumber(Integer gnumber) {
        this.gnumber = gnumber;
    }

    public ShopCart() {
        super();
    }

    public ShopCart(Integer cid) {
        super();
        this.cid = cid;
    }

    public ShopCart(Integer cid, String gtime, Integer gnumber) {
        super();
        this.cid = cid;
        this.gtime = gtime;
        this.gnumber = gnumber;
    }

    public Integer getCid() {
        return cid;
    }

    public Users getUse() {
        return use;
    }

    public Set<Goods> getSetgoods() {
        return setgoods;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setUse(Users use) {
        this.use = use;
    }

    public void setSetgoods(Set<Goods> setgoods) {
        this.setgoods = setgoods;
    }

    @Override
    public String toString() {
        return "ShopCart [cid=" + cid + ", gtime=" + gtime + ", gnumber=" + gnumber + ", g=" + g + "]";
    }


}
