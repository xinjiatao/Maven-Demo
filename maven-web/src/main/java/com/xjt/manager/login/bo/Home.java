package com.xjt.manager.login.bo;

import java.io.Serializable;
import java.util.Date;

import com.xjt.core.persistence.QueryFilter;

import net.sf.json.JSONObject;

/**
 * <p>项目名称：Demo</p>
 * <p>模块名称：xxx</p>
 * <p>功能描述：xxx</p>
 * <p>类名称：com.xjt.entity.UUser</p>
 * @author 辛加涛
 * @create 2017年4月10日 下午3:47:35
 */
public class Home extends QueryFilter implements Serializable{

	private static final long serialVersionUID = 1L;
	//0:禁止登录
	public static final Long _0 = new Long(0);
	//1:有效
	public static final Long _1 = new Long(1);
	private Long id;
	/**昵称*/
    private String nickname;
    /**邮箱 | 登录帐号*/
    private String email;
    /**密码*/
    private transient String pswd;
    /**创建时间*/
    private Date createTime;
    /**最后登录时间*/
    private Date lastLoginTime;
    /**1:有效，0:禁止登录*/
    private Long status;
    
    
    
    public Home() {}
    public Home(Home user) {
		this.id = user.getId();
		this.nickname = user.getNickname();
		this.email = user.getEmail();
		this.pswd = user.getPswd();
		this.createTime = user.getCreateTime();
		this.lastLoginTime = user.getLastLoginTime();
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public String toString(){
    	return JSONObject.fromObject(this).toString();
    }
	
}
