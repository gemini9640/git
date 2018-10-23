package com.dew.util;

import java.io.BufferedReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtils extends org.apache.commons.lang.StringUtils {
	private static Log log = LogFactory.getLog(StringUtils.class);
	public static final Pattern P_URL = Pattern.compile("(\\w+)://([^/:]+)(:\\d*)?([^#\\s]*)");

	public static String formatNumberToDigits(Integer integer, Integer digits) {
		Integer length = Integer.valueOf(integer.toString().length());
		if (length.intValue() > digits.intValue())
			return integer.toString().substring(length.intValue() - digits.intValue());
		StringBuffer buffer = new StringBuffer(integer.toString());
		buffer.reverse();
		for (int i = 0; i < digits.intValue() - length.intValue(); i++)
			buffer.append("0");
		return buffer.reverse().toString();
	}

	public static String formatDouble(Double value) {
		String valueStr = value.toString();
		Double newValueStr = Double.parseDouble(valueStr.substring(valueStr.indexOf(".") + 1, valueStr.length()));
		if (newValueStr.doubleValue() > 0)
			return valueStr;
		return value.intValue() + "";
	}

	public static String formatOneRow(String content) {
		String resultStr = "";
		try {
			StringReader sr = new StringReader(content);
			BufferedReader br = new BufferedReader(sr);
			while (br.ready()) {
				String str = br.readLine();
				if (str != null)
					resultStr = resultStr + str;
				else
					break;
			}
			sr.close();
			br.close();
		} catch (Exception e) {
			log.error("exception", e);
			e.printStackTrace();
		}
		return resultStr;
	}

	public static String getRandomCharacter(int length) {
		StringBuffer buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++)
			sb.append(buffer.charAt(r.nextInt(range)));
		return sb.toString();
	}

	public static String getRandomString(int length) {
		StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++)
			sb.append(buffer.charAt(r.nextInt(range)));
		return sb.toString();
	}

	public static String transform(String content) {
		content = content.replaceAll(" ", "&nbsp;");
		content = content.replaceAll("\n", "<br/>");
		return content;
	}

	public static String replaceAll(String str) {
		if(StringUtils.isEmpty(str)){
			return str;
		}
		return StringUtils.trim(str).replaceAll("[\u0000-\u0010\u000B\u000E-\u001F\uD800-\uDFFF\uFFFE\uFFFF]", "");
	}

	public static String getRandomNumber(int length) {
		StringBuffer buffer = new StringBuffer("0123456789");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++)
			sb.append(buffer.charAt(r.nextInt(range)));
		return sb.toString();
	}

	public static String nullToString(String str){
		return str==null?"":str;
	}
	
	public static String objToString(Object obj){
		return obj==null?"":obj.toString();
	}
	
	public static String getDomain(String curl){
		URL url = null;
		String domain = "";
		if(isEmpty(curl)){
			return domain;
		}
		try {
			url = new URL(curl);
			domain = url.getHost();
			return domain;
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		}finally{
			url=null;
		}
		return domain;
	}
}
