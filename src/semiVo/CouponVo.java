package semiVo;

import java.sql.Date;

public class CouponVo {
	private int coupid;
	private Date expire;
	private int memid;
	private String context;
	private int avail;
	public CouponVo() {}
	public CouponVo(int coupid, Date expire, int memid, String context, int avail) {
		this.coupid = coupid;
		this.expire = expire;
		this.memid = memid;
		this.context = context;
		this.avail = avail;
	}
	public int getCoupid() {
		return coupid;
	}
	public void setCoupid(int coupid) {
		this.coupid = coupid;
	}
	public Date getExpire() {
		return expire;
	}
	public void setExpire(Date expire) {
		this.expire = expire;
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
	public int getAvail() {
		return avail;
	}
	public void setAvail(int avail) {
		this.avail = avail;
	}
	
	
	
}
