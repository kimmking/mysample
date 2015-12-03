package com.roytrack.dailytest.number;

import java.math.BigDecimal;

/**
 * Created by ruanchangming on 2015/11/14.
 */
public class RoundTest {
    public static void main(String[] args) {
        String s=String.valueOf(45.275);
        BigDecimal b=new BigDecimal(s);
        System.out.println(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.println(b.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());

        BigDecimal a=new BigDecimal(45.275);
        System.out.println(a.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.println(a.setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue());
        BigDecimal c=new BigDecimal("24.5");
        BigDecimal d=new BigDecimal("67.475");
        System.out.println(c.add(d).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());

        int intA=5;
        int intB=4;
        int intC=3;
        int intD=2;
        System.out.println((intA/intB)+"  "+(intA/intC)+"  "+(intA/intD)+"");
    }
}