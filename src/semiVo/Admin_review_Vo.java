package semiVo;

public class Admin_review_Vo {
	private String itemname;
	private int cnt;
	private int star;
	public Admin_review_Vo(String itemname, int cnt,int star) {
		
		this.itemname = itemname;
		this.cnt = cnt;
		this.star=star;
	}
	public Admin_review_Vo() {}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getStar() {
		return star;
	}public void setStar(int star) {
		this.star=star;
	}
	
}
