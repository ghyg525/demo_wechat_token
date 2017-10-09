package com.yangjie.dao;

import org.springframework.stereotype.Repository;

import com.yangjie.entity.WeixinTokenBean;

/**
 * 单应用获取token可临时将token存储在内存
 * 如果多应用同时获取token会出现抢占失效问题
 * 可存储于共享缓存或数据库中
 * @author YangJie [2017年10月9日 下午5:50:09]
 */
@Repository
public class WeixinTokenDao {

	/** 缓存Token信息 */
	private WeixinTokenBean tokenBean;

	
	/**
	 * 获取Token信息
	 * @param appid
	 * @return
	 */
	public WeixinTokenBean getToken(){
		return tokenBean;
	}
	
	/**
	 * 缓存新获得的token
	 * @param tokenBean
	 * @return
	 */
	public void setToken(WeixinTokenBean tokenBean){
		this.tokenBean = tokenBean;
	}
	
}
