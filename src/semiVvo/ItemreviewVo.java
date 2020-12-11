package semiVvo;

import java.sql.Date;

public class ItemreviewVo {
	private int revid;
	private int itemid;
	private int memid;
	private String title;
	private String image;
	private String context;
	private int star;
	private Date revdate;
	public ItemreviewVo() {}
	public ItemreviewVo(int revid, int itemid, int memid, String title, String image, String context, int star,
			Date revdate) {
		this.revid = revid;
		this.itemid = itemid;
		this.memid = memid;
		this.title = title;
		this.image = image;
		this.context = context;
		this.star = star;
		this.revdate = revdate;
	}
	public int getRevid() {
		return revid;
	}
	public void setRevid(int revid) {
		this.revid = revid;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public int getMemid() {
		return memid;
	}
	public void setMemid(int memid) {
		this.memid = memid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public Date getRevdate() {
		return revdate;
	}
	public void setRevdate(Date revdate) {
		this.revdate = revdate;
	}
	
	//새로올림
}
