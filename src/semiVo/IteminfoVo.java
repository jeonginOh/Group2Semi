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
	
	
}
