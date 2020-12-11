package semiVo;

import java.sql.Date;

public class DiscountVo {
	private int disid;
	private int dis_per;
	private int dis_price;
	private int itemid;
	private Date expire;
	
	public DiscountVo() {}

	public DiscountVo(int disid, int dis_per, int dis_price, int itemid, Date expire) {
		super();
		this.disid = disid;
		this.dis_per = dis_per;
		this.dis_price = dis_price;
		this.itemid = itemid;
		this.expire = expire;
	}

	public int getDisid() {
		return disid;
	}

	public void setDisid(int disid) {
		this.disid = disid;
	}

	public int getDis_per() {
		return dis_per;
	}

	public void setDis_per(int dis_per) {
		this.dis_per = dis_per;
	}

	public int getDis_price() {
		return dis_price;
	}

	public void setDis_price(int dis_price) {
		this.dis_price = dis_price;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}
}
