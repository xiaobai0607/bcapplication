package com.bootcamp.utils.tenpay.JSAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.http.ProtocolException;



/**
* 通用工具类
* @author rory.wu
* @version .
* @since 年月日
*/
public class OpenIdUtil {

	// 第三方用户唯一凭证
	 public static String appid = "";
	 // 第三方用户唯一凭证密钥
	 public static String appsecret = "";
	 //商户ID
	 public static String mch_id="";
	 //获取openId
	 //public static String oauth_url = "https://api.weixin.qq.com/sns/oauth/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	

	
public static JSONObject httpsRequestToJsonObject(String requestUrl, String requestMethod, String outputStr) {
	JSONObject jsonObject = null;
	try {
		StringBuffer buffer = httpsRequest(requestUrl, requestMethod, outputStr);
		
		jsonObject = JSONObject.fromObject(buffer.toString());
	} catch (ConnectException ce) {
		ce.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return jsonObject;
}

	private static StringBuffer httpsRequest(String requestUrl, String requestMethod, String output)
	throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, MalformedURLException,
	IOException, ProtocolException, UnsupportedEncodingException {
		URL url = new URL(requestUrl);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestMethod(requestMethod);
		if (null != output) {
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(output.getBytes("UTF-8"));
			outputStream.close();
		}
		// 从输入流读取返回内容
		InputStream inputStream = connection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		StringBuffer buffer = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		inputStream = null;
		connection.disconnect();
		return buffer;
	} 
	

	/**
	* 获取用户的openId，并放入session
	* @param code 微信返回的code
	*/
	private void setOpenId(String code,HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		session.setAttribute("code", code);
//		String oauth_url =this.oauth_url.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", String.valueOf(session.getAttribute("code")));
//		
//		JSONObject jsonObject = OpenIdUtil.httpsRequestToJsonObject(oauth_url, "POST", null);
//		Object errorCode = jsonObject.get("errcode");
//		if(errorCode != null) {
//		}else{
//		String openId = jsonObject.getString("openid");
//		session.setAttribute("openId", openId);
//		}
	}
}
