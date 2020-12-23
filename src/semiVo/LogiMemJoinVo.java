package semiVo;

import java.sql.Date;

public class LogiMemJoinVo {
    private int memid;
    private String id;
    private String pwd;
    private String salt;
    private String age;
    private String email;
    private String addr;
    private Date regdate; 
    private String phone;
    private int point;
    private int status;
	private int logiid;
	private int buyid;
	private int itemid;
	private String logiinfo;
	private String itemname;
	public LogiMemJoinVo() {}
	public LogiMemJoinVo(int memid, String id, String pwd, String salt, String age, String email, String addr,
			Date regdate, String phone, int point, int status, int logiid, int buyid, int itemid, String logiinfo, String itemname) {
		super();
		this.memid = memid;
		this.id = id;
		this.pwd = pwd;
		this.salt = salt;
		this.age = age;
		this.email = email;
		this.addr = addr;
		this.regdate = regdate;
		this.phone = phone;
		this.point = point;
		this.status = status;
		this.logiid = logiid;
		this.buyid = buyid;
		this.itemid = itemid;
		this.logiinfo = logiinfo;
		this.itemname = itemname;
	}
	public int getMemid() {
		return memid;
	}
	public void setMemid(int memid) {
		this.memid = memid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getLogiid() {
		return logiid;
	}
	public void setLogiid(int logiid) {
		this.logiid = logiid;
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
	public String getLogiinfo() {
		return logiinfo;
	}
	public void setLogiinfo(String logiinfo) {
		this.logiinfo = logiinfo;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
}
