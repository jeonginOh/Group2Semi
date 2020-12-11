package semiVo;

import java.sql.Date;

public class Rev_childVo {
	private int rchildid;
	private int revid;
	private String context;
	private Date rchilddate;
	public Rev_childVo() {}
	public Rev_childVo(int rchildid, int revid, String context, Date rchilddate) {
		this.rchildid = rchildid;
		this.revid = revid;
		this.context = context;
		this.rchilddate = rchilddate;
	}
	public int getRchildid() {
		return rchildid;
	}
	public void setRchildid(int rchildid) {
		this.rchildid = rchildid;
	}
	public int getRevid() {
		return revid;
	}
	public void setRevid(int revid) {
		this.revid = revid;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Date getRchilddate() {
		return rchilddate;
	}
	public void setRchilddate(Date rchilddate) {
		this.rchilddate = rchilddate;
	}
	//새로올림
}
