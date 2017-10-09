package com.yangjie.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * token保存实体
 * @author YangJie [2017年10月9日 下午5:52:06]
 */
public class TokenBean {
	
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("expires_in")
	private int expiresIn;
	private Date updateTime; // 更新时间
	
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}