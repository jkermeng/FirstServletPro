package onetooneentity;

import onetomanyentity.Orders;
import onetomanyentity.ShopCart;

public class Users {
    private Integer uid;// 用户id
    private String rName;//真实姓名
    private String uname;// 用户名称
    private String uphone;// 用户手机
    private String ugender;// 性别
    private String uemail;//邮箱
    private String upassword;//密码
    private String uhotword;
    // 关系
    private ShopCart scart;
    private Orders order;

    public Users() {
        super();
    }

    public Users(String uname, String uphone, String ugender, String uemail, String upassword) {

        this.uname = uname;
        this.uphone = uphone;
        this.ugender = ugender;
        this.uemail = uemail;
        this.upassword = upassword;
    }

    public String getUhotword() {
        return uhotword;
    }

    public void setUhotword(String uhotword) {
        this.uhotword = uhotword;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public Users(String uname, String uphone) {
        super();
        this.uname = uname;
        this.uphone = uphone;
    }

    public Users(Integer uid) {
        super();
        this.uid = uid;
    }

    public Users(String uphone) {
        super();
        this.uphone = uphone;
    }

    public Users(Integer uid, String uname, String uphone, String ugender) {
        super();
        this.uid = uid;
        this.uname = uname;
        this.uphone = uphone;
        this.ugender = ugender;
    }

    public Integer getUid() {
        return uid;
    }

    public String getUname() {
        return uname;
    }

    public String getUphone() {
        return uphone;
    }

    public String getUgender() {
        return ugender;
    }

    public ShopCart getScart() {
        return scart;
    }

    public Orders getOrder() {
        return order;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public void setUgender(String ugender) {
        this.ugender = ugender;
    }

    public void setScart(ShopCart scart) {
        this.scart = scart;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Users [uid=" + uid + ", uname=" + uname + ", uphone=" + uphone + ", ugender=" + ugender + ", scart="
                + scart + ", order=" + order + "]";
    }

}
