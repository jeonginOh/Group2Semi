package semiVvo;

public class LogisticVo {
	private int logiid;
	private int memid;
	private int buyid;
	private int itemid;
	private String addr;
	private String logiinfo;
	
	public LogisticVo() {}

	public LogisticVo(int logiid, int memid, int buyid, int itemid, String addr, String logiinfo) {
		super();
		this.logiid = logiid;
		this.memid = memid;
		this.buyid = buyid;
		this.itemid = itemid;
		this.addr = addr;
		this.logiinfo = logiinfo;
	}

	public int getLogiid() {
		return logiid;
	}

	public void setLogiid(int logiid) {
		this.logiid = logiid;
	}

	public int getMemid() {
		return memid;
	}

	public void setMemid(int memid) {
		this.memid = memid;
	}

	public int getBuyid() {
		return buyid;
	}

	public void setBuyid(int buyid) {
		this.buyid = buyid;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getLogiinfo() {
		return logiinfo;
	}

	public void setLogiinfo(String logiinfo) {
		this.logiinfo = logiinfo;
	}
}
