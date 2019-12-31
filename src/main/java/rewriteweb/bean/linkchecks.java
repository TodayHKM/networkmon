package rewriteweb.bean;

import java.math.BigDecimal;

public class linkchecks {
    private String id;

    private String ljUrl;

    private String ljwb;

    private String ztxx;

    private String sfcs;

    private String zt;

    private String ljjcrq;

    private String ljszwyId;

    private String ssrwId;

    private BigDecimal ljlx;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLjUrl() {
        return ljUrl;
    }

    public void setLjUrl(String ljUrl) {
        this.ljUrl = ljUrl == null ? null : ljUrl.trim();
    }

    public String getLjwb() {
        return ljwb;
    }

    public void setLjwb(String ljwb) {
        this.ljwb = ljwb == null ? null : ljwb.trim();
    }

    public String getZtxx() {
        return ztxx;
    }

    public void setZtxx(String ztxx) {
        this.ztxx = ztxx == null ? null : ztxx.trim();
    }

    public String getSfcs() {
        return sfcs;
    }

    public void setSfcs(String sfcs) {
        this.sfcs = sfcs == null ? null : sfcs.trim();
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt == null ? null : zt.trim();
    }

    public String getLjjcrq() {
        return ljjcrq;
    }

    public void setLjjcrq(String ljjcrq) {
        this.ljjcrq = ljjcrq == null ? null : ljjcrq.trim();
    }

    public String getLjszwyId() {
        return ljszwyId;
    }

    public void setLjszwyId(String ljszwyId) {
        this.ljszwyId = ljszwyId == null ? null : ljszwyId.trim();
    }

    public String getSsrwId() {
        return ssrwId;
    }

    public void setSsrwId(String ssrwId) {
        this.ssrwId = ssrwId == null ? null : ssrwId.trim();
    }

    public BigDecimal getLjlx() {
        return ljlx;
    }

    public void setLjlx(BigDecimal ljlx) {
        this.ljlx = ljlx;
    }
}