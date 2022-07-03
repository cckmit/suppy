package com.zjjzfy.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author hsmz
 */
public class DateUtil {

	/**
	 *
	 * @param day
	 * @param delaySeconds 单位秒
	 * @return
	 */
	public static Date getTimeDelay(Date day, int delaySeconds){

		Calendar c = Calendar.getInstance();
		c.setTime(day);
		int seconds = c.get(Calendar.SECOND);
		c.set(Calendar.SECOND,seconds + delaySeconds);
		return c.getTime();
	}

	public static Date getNextDay(Date day)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		int days = c.get(Calendar.DATE);
		c.set(Calendar.DATE, days+1);
		return c.getTime();
		
	}
	
	public static Date getLastDay(Date day)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		c.add(Calendar.DATE,-1);
		return c.getTime();
	}

	/**
	 * 获得当前时间
	 * @return
     */
	public static Date getCurrentDate()
	{
		return Calendar.getInstance().getTime();
	}

	/**
	 * 获得当前时间在当月天
	 * @return
	 */
	public static int getCurrentDateDay(Date day)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得当前时间年
	 * @param date
	 * @return
	 */
	public static int getYear(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获得当前时间月
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH)+1;
	}

	/**
	 * 获取该周的第一天
	 * @param day
	 * @return
	 */
	public static Date getFirstDayofWeek(Date day){

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		int weekday = calendar.get(Calendar.DAY_OF_WEEK); // 1~7
		int days = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE,days - weekday + 1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);


		return calendar.getTime();
	}

	/**
	 * 获取该月的第一天
	 * @param day
	 * @return
	 */
	public static Date getFirstDayofMonth(Date day){

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
    	calendar.set(Calendar.DATE,1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		return calendar.getTime();
	}

	/**
	 * 获取该月的最后一天
	 * @param day
	 * @return
	 */
	public static Date getLastDayofMonth(Date day){

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取该年的第一天
	 * @param day
	 * @return
	 */
	public static Date getFirstDayofYear(Date day){

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		int year = calendar.get(Calendar.YEAR);
		calendar.clear();
		calendar.set(Calendar.YEAR,year);

		return calendar.getTime();
	}

	public static String getString(Date day)
	{
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(day);
	}

	public static Date stringToDate(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String dateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static Date stringToDateNoTime(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取当天时间的零点零分零秒
	 * @param date
	 * @return
	 */
	public static Date getCurrentZeroDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}


	/**
	 * 获取当天时间的23点59分59秒
	 * @param date
	 * @return
	 */
	public static Date getCurrentLastDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	public static void main(String args[]){
		System.out.println(getLastDayofMonth(new Date()));
	}
}
