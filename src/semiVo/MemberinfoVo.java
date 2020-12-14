package semiVo;

import java.sql.Date;

/**
 * 
 */
public class MemberinfoVo {
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

    public MemberinfoVo() {
    }
    /**
     * 
     * @param memid int
     * @param id String
     * @param pwd String
     * @param salt String
     * @param age String
     * @param email String
     * @param addr String
     * @param regdate Date
     * @param phone String
     * @param point int
     * @param status int
     */
    public MemberinfoVo(int memid, String id, String pwd, String salt, String age, String email, String addr, Date regdate, String phone, int point, int status) {
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
        this.status=status;
    }
    public void setStatus(int status) {
        this.status=status;
    }
    public int getStatus() {
        return this.status;
    }
    public void setSalt(String salt) {
        this.salt=salt;
    }
    public String getSalt() {return this.salt;}
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

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
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
