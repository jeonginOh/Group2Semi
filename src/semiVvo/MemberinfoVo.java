package semiVvo;

import java.sql.Date;

public class MemberinfoVo {
    private int memid;
    private String id;
    private String pwd;
    private int age;
    private String email;
    private String addr;
    private Date regdate; 
    private String phone;
    private int point;

    public MemberinfoVo() {
    }

    public MemberinfoVo(int memid, String id, String pwd, int age, String email, String addr, Date regdate, String phone, int point) {
        this.memid = memid;
        this.id = id;
        this.pwd = pwd;
        this.age = age;
        this.email = email;
        this.addr = addr;
        this.regdate = regdate;
        this.phone = phone;
        this.point = point;
    }

    public int getMemid() {
        return this.memid;
    }

    public void setMemid(int memid) {
        this.memid = memid;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Date getRegdate() {
        return this.regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPoint() {
        return this.point;
    }

    public void setPoint(int point) {
        this.point = point;
    }


}
