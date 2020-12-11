package semiVvo;

import java.sql.Date;

public class AsktableVo {
    private int askid;
    private int askcat;
    private String title;
    private String context;
    private Date askdate;
    private String image;

    public AsktableVo() {
    }

    public AsktableVo(int askid, int askcat, String title, String context, Date askdate, String image) {
        this.askid = askid;
        this.askcat = askcat;
        this.title = title;
        this.context = context;
        this.askdate = askdate;
        this.image = image;
    }

    public int getAskid() {
        return this.askid;
    }

    public void setAskid(int askid) {
        this.askid = askid;
    }

    public int getAskcat() {
        return this.askcat;
    }

    public void setAskcat(int askcat) {
        this.askcat = askcat;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return this.context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getAskdate() {
        return this.askdate;
    }

    public void setAskdate(Date askdate) {
        this.askdate = askdate;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
