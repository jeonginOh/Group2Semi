package semiVvo;

public class BasketVo {
	private int basid;
	private int memid;
	private int itemid;
	private int count;
	public BasketVo() {}
	public BasketVo(int basid, int memid, int itemid, int count) {
		super();
		this.basid = basid;
		this.memid = memid;
		this.itemid = itemid;
		this.count = count;
	}
	public int getBasid() {
		return basid;
	}
	public void setBasid(int basid) {
		this.basid = basid;
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
}
