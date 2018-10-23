package com.dew.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	private static Log log = LogFactory.getLog(DateUtils.class);
	private static SimpleDateFormat yyMMdd = new SimpleDateFormat("yyMMdd");
	private static SimpleDateFormat standardFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat HH_MM_SS = new SimpleDateFormat("HH:mm:ss");
	private static GregorianCalendar grc = new GregorianCalendar();
	private static SimpleDateFormat standardFmtRemoteDetails = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	private static SimpleDateFormat yyMMddHH = new SimpleDateFormat("yyMMddHH");
	private static SimpleDateFormat HH = new SimpleDateFormat("HH");
	private static SimpleDateFormat mm = new SimpleDateFormat("mm");
	private static SimpleDateFormat ss = new SimpleDateFormat("ss");
	private static SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyyMM");

	public static Timestamp convertToTimestamp(Date date) {
		if (date != null)
			return new Timestamp(date.getTime());
		return null;
	}

	public static String hhmmss(Date date) {
		return HH_MM_SS.format(date);
	}

	public static String fmthh(Date date) {
		return HH.format(date);
	}

	public static String fmtmm(Date date) {
		return mm.format(date);
	}
	
	public static String fmtss(Date date) {
		return ss.format(date);
	}

	public static String fmtyyyyMMddHHmmss(Date date) {
		return yyyyMMddHHmmss.format(date);
	}

	public static String fmtDateForBetRecods(Date date) {
		return standardFmt.format(date);
	}

	public static String fmtyyyyMMdd(Date date) {
		return yyyyMMdd.format(date);
	}

	public static String fmtyyMMddHH(Date date) {
		return yyMMddHH.format(date);
	}

	public static String fmtyyyy_MM_dd(Date date) {
		return yyyy_MM_dd.format(date);
	}

	public static String fmtyyyyMM(Date date) {
		return yyyyMM.format(date);
	}
	
	public static String fmtyyyy_MM_dd_HH(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(12, 0);
		c.set(13, 0);
		return standardFmt.format(c.getTime());
	}

	public static String fmtyyMMdd(Date date) {
		return yyMMdd.format(date);
	}

	public static String formatDateForStandard(Date date) {
		return standardFmt.format(date);
	}

	public static Timestamp convertTimestamp(Date date) {
		Calendar currDate = Calendar.getInstance();
		currDate.setTime(date);
		return new Timestamp(currDate.getTimeInMillis());
	}

	public static Timestamp getCurrentTimestamp() {
		Calendar currDate = Calendar.getInstance();
		return new Timestamp(currDate.getTimeInMillis());
	}

	public static Timestamp now() {
		return getCurrentTimestamp();
	}
	
	public static Date getToday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(11, 0);
		c.set(12, 0);
		c.set(13, 0);
		return c.getTime();
	}

	public static Date getTomorrow(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(6, 1);
		c.set(11, 0);
		c.set(12, 0);
		c.set(13, 0);
		return c.getTime();
	}

	public static Timestamp getDate(Date date, int fixedHours) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(10, fixedHours);
		log.info((new StringBuilder("修正小时数:")).append(fixedHours).toString());
		String dateStr = yyMMdd.format(c.getTime());
		log.info((new StringBuilder("修正后时间:")).append(c.getTime().toLocaleString()).toString());
		return new Timestamp(c.getTimeInMillis());
	}

	public static String getDateFormat(Date date, String format, int fixedHours) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(10, fixedHours);
		log.info((new StringBuilder("修正小时数:")).append(fixedHours).toString());
		String dateStr = sdf.format(c.getTime());
		log.info((new StringBuilder("修正后时间:")).append(c.getTime().toLocaleString()).toString());
		return dateStr;
	}

	public static String getyyyyMMdd(Date date, int fixedHours) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(10, fixedHours);
		log.info((new StringBuilder("修正小时数:")).append(fixedHours).toString());
		String dateStr = yyyyMMdd.format(c.getTime());
		log.info((new StringBuilder("修正后时间:")).append(c.getTime().toLocaleString()).toString());
		return dateStr;
	}

	public static Date getFirstDayOfMonth(Date date, Integer month, Integer year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		month = Integer.valueOf(month != null && month.intValue() >= 0 && month.intValue() <= 11 ? month.intValue() : calendar.get(2));
		year = Integer.valueOf(year != null && year.intValue() >= 0 ? year.intValue() : calendar.get(1));
		calendar.set(1, year.intValue());
		calendar.set(2, month.intValue());
		calendar.set(5, 1);
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);
		return calendar.getTime();
	}

	public static Date getLongAfter() {
		Date date = new Date(0);
		date.setYear(1000);
		return date;
	}

	public static Date getLongAgo() {
		return new Date(0);
	}

	public static Date getOneHourAgo(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, -1);
		return c.getTime();
	}
	
	public static Date getOneHourAfter(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, 1);
		return c.getTime();
	}
	
	public static Date getHourStart(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	} 
	
	public static Date get15MinutesAgo(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, -15);
		return c.getTime();
	}

	public static Date parseDateRemoteDetails(String dateText) {
		try {
			return standardFmtRemoteDetails.parse(dateText);
		} catch (ParseException e) {
			log.error("exception", e);
			e.printStackTrace();
			return null;
		}
	}

	public static String getOneHourAgoFormat(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, -1);
		return standardFmt.format(c.getTime());
	}

	/**
	 * 前一天开始
	 * 
	 * @param date
	 * @return
	 */
	public static Date getYesterday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(6, -1);
		c.set(11, 0);
		c.set(12, 0);
		c.set(13, 0);
		return c.getTime();
	}

	public static Date parseDateForStandard(String dateText) {
		try {
			return standardFmt.parse(dateText);
		} catch (ParseException e) {
			log.error("exception", e);
			e.printStackTrace();
			return null;
		}
	}

	public static Date parseDateyyyyMMddHHmmss(String dateText) {
		try {
			return yyyyMMddHHmmss.parse(dateText);
		} catch (ParseException e) {
			log.error("exception", e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 时间相加/减 月
	 */
	public static Date getNewMonth(Date date, Integer month) {
		grc.setTime(date);
		grc.add(Calendar.MONTH, month);
		return new Timestamp(grc.getTimeInMillis());
	}

	/**
	 * 
	 * @param day
	 *            时间相加(天)
	 * @return
	 */
	public static String getMontHreduce(Date date, Integer day) {
		grc.setTime(date);
		grc.add(Calendar.DAY_OF_MONTH, day);
		return standardFmt.format(grc.getTime());
	}

	/**
	 * 
	 * @param day
	 *            时间相加(秒 )
	 * @return
	 */
	public static Date getMontSecond(Date date, Integer second) {
		grc.setTime(date);
		grc.add(Calendar.SECOND, second);
		return grc.getTime();
	}

	/**
	 * 
	 * @param day
	 *            时间相加(天)
	 * @return
	 */
	public static Date getMontToDate(Date date, Integer day) {
		grc.setTime(date);
		grc.add(Calendar.DAY_OF_MONTH, day);
		return new Timestamp(grc.getTimeInMillis());
	}

	/**
	 * 
	 * @param year
	 * 
	 * @return
	 */
	public static String getYear(Date date) {
		grc.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(date);
	}

	/**
	 * @return
	 */
	public static String getMonth(Date date) {
		grc.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("M");
		return sdf.format(date);
	}

	/**
	 * @return
	 */
	public static String getDay(Date date) {
		grc.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("d");
		return sdf.format(date);
	}
	
	/**
	 * @return
	 */
	public static Integer getQuarter(Date date){
		Integer quarter = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
        int year = cal.get(Calendar.YEAR);//获取年份
        int month=cal.get(Calendar.MONTH);//获取月份
		if(month < 3){
			quarter = 1;
		}else if(month < 6 && month >= 3){
			quarter = 2;
		}else if(month < 9 && month >= 6){
			quarter = 3;
		}else{
			quarter = 4;
		}
		return quarter;
	}

	// 上周 的一天
	public static Date getLastWeek(Date date, Integer index) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -7);
		c.set(Calendar.DAY_OF_WEEK, index);
		return DateUtils.getMontToDate(c.getTime(), 1);
	}

	// 上周的一天起始时间
	public static Date getLastWeekDayStartTime(Date date, Integer index) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -7);
		c.set(Calendar.DAY_OF_WEEK, index);
		c.set(11, 0);
		c.set(12, 0);
		c.set(13, 0);
		return DateUtils.getMontToDate(c.getTime(), 1);
	}

	// 本周 的一天
	public static Date getThisWeek(Date date, Integer index) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, index);
		return DateUtils.getMontToDate(c.getTime(), 1);
	}

	// 本周 的一天起始时间
	public static Date getThisWeekDayStartTime(Date date, Integer index) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, index);
		c.set(11, 0);
		c.set(12, 0);
		c.set(13, 0);
		return DateUtils.getMontToDate(c.getTime(), 1);
	}

	// 下周的一天
	public static Date getNextWeek(Date date, Integer index) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 7);
		c.set(Calendar.DAY_OF_WEEK, index);
		return DateUtils.getMontToDate(c.getTime(), 1);
	}

	public static Integer getWeekNumber(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
		case 1:
			return 7;
		case 2:
			return 1;
		case 3:
			return 2;
		case 4:
			return 3;
		case 5:
			return 4;
		case 6:
			return 5;
		case 7:
			return 6;
		}
		return null;
	}

	/**
	 * 获取给定时间所在月的第一天的日期
	 */
	public static Date getMonthStartdDate(Date date) {
		Date firstDateOfMonth;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// 得到当天是这月的第几天
		int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
		// 减去dayOfMonth,得到第一天的日期，因为Calendar用０代表每月的第一天，所以要减一
		c.add(Calendar.DAY_OF_MONTH, -(dayOfMonth - 1));
		firstDateOfMonth = c.getTime();
		return firstDateOfMonth;
	}
	
	/**
	 * 获取给定时间所在下个月的第一天的日期
	 */
	public static Date getNextMonthStartdDate(Date date) {
		Date nextMonthFirstDay;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DATE, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		nextMonthFirstDay = c.getTime();
		return nextMonthFirstDay;
	}

	/**
	 * 获取给定时间所在月的最后一天的日期
	 */
	public static Date getMonthEndDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 获取给定时间的差异 返回年数
	 */
	public static int getDistanceyear(Date start, Date end) {
		String startdate = fmtDateForBetRecods(start);
		String enddate = fmtDateForBetRecods(end);
		int year = 0;
		try {
			Long beginL = standardFmt.parse(startdate).getTime();
			Long endL = standardFmt.parse(enddate).getTime();
			year = ((int) (endL - beginL)) / 1000 / 60 / 60 / 24 / 356;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return year;
	}

	/**
	 * 获取给定时间的差异 返回月数
	 */
	public static int getDistancemonth(Date start, Date end) {
		String startdate = fmtDateForBetRecods(start);
		String enddate = fmtDateForBetRecods(end);
		int month = 0;
		try {
			Long beginL = standardFmt.parse(startdate).getTime();
			Long endL = standardFmt.parse(enddate).getTime();
			month = ((int) (endL - beginL)) / 1000 / 60 / 60 / 24 / 30;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return month;
	}

	/**
	 * 获取给定时间的差异 返回天数
	 */
	public static int getDistancedate(Date start, Date end) {
		String startdate = fmtDateForBetRecods(start);
		String enddate = fmtDateForBetRecods(end);
		int date = 0;
		try {
			Long beginL = standardFmt.parse(startdate).getTime();
			Long endL = standardFmt.parse(enddate).getTime();
			date = ((int) (endL - beginL)) / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取给定时间的差异 返回小时
	 */
	public static int getDistancehour(Date start, Date end) {
		String startdate = fmtDateForBetRecods(start);
		String enddate = fmtDateForBetRecods(end);
		int hour = 0;
		try {
			Long beginL = standardFmt.parse(startdate).getTime();
			Long endL = standardFmt.parse(enddate).getTime();
			hour = ((int) (endL - beginL)) / (1000 * 60 * 60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return hour;

	}

	/**
	 * 获取给定时间的差异 返回分钟
	 */
	public static int getDistanceMinute(Date start, Date end) {
		String startdate = fmtDateForBetRecods(start);
		String enddate = fmtDateForBetRecods(end);
		int Minute = 0;
		try {
			Long beginL = standardFmt.parse(startdate).getTime();
			Long endL = standardFmt.parse(enddate).getTime();
			Minute = ((int) (endL - beginL)) / (1000 * 60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Minute;
	}

	/**
	 * 获取给定时间的差异 返回秒数
	 */
	public static int getDistanceSecond(Date start, Date end) {
		String startdate = fmtDateForBetRecods(start);
		String enddate = fmtDateForBetRecods(end);
		int Second = 0;
		try {
			Long beginL = standardFmt.parse(startdate).getTime();
			Long endL = standardFmt.parse(enddate).getTime();
			Second = ((int) (endL - beginL)) / (1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Second;
	}

	/**
	 * 获取给定时间的差异 返回毫秒数
	 */
	public static int getMillisecond(Date start, Date end) {
		String startdate = fmtDateForBetRecods(start);
		String enddate = fmtDateForBetRecods(end);
		int Millisecond = 0;
		try {
			Long beginL = standardFmt.parse(startdate).getTime();
			Long endL = standardFmt.parse(enddate).getTime();
			Millisecond = (int) (endL - beginL);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Millisecond;
	}

	
	/*
	 * 
	 * 上个月第一天,最后一天时间
	 */

	public static String[] getLastMonthDay(Date date) {
		String[] lastMonth = new String[2];
		Calendar calFrist = Calendar.getInstance();
		calFrist.setTime(date);
		calFrist.add(Calendar.MONTH, -1);
		calFrist.set(Calendar.DAY_OF_MONTH, 1);
		calFrist.set(11, 0);
		calFrist.set(12, 0);
		calFrist.set(13, 0);
		lastMonth[0] = formatDateForStandard(calFrist.getTime());
		Calendar calLast = Calendar.getInstance();
		calLast.setTime(date);
		calLast.add(Calendar.MONTH, -1);
		calLast.set(Calendar.DAY_OF_MONTH, calLast.getActualMaximum(Calendar.DAY_OF_MONTH));
		calLast.set(11, 23);
		calLast.set(12, 59);
		calLast.set(13, 59);
		lastMonth[1] = formatDateForStandard(calLast.getTime());
		return lastMonth;
	}

	/*
	 * 
	 * 上周第一天,最后一天时间
	 */

	public static String[] getLastWeekDay(Date date) {
		String[] lastWeek = new String[2];
		int weeks = -1;
		int mondayPlus = getMondayPlus(date);
		Calendar lastWeekMon = Calendar.getInstance();
		lastWeekMon.setTime(date);
		lastWeekMon.add(Calendar.DATE, mondayPlus + 7 * weeks);
		lastWeekMon.set(11, 0);
		lastWeekMon.set(12, 0);
		lastWeekMon.set(13, 0);
		Date monday = lastWeekMon.getTime();
		lastWeek[0] = formatDateForStandard(monday);
		Calendar lastWeekSun = Calendar.getInstance();
		lastWeekSun.setTime(date);
		lastWeekSun.add(Calendar.DATE, mondayPlus + 7 * weeks + 6);
		lastWeekSun.set(11, 23);
		lastWeekSun.set(12, 59);
		lastWeekSun.set(13, 59);
		lastWeek[1] = formatDateForStandard(lastWeekSun.getTime());
		return lastWeek;
	}

	/*
	 * 
	 * 上周第一天0:00,本周第一天0:00
	 */

	public static String[] getLastWeekLevelDay(Date date) {
		String[] lastWeek = new String[2];
		int weeks = -1;
		int mondayPlus = getMondayPlus(date);
		Calendar lastWeekMon = Calendar.getInstance();
		lastWeekMon.setTime(date);
		lastWeekMon.add(Calendar.DATE, mondayPlus + 7 * weeks);
		lastWeekMon.set(11, 0);
		lastWeekMon.set(12, 0);
		lastWeekMon.set(13, 0);
		Date monday = lastWeekMon.getTime();
		lastWeek[0] = formatDateForStandard(monday);
		Calendar lastWeekSun = Calendar.getInstance();
		lastWeekSun.setTime(date);
		lastWeekSun.add(Calendar.DATE, mondayPlus);
		lastWeekSun.set(11, 0);
		lastWeekSun.set(12, 0);
		lastWeekSun.set(13, 0);
		lastWeek[1] = formatDateForStandard(lastWeekSun.getTime());
		return lastWeek;
	}
	
	
	// 获得当前日期与本周一相差的天数
	private static int getMondayPlus(Date date) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		// 获得今天是一周的第几天，星期日是第一天，星期一是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}
	/**
	 * 本周第一天
	 * @param date
	 * @return
	 */
	public static String getThisWeekStartTime(Date date) {
		int mondayPlus = getMondayPlus(date);
		Calendar thisWeekSun = Calendar.getInstance();
		thisWeekSun.setTime(date);
		thisWeekSun.add(Calendar.DATE, mondayPlus);
		thisWeekSun.set(11, 0);
		thisWeekSun.set(12, 0);
		thisWeekSun.set(13, 0);
		return formatDateForStandard(thisWeekSun.getTime());
	}
	
	/**
	 * 获得现在半小时的开始与结束时间
	 * @param date
	 * @return
	 */
	public static Map<String,Date> getNowHalfAnHourPeriod(Date date){
		Date startTime;
		Date endTime;
		Map<String,Date> map = new HashMap<String,Date>();
		Integer nowMinute = Integer.parseInt(fmtmm(date));
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if(nowMinute < 30){
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			startTime = c.getTime();
			
			c.set(Calendar.MINUTE, 30);
			c.set(Calendar.SECOND, 0);
			endTime = c.getTime();
		}else{
			c.set(Calendar.MINUTE, 30);
			c.set(Calendar.SECOND, 0);
			startTime = c.getTime();
			
			c.add(Calendar.HOUR_OF_DAY, 1);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			endTime = c.getTime();
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return map;
	}
	
	/**
	 * 获得前半小时的开始与结束时间
	 * @param date
	 * @return
	 */
	public static Map<String,Date> getPreviousHalfAnHourPeriod(Date date){
		Date startTime;
		Date endTime;
		Map<String,Date> map = new HashMap<String,Date>();
		Integer nowMinute = Integer.parseInt(fmtmm(date));
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if(nowMinute < 30){
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			endTime = c.getTime();
			
			c.add(Calendar.HOUR_OF_DAY, -1);
			c.set(Calendar.MINUTE, 30);
			c.set(Calendar.SECOND, 0);
			startTime = c.getTime();
		}else{
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			startTime = c.getTime();
			
			c.set(Calendar.MINUTE, 30);
			c.set(Calendar.SECOND, 0);
			endTime = c.getTime();
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return map;
	}
	
	/**
	 * 本月第一天0点
	 * 
	 * @param now
	 * @return
	 */
	public static Date getMonthFirstTime(Date now) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		return cal.getTime();
	}
	
	/**
	 * 下个月第一天0点
	 * 
	 * @param now
	 * @return
	 */
	public static Date getNextMonthFirstTime(Date now){
		Date thisMonthFirstTime = getMonthFirstTime(now);
		Calendar cd = Calendar.getInstance();
		cd.setTime(thisMonthFirstTime);
		cd.add(Calendar.MONTH, 1);
		return cd.getTime();
	}
	
	public static void main(String[] args) {
		long time = 1002935039364L;
		Date date =  parseDateForStandard("2017-06-05 16:00:00");
		
//		System.out.println(getOneHourAfter(getHourStart(date)));
		System.out.println(getNextMonthStartdDate(getToday(date)));
		
		//System.out.println(formatDateForStandard(date));
		//System.out.println(getThisWeekDayStartTime(date ,1));
		//System.out.println(getMontToDate(getThisWeekDayStartTime(date ,1) ,7));
	}
}
