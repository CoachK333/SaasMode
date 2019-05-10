package com.boot.data.controller;

public class BigNumber {
	private final static int threshold = 18;

	public static void main(String[] args) {

		long l1 = 444L;
		long l2 = 5555L;
		long l3 = 9223372036854775807L;
		long l4 = 1L; //5555555555555555555
		long l5 = 555555555555555555L; //5555555555555555555
										//1111111111111111110
									   //11111111111111111110
		System.out.println(String.valueOf(l5).length());
//		l3 = l4;
		String value = add(l1,l2);
		System.out.println(value);
		long s = System.currentTimeMillis();
		value = add(l3,l4);
		long e = System.currentTimeMillis();
		System.out.println("consuming times :"+(e-s));
		System.out.println("string value = "+value+", and length = "+String.valueOf(value).length());
		System.out.println("string value = "+Long.MAX_VALUE+", and length = "+String.valueOf(value).length());
		System.out.println("long value = "+(l3+l4));
		System.out.println(Long.MAX_VALUE);
	}

	public static String summation(String s1 , String s2){
		int len1 = s1.length();
		int len2 = s2.length();
		int maxlen = len1>len2?len1:len2;
		int minlen = len1<len2?len1:len2;

		StringBuffer pre0 = new StringBuffer();
		for(int i=0; i<maxlen-minlen; i++){
			pre0.append(0);
		}

		if(len1 < len2){
			s1 = pre0.toString()+s1;
		}else{
			s2 = pre0.toString()+s2;
		}

		String temp = "";
		int carry = 0;//进位
		String[] ss1 = null;
		String[] ss2 = null;
		if(maxlen > threshold){
			ss1 = group(s1,maxlen);
			ss2 = group(s2,maxlen);
		}else{
			ss1 = new String[]{s1,""};
			ss2 = new String[]{s2,""};
		}

		for(int i = ss1.length-1; i>=0; i--){
			String temp_str = parseString(ss1[i],ss2[i],carry);
			if(temp_str.length() > threshold){
				temp = temp_str.substring(1,temp_str.length()) + temp;
				carry = 1;
			}else{
				if(temp_str.length() > ss1[i].length()){
					temp = temp_str.substring(1,temp_str.length()) + temp;
					carry = 1;
				}else{
					temp = temp_str + temp;
					carry = 0;
				}

			}
		}
		if(carry != 0){
			temp = carry + temp;
		}
		return temp;
	}

	public static String add(String s1, String s2){
		long l1;
		long l2;
		try{
			l1 = Long.parseLong(s1);
			l2 = Long.parseLong(s2);
			if(l1+l2 < 0){
				return summation(s1, s2);
			}else{
				return String.valueOf(l1+l2);
			}
		}catch(NumberFormatException e){
			return summation(s1, s2);
		}
	}

	//根据给定的两个long类型的数值，相加后，得到字符串结果，如果结果的长度大于19,说明已经超过了long的最大表数
	public static String add(long l1 , long l2){
		if(l1 + l2 <0){
			return summation(String.valueOf(l1), String.valueOf(l2));
		}else{
			return String.valueOf(l1+l2);
		}
	}

	private static String parseString(String s1,String s2,int i){
		long l1 = 0;
		long l2 = 0;
		if(s1!=null && !s1.equals("")){
			l1 = Long.parseLong(s1);
		}
		if(s2!=null && !s2.equals("")){
			l2 = Long.parseLong(s2);
		}

		return (l1+l2+i>0) ? (String.valueOf(l1+l2+i)) : "";
	}

	private static String[] group(String s,int len){
		String[] ss = new String[(len/threshold)+1];
		for (int i = 0; i <= len/threshold; i++) {
			if(i==len/threshold)
				ss[i] = s.substring(i*threshold,len);
			else
				ss[i] = s.substring(i*threshold,i*threshold+threshold);
		}
		return ss;
	}

}



