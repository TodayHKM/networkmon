package rewriteweb.bean;

import java.math.BigDecimal;

public class warns {
    private String id;

    private String xxwb;

    private BigDecimal xxlx;

    private String bjsj;

    private String rwId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getXxwb() {
        return xxwb;
    }

    public void setXxwb(String xxwb) {
        this.xxwb = xxwb == null ? null : xxwb.trim();
    }

    public BigDecimal getXxlx() {
        return xxlx;
    }

    public void setXxlx(BigDecimal xxlx) {
        this.xxlx = xxlx;
    }

    public String getBjsj() {
        return bjsj;
    }

    public void setBjsj(String bjsj) {
        this.bjsj = bjsj == null ? null : bjsj.trim();
    }

    public String getRwId() {
        return rwId;
    }

    public void setRwId(String rwId) {
        this.rwId = rwId == null ? null : rwId.trim();
    }
}