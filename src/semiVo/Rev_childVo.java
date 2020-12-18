package semiVo;

import java.sql.Date;

public class Rev_childVo {
	private int rchildid;
	private int revid;
	private String context_child;
	private Date rchilddate;
	public Rev_childVo() {}
	public Rev_childVo(int rchildid, int revid, String context_child, Date rchilddate) {
		this.rchildid = rchildid;
		this.revid = revid;
		this.context_child = context_child;
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
	public String getcontext_child() {
		return context_child;
	}
	public void setcontext_child(String context_child) {
		this.context_child = context_child;
	}
	public Date getRchilddate() {
		return rchilddate;
	}
	public void setRchilddate(Date rchilddate) {
		this.rchilddate = rchilddate;
	}
	//새로올림
}
