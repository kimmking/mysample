package com.roytrack.dailytest;

import java.math.BigDecimal;

/**
 * Created by roytrack on 2014/12/30.
 */
public class BigDecimalToStringNoE {
    public static void main(String[] args) {
//        System.out.println(String_String_Enum.getEnum("2000").getDesc());
//        System.out.println((new BigDecimal("100000000000000000").stripTrailingZeros().toPlainString()));
//        System.out.println((new BigDecimal("100000000000000000").stripTrailingZeros().toString()));
//        System.out.println((new BigDecimal("100000000000000000").stripTrailingZeros().toEngineeringString()));

        double a=12.2233344;
        a=3014565.8499999978;
        BigDecimal b = new BigDecimal(a);
        //保留2位小数
        double result = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(result);
    }
}
