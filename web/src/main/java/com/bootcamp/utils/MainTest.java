/**
 * 
 */
package com.bootcamp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author caoyuan
 * @version 2016年4月10日
 */
public class MainTest {  
	  
    public static void main(String[] args) throws Exception {  
//        String filepath="C:/tmp/";  
//  
//        //RSAEncrypt.genKeyPair(filepath);  
//          
//          
//        System.out.println("--------------公钥加密私钥解密过程-------------------");  
//        String plainText="ihep_公钥加密私钥解密";  
//        //公钥加密过程  
//        byte[] cipherData=RSAEncrypt.encrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(filepath)),plainText.getBytes());  
//        String cipher="xAUIo9D2JgIXAGL/fBqKrB+QI4EsOOJY2RymDbNZNsolnlLTYiCUPZAglcS+2dlCyjhWLmZwbaddxdMqec4MjNO5zNjLXN7LyAP3gCQpLtJ6Q1cbcBFb95OaBLsfeyHBkce8u8ZB57iKvoCRkWW4DoMbff5mHnW9nLu5nFhyQnI=";  
//        //私钥解密过程  
//        byte[] res=RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(filepath)), Base64.decode(cipher));  
//        String restr=new String(res);  
//        System.out.println("原文："+plainText);  
//        System.out.println("加密："+cipher);  
//        System.out.println("解密："+restr);  
//        System.out.println();  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    System.out.println(sdf.format(TimeUtils.getDayEnd(new Date())));
          
    }  
}  
