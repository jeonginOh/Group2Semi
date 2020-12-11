package semiVo;

import java.sql.Date;

public class BuylistVo {
	private int buyid;
	private int memid;
	private int itemid;
	private int count;
	private int status;
	private Date buydate;
	private int coupid;
	public BuylistVo() {}
	public BuylistVo(int buyid, int memid, int itemid, int count, int status, Date buydate, int coupid) {
		super();
		this.buyid = buyid;
		this.memid = memid;
		this.itemid = itemid;
		this.count = count;
		this.status = status;
		this.buydate = buydate;
		this.coupid = coupid;
	}
	public int getBuyid() {
		return buyid;
	}
	public void setBuyid(int buyid) {
		this.buyid = buyid;
	}
	public int getMemid() {
		return memid;
	}
	public void setMemid(int memid) {
		this.memid = memid;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getBuydate() {
		return buydate;
	}
	public void setBuydate(Date buydate) {
		this.buydate = buydate;
	}
	public int getCoupid() {
		return coupid;
	}
	public void setCoupid(int coupid) {
		this.coupid = coupid;
	}
}
