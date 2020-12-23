package semiVo;

import java.sql.Date;

public class AsktableVo {
    private int askid;
    private int askcat;
    private String title;
    private String context;
    private Date askdate;
    private String image;
    private int memid;
	public AsktableVo() {
	}
	public AsktableVo(int askid, int memid, int askcat, String title, String context, Date askdate, String image) {
		super();
		this.askid = askid;
		this.askcat = askcat;
		this.title = title;
		this.context = context;
		this.askdate = askdate;
		this.image = image;
		this.memid = memid;
	}
	public int getAskid() {
		return askid;
	}
	public void setAskid(int askid) {
		this.askid = askid;
	}
	public int getAskcat() {
		return askcat;
	}
	public void setAskcat(int askcat) {
		this.askcat = askcat;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Date getAskdate() {
		return askdate;
	}
	public void setAskdate(Date askdate) {
		this.askdate = askdate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getMemid() {
		return memid;
	}
	public void setMemid(int memid) {
		this.memid = memid;
	}
	
    
}
