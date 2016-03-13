package com.lyloou.android.util;

import java.text.DecimalFormat;

public class DataUtil {
	public static int getBit(int data, int shift){
		return data >> shift & 0x01;
	}
	public static String getBitStr(int data){
		int shift = 8;
		String str = new String();
		for(int i=0;i<shift;i++){
			str = (data >> i & 0x01) + str;
		}
		return str;
	}
	
	public static int booleanArrayToInt(boolean[] data){
		int result = 0;
		for(int i=0; i<data.length; i++){
			result = (int) (result + Math.pow(2, i) * (data[i]?1:0));
		}
		return result;
	}
	
	public static boolean[] intToBooleanArray(int data, int len){
		boolean[] result = new boolean[len];
		for(int i=0;i<len;i++){
			result[i] = (data >> i & 0x01)==1;
		}
		return result;
	}
	
	public static String getFormatNumWithPattern(Object object, String pattern){
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(object);
	}
}
                                                  