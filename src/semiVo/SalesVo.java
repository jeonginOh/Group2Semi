package semiVo;

public class SalesVo {
	private String itemname;
	private String dat;
	private int price;
	public SalesVo() {}
	public SalesVo(String dat, int price) {
		super();
		this.dat = dat;
		this.price = price;
	}
	public SalesVo(String itemname, String dat, int price) {
		super();
		this.itemname = itemname;
		this.dat = dat;
		this.price = price;
	}
	public String getDat() {
		return dat;
	}
	public void setDat(String dat) {
		this.dat = dat;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
}
