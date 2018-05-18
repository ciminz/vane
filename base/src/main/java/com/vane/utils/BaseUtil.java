package com.vane.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseUtil {
	public static String getSeqStr() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
		int i=(int)(Math.random()*900)+100;
		return sdf.format(date) + i;
	}
	
	public static long getSeqLong() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
		int i=(int)(Math.random()*900)+100;
		return Long.valueOf(sdf.format(date) + i);
	}
}
