package com.vane.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class BaseUtil {
	public static String getSeqStr() {
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if(hashCodeV < 0) {
			hashCodeV = -hashCodeV;
		}
		
		String seq = String.format("%15d", hashCodeV).trim();
		return seq;
	}
	
	public static long getSeqLong() {
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if(hashCodeV < 0) {
			hashCodeV = -hashCodeV;
		}
		
		String seq = String.format("%15d", hashCodeV).trim();
		return Long.valueOf(seq);
	}
	
	public static void main(String[] args) {
		System.out.println(getSeqLong());
	}
}
