package onetomanyentity;

public class GoodsPicture {
    private Integer pid;// Í¼Æ¬id
    private String pname;// Ãû³Æ
    private String purl;// Í¼Æ¬URL
    private Goods goods_gid;
    // ¹ØÏµ
    private Goods good;

    public GoodsPicture() {
        super();
    }

    public GoodsPicture(Integer pid, String pname, String purl) {
        super();
        this.pid = pid;
        this.pname = pname;
        this.purl = purl;
    }

    public Integer getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public String getPurl() {
        return purl;
    }

    public Goods getGoods_gid() {
        return goods_gid;
    }

    public void setGoods_gid(Goods goods_gid) {
        this.goods_gid = goods_gid;
    }

    public Goods getGood() {
        return good;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public void setGood(Goods good) {
        this.good = good;
    }

}
