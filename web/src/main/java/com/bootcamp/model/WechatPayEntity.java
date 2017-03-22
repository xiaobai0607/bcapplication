package com.bootcamp.model;

public class WechatPayEntity {
	private String appId;
	private String timestamp;
	private String nonceStr;
	private String packageStr;
	private String signType;
	private String paySign;
	private String sign;
	
	
	public WechatPayEntity() {
		
	}


	public WechatPayEntity(String timestamp, String nonceStr, String packageStr,
			String signType, String paySign) {
	
		this.timestamp = timestamp;
		this.nonceStr = nonceStr;
		this.packageStr = packageStr;
		this.signType = signType;
		this.paySign = paySign;
	}


	public String getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


	public String getNonceStr() {
		return nonceStr;
	}


	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}


	public String getPackageStr() {
		return packageStr;
	}


	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
	}


	public String getSignType() {
		return signType;
	}


	public void setSignType(String signType) {
		this.signType = signType;
	}


	public String getPaySign() {
		return paySign;
	}


	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}


	public String getAppId() {
		return appId;
	}


	public void setAppId(String appId) {
		this.appId = appId;
	}


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
}
