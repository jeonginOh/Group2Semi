package semiVo;

import java.sql.Date;

public class AnstableVo {
    private int ansid;
    private int askid;
    private String context;
    private String image;
    private Date ansdate;
	public AnstableVo() {
		
	}
	public AnstableVo(int ansid, int askid, String context, String image, Date ansdate) {
		super();
		this.ansid = ansid;
		this.askid = askid;
		this.context = context;
		this.image = image;
		this.ansdate = ansdate;
	}
	public int getAnsid() {
		return ansid;
	}
	public void setAnsid(int ansid) {
		this.ansid = ansid;
	}
	public int getAskid() {
		return askid;
	}
	public void setAskid(int askid) {
		this.askid = askid;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getAnsdate() {
		return ansdate;
	}
	public void setAnsdate(Date ansdate) {
		this.ansdate = ansdate;
	}
	

}
