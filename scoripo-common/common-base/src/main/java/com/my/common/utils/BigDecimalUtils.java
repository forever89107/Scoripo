package com.my.common.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

    public final static BigDecimal TEN_THOUSAND = new BigDecimal(10000);
    public final static BigDecimal HUNDRED = new BigDecimal(100);

    public static BigDecimal ifNullSet0(BigDecimal in) {
        if (in != null) {
            return in;
        }
        return BigDecimal.ZERO;
    }

    /**
     * sum
     */
    public static BigDecimal sum(BigDecimal ...in){
        BigDecimal result = BigDecimal.ZERO;
        for (int i = 0; i < in.length; i++){
            result = result.add(ifNullSet0(in[i]));
        }
        return result;
    }

      public static boolean commonCompare(BigDecimal amount, String operator, BigDecimal amount2) {
        int comparisonResult = amount.compareTo(amount2);

        switch (operator) {
            case "==":
            case "=":
                return comparisonResult == 0;
            case "!=":
                return comparisonResult != 0;
            case "<":
                return comparisonResult < 0;
            case ">":
                return comparisonResult > 0;
            case ">=":
                return comparisonResult >= 0;
            case "<=":
                return comparisonResult <= 0;
            default:
                return false;
        }
    }

    /**
     * 判斷是否BigDecimal
     */
    public static boolean isBigDecimal(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        char[] chars = str.toCharArray();
        int sz = chars.length;
        int i = (chars[0] == '-') ? 1 : 0;
        if (i == sz) return false;

        if (chars[i] == '.') return false;//besides '-'，first char can't be '.'

        boolean radixPoint = false;
        for (; i < sz; i++) {
            if (chars[i] == '.') {
                if (radixPoint) return false;
                radixPoint = true;
            } else if (!(chars[i] >= '0' && chars[i] <= '9')) {
                return false;
            }
        }
        return true;
    }


    public static boolean lessThanZero(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) < 0;
    }

    public static boolean moreThanZero(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
