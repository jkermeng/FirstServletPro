package entity;

public class HotNews {
    private Integer hnId;
    private String hnTitle;

    public HotNews() {
    }


    public HotNews(Integer hnId, String hnTitle) {
        this.hnId = hnId;
        this.hnTitle = hnTitle;
    }

    public Integer getHnId() {
        return hnId;
    }

    public void setHnId(Integer hnId) {
        this.hnId = hnId;
    }

    public String getHnTitle() {
        return hnTitle;
    }

    public void setHnTitle(String hnTitle) {
        this.hnTitle = hnTitle;
    }
}
