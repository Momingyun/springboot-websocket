package com.im.common.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description 时间获取工具类
 * @data 2019/8/13
 * @Author: LiuBin
 * @Modified By:
 */
public class TimeUtil {
		private static final java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		private static Calendar calendar;

		/**
		 * 获取今天日期
		 *
		 * @return yyyy-MM-dd HH:mm:ss
		 * @throws ParseException
		 */
		public static Date today() throws ParseException {
				calendar = Calendar.getInstance();
				calendar.setTime(new Date(calendar.getTime().getTime()));
				Date today = df.parse(df.format(calendar.getTime()));
				return today;
		}

		/**
		 * 获取明天日期
		 *
		 * @return yyyy-MM-dd HH:mm:ss
		 * @throws ParseException
		 */
		public static Date tomorrow() throws ParseException {
				calendar = Calendar.getInstance();
				calendar.setTime(new Date(calendar.getTime().getTime() + 1000 * 60 * 60 * 24));
				Date tomorrow = df.parse(df.format(calendar.getTime()));
				return tomorrow;
		}

		/**
		 * 获取自定义日期
		 * 天数小于等于零时返回当前日期
		 *
		 * @param day 第几天
		 * @return yyyy-MM-dd HH:mm:ss
		 * @throws ParseException
		 */
		public static Date getDate(Integer day) throws ParseException {
				//天数小于等于零时返回当前日期
				if (day <= 0) {
						return today();
				}
				calendar = Calendar.getInstance();
				calendar.setTime(new Date(calendar.getTime().getTime() + 1000 * 60 * 60 * 24 * day));
				Date date = df.parse(df.format(calendar.getTime()));
				return date;
		}

		/**
		 * 获取输入日期星期
		 *
		 * @param date
		 * @return
		 */
		public static final String getWeek(Date date) {
				String weekDay = "";
				String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
				if (w < 0) {
						w = 0;
				}
				weekDay = weekDays[w];
				return weekDay;
		}
}
