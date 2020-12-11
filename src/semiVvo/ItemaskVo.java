package semiVvo;

import java.sql.Date;

public class ItemaskVo {
	private int iaskid;
	private int itemid;
	private int memid;
	private String context;
	private Date iaskdate;
	private String anscontext;
	private Date ansdate;
	
	public ItemaskVo() {}

	public ItemaskVo(int iaskid, int itemid, int memid, String context, Date iaskdate, String anscontext, Date ansdate) {
		super();
		this.iaskid = iaskid;
		this.itemid = itemid;
		this.memid = memid;
		this.context = context;
		this.iaskdate = iaskdate;
		this.anscontext = anscontext;
		this.ansdate = ansdate;
	}

	public int getIaskid() {
		return iaskid;
	}

	public void setIaskid(int iaskid) {
		this.iaskid = iaskid;
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

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getIaskdate() {
		return iaskdate;
	}

	public void setIaskdate(Date iaskdate) {
		this.iaskdate = iaskdate;
	}

	public String getAnscontext() {
		return anscontext;
	}

	public void setAnscontext(String anscontext) {
		this.anscontext = anscontext;
	}

	public Date getAnsdate() {
		return ansdate;
	}

	public void setAnsdate(Date ansdate) {
		this.ansdate = ansdate;
	}
}
