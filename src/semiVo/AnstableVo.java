package semiVo;

import java.sql.Date;

public class AnstableVo {
    private int ansid;
    private int askid;
    private int memid;
    private String context;
    private String image;
    private Date ansdate;

    public AnstableVo() {
    }

    public AnstableVo(int ansid, int askid, int memid, String context, String image, Date ansdate) {
        this.ansid = ansid;
        this.askid = askid;
        this.memid = memid;
        this.context = context;
        this.image = image;
        this.ansdate = ansdate;
    }

    public int getAnsid() {
        return this.ansid;
    }

    public void setAnsid(int ansid) {
        this.ansid = ansid;
    }

    public int getAskid() {
        return this.askid;
    }

    public void setAskid(int askid) {
        this.askid = askid;
    }

    public int getMemid() {
        return this.memid;
    }

    public void setMemid(int memid) {
        this.memid = memid;
    }

    public String getContext() {
        return this.context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getAnsdate() {
        return this.ansdate;
    }

    public void setAnsdate(Date ansdate) {
        this.ansdate = ansdate;
    }

}
