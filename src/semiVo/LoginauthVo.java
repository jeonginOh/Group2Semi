package semiVo;

import java.sql.Date;

//   token varchar2(36) NOT NULL,
//   memid number(10)   NOT NULL,
//   per   number(1)    DEFAULT 0,
//   CONSTRAINT PK_loginauth PRIMARY KEY (token, memid)

/**
     * 
     * @param id
     * @param token
     * @param memid
     * @param identifier
     * @param per 권한. 기본값 0, -1 expire
     * @param created
     */
public class LoginauthVo {
    private int id;
    private String token;
    private int memid;
    private String identifier;
    private int per;
    private Date created;

    public LoginauthVo() {
    }
    /**
     * 
     * @param id
     * @param token
     * @param memid
     * @param identifier
     * @param per 권한. 기본값 0, -1 expire
     * @param created
     */
    public LoginauthVo(int id, String token, int memid, String identifier, int per, Date created) {
        this.id=id;
        this.token = token;
        this.memid = memid;
        this.identifier = identifier;
        this.per = per;
        this.created = created;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id=id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getMemid() {
        return this.memid;
    }

    public void setMemid(int memid) {
        this.memid = memid;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getPer() {
        return this.per;
    }

    public void setPer(int per) {
        this.per = per;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    
}
