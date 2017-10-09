package com.yangjie.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yangjie.dao.WeixinTokenDao;
import com.yangjie.entity.WeixinTokenBean;
import com.yangjie.util.HttpUtil;
import com.yangjie.util.JsonUtil;

/**
 * 微信通用access_token
 * @author YangJie [2017年10月9日 下午5:52:18]
 */
@Service
public class WeixinTokenService {
	
	private Logger logger = LoggerFactory.getLogger(WeixinTokenService.class);
	
	/** 获取access_token地址 */
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
	
	@Value("${wechat.appid}")
	private String appid;
	@Value("${wechat.secret}")
	private String secret;
	
	@Autowired
	private WeixinTokenDao tokenDao;
	
	
	/**
	 * 获取accessToken
	 * @author YangJie [2016年5月4日 下午3:37:50]
	 * @return
	 */
	public String getAccessToken() {
		WeixinTokenBean tokenBean = tokenDao.getToken();
		// 判断当前token是否在有效期内
		if (tokenBean!=null && tokenBean.getAccessToken()!=null) { 
			if((System.currentTimeMillis()-tokenBean.getUpdateTime().getTime())/1000 < (tokenBean.getExpiresIn()-300)){
				logger.debug("返回有效期内的access_token: {}", tokenBean.getAccessToken());
				return tokenBean.getAccessToken();
			}
		}
		// 如果没有token信息或者已经过期, 重新从api获取
		StringBuilder urlBuilder = new StringBuilder(ACCESS_TOKEN_URL);
		urlBuilder.append("?appid=").append(appid);
		urlBuilder.append("&secret=").append(secret);
		urlBuilder.append("&grant_type=").append("client_credential");
		logger.debug("获取access_token请求地址: {}", urlBuilder);
		String result = HttpUtil.get(urlBuilder.toString());
		logger.debug("获取access_token返回数据: {}", result);
		tokenBean = JsonUtil.toObject(result, WeixinTokenBean.class);
		if (tokenBean!=null && tokenBean.getAccessToken()!=null) {
			tokenBean.setUpdateTime(new Date());
			tokenDao.setToken(tokenBean); // 缓存获取的token信息
			logger.debug("返回新获取的access_token: {}", tokenBean.getAccessToken());
			return tokenBean.getAccessToken();
		}
		logger.error("获取access_token信息失败!, 返回null");
		return null;
	}
	
}
