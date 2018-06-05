package com.biencloud.smarthome.web.clientuser.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;

public class ClientUserVO extends BaseVO{
	private Long id;				//编号

    private String username;		//用户名

    private String password;		//密码

    private String type;			//类型	1Skeeper用户，2楼宇用户，3：1byone用户

    private Integer status;			//状态

    private String mobile;			//手机号
    
    private String sipid;			//sip用户名
    
    private String sipPasswd;			//sip密码

    private String email;			//电子邮件地址

    private Date createTime;		//创建时间
    
    private Date updateTime;		//更新时间

    private String remark;			//备注
    
    private String sessionKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getSipid() {
		return sipid;
	}

	public void setSipid(String sipid) {
		this.sipid = sipid;
	}

	public String getSipPasswd() {
		return sipPasswd;
	}

	public void setSipPasswd(String sipPasswd) {
		this.sipPasswd = sipPasswd;
	}
    
}