package rewriteweb.bean;

public class checktask {
    private String id;

    private String rwId;

    private String wzzt;

    private Long urlYccs;

    private Long ymjzyccs;

    private Long ljcsyccs;

    private String jckssj;

    private String jcjssj;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRwId() {
        return rwId;
    }

    public void setRwId(String rwId) {
        this.rwId = rwId == null ? null : rwId.trim();
    }

    public String getWzzt() {
        return wzzt;
    }

    public void setWzzt(String wzzt) {
        this.wzzt = wzzt == null ? null : wzzt.trim();
    }

    public Long getUrlYccs() {
        return urlYccs;
    }

    public void setUrlYccs(Long urlYccs) {
        this.urlYccs = urlYccs;
    }

    public Long getYmjzyccs() {
        return ymjzyccs;
    }

    public void setYmjzyccs(Long ymjzyccs) {
        this.ymjzyccs = ymjzyccs;
    }

    public Long getLjcsyccs() {
        return ljcsyccs;
    }

    public void setLjcsyccs(Long ljcsyccs) {
        this.ljcsyccs = ljcsyccs;
    }

    public String getJckssj() {
        return jckssj;
    }

    public void setJckssj(String jckssj) {
        this.jckssj = jckssj == null ? null : jckssj.trim();
    }

    public String getJcjssj() {
        return jcjssj;
    }

    public void setJcjssj(String jcjssj) {
        this.jcjssj = jcjssj == null ? null : jcjssj.trim();
    }
}