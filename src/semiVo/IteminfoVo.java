package semiVo;

import java.sql.Date;

public class IteminfoVo {
	private int itemid;
	private String itemname;
	private int catid;
	private int price;
	private String factory;
	private String origin;
	private int stock;
	private Date expire;
	private Date storedate;
	private String image;
	private int avail;
	
	public IteminfoVo() {}

	public IteminfoVo(int itemid, String itemname, int catid, int price, String factory, String origin, int stock,
			Date expire, Date storedate, String image, int avail) {
		super();
		this.itemid = itemid;
		this.itemname = itemname;
		this.catid = catid;
		this.price = price;
		this.factory = factory;
		this.origin = origin;
		this.stock = stock;
		this.expire = expire;
		this.storedate = storedate;
		this.image = image;
		this.avail = avail;
	}

	public int getItemid() {
		return this.itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public String getItemname() {
		return this.itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public int getCatid() {
		return this.catid;
	}

	public void setCatid(int catid) {
		this.catid = catid;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getFactory() {
		return this.factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getExpire() {
		return this.expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public Date getStoredate() {
		return this.storedate;
	}

	public void setStoredate(Date storedate) {
		this.storedate = storedate;
	}

	public String getImage() {
		return this.image;
	}

	//ㅠㅠ
	public void setImage(String image) {
		this.image = image;
	}

	public int getAvail() {
		return this.avail;
	}

	public void setAvail(int avail) {
		this.avail = avail;
	}
	
	
}