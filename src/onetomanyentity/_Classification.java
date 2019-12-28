package onetomanyentity;

import java.util.List;
import java.util.Set;

public class _Classification {
    private Integer cid;// 类别id
    private String cname;// 类别名
    // 关系
    private List<Goods> setgoods;

    public _Classification() {
        super();
    }

    public _Classification(Integer cid) {
        super();
        this.cid = cid;
    }

    public _Classification(Integer cid, String cname) {
        super();
        this.cid = cid;
        this.cname = cname;
    }

    public Integer getCid() {
        return cid;
    }

    public String getCname() {
        return cname;
    }

    public List<Goods> getSetgoods() {
        return setgoods;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setSetgoods(List<Goods> setgoods) {
        this.setgoods = setgoods;
    }

    @Override
    public String toString() {
        return "_Classification [cid=" + cid + ", cname=" + cname + ", setgoods=" + setgoods + "]";
    }

}
