package com.lcz.shop.util;

import java.util.Random;

public class GetRandom {

    public static int random() {
        return (int) ((Math.random()*9+1)*1000);
    }
    public static Long getNumber() {
        return Long.parseLong(String.valueOf(System.currentTimeMillis()) + String.valueOf(random()));
    }
    
    public static String getCode(Integer length) {
    	StringBuffer val=new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			val.append(String.valueOf(random.nextInt(10)));
		}
		return val.toString();
	}

}
