package enums;

public class Responese {
    private int code;
    private String msg;
    private Object obj;

    public Responese() {
        super();
    }

    public Responese(Enums e) {
        super();
        this.code = e.getCode();
        this.msg = e.getMsg();
    }

    public Responese(Enums e, Object obj) {
        super();
        this.code = e.getCode();
        this.msg = e.getMsg();
        this.obj = obj;
    }

    public Responese(int code, String msg, Object obj) {
        super();
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}
