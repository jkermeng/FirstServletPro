package entity;

public class HotProduce {
    private Integer hpId;//热门货物id
    private String hpFileName;//图片
    private String hpName;//热门货物名称
    private Double hpPrice;//热门货物价格

    public HotProduce(Integer hpId, String hpFileName, String hpName, Double hpPrice) {
        this.hpId = hpId;
        this.hpFileName = hpFileName;
        this.hpName = hpName;
        this.hpPrice = hpPrice;
    }

    public HotProduce() {
    }

    public Integer getHpId() {
        return hpId;
    }

    public void setHpId(Integer hpId) {
        this.hpId = hpId;
    }

    public String getHpFileName() {
        return hpFileName;
    }

    public void setHpFileName(String hpFileName) {
        this.hpFileName = hpFileName;
    }

    public String getHpName() {
        return hpName;
    }

    public void setHpName(String hpName) {
        this.hpName = hpName;
    }

    public Double getHpPrice() {
        return hpPrice;
    }

    public void setHpPrice(Double hpPrice) {
        this.hpPrice = hpPrice;
    }
}
