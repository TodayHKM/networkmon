package rewriteweb.bean;

public class pagechecks {
    private String id;

    private String yurlId;

    private String ybt;

    private Long ylx;

    private String yjzzt;

    private Long ynhls;

    private String ssrwId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getYurlId() {
        return yurlId;
    }

    public void setYurlId(String yurlId) {
        this.yurlId = yurlId == null ? null : yurlId.trim();
    }

    public String getYbt() {
        return ybt;
    }

    public void setYbt(String ybt) {
        this.ybt = ybt == null ? null : ybt.trim();
    }

    public Long getYlx() {
        return ylx;
    }

    public void setYlx(Long ylx) {
        this.ylx = ylx;
    }

    public String getYjzzt() {
        return yjzzt;
    }

    public void setYjzzt(String yjzzt) {
        this.yjzzt = yjzzt == null ? null : yjzzt.trim();
    }

    public Long getYnhls() {
        return ynhls;
    }

    public void setYnhls(Long ynhls) {
        this.ynhls = ynhls;
    }

    public String getSsrwId() {
        return ssrwId;
    }

    public void setSsrwId(String ssrwId) {
        this.ssrwId = ssrwId == null ? null : ssrwId.trim();
    }
}