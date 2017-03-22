package com.bootcamp.utils;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	/**
	 * 获取一天00:00:00
	 * @param date
	 * @return
	 */
	public static Date getDayBegin(Date date){
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);
		//将小时至0  
		calendar.set(Calendar.HOUR_OF_DAY, 0);  
		 //将分钟至0  
		calendar.set(Calendar.MINUTE, 0);  
		 //将秒至0  
		calendar.set(Calendar.SECOND,0);  
		return calendar.getTime(); 
	}
	
	/**
	 * 获取一天23:59:59
	 * @param date
	 * @return
	 */
	public static Date getDayEnd(Date date){
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date); 
		calendar.set(Calendar.HOUR_OF_DAY, 23);   
		calendar.set(Calendar.MINUTE, 59);    
		calendar.set(Calendar.SECOND,59);  
		return calendar.getTime(); 
	}
}
