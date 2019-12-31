package rewriteweb.bean;

public class specialurls {
    private String id;

    private String wz;

    private String mswb;

    private Long lx;

    private String rwId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWz() {
        return wz;
    }

    public void setWz(String wz) {
        this.wz = wz == null ? null : wz.trim();
    }

    public String getMswb() {
        return mswb;
    }

    public void setMswb(String mswb) {
        this.mswb = mswb == null ? null : mswb.trim();
    }

    public Long getLx() {
        return lx;
    }

    public void setLx(Long lx) {
        this.lx = lx;
    }

    public String getRwId() {
        return rwId;
    }

    public void setRwId(String rwId) {
        this.rwId = rwId == null ? null : rwId.trim();
    }
}