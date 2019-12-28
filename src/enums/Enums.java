package enums;

public enum Enums {
    SUCCESS(1, "³É¹¦"), FAIL(-1, "Ê§°Ü"), NULL(0, "NULL");
    private int code;
    private String msg;

    private Enums() {
    }

    private Enums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
