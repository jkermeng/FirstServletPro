package entity;

import java.util.List;

public class PageModel {
    private Integer totalPage;
    private Integer currentPage;
    private String qname;
    private List<HotProduce> list;
    private List pageList;
    private String source;
    private String hpcId;
    private String hpcName;

    public List<HotProduce> getList() {
        return list;
    }

    public String getHpcName() {
        return hpcName;
    }

    public void setHpcName(String hpcName) {
        this.hpcName = hpcName;
    }

    public void setList(List<HotProduce> list) {
        this.list = list;
    }

    public PageModel() {
    }

    public Integer getTotalPage() {
        return totalPage;
    }


    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public List getPageList() {
        return pageList;
    }

    public void setPageList(List pageList) {
        this.pageList = pageList;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHpcId() {
        return hpcId;
    }

    public void setHpcId(String hpcId) {
        this.hpcId = hpcId;
    }
}
