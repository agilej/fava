package me.donnior.fava.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Numbers {

    @SuppressWarnings("unchecked")
    public static <T> T plus(T first, T second) {
        if (first == null) {
            return second;
        } else if (second == null) {
            return first;
        } else if (first instanceof BigDecimal) {
            return (T) aggregate((BigDecimal) first, (BigDecimal) second);
        } else if (second instanceof BigInteger) {
            return (T) aggregate((BigInteger) first, (BigInteger) second);
        } else if (first instanceof Byte) {
            return (T) aggregate((Byte) first, (Byte) second);
        } else if (first instanceof Double) {
            return (T) aggregate((Double) first, (Double) second);
        } else if (first instanceof Float) {
            return (T) aggregate((Float) first, (Float) second);
        } else if (first instanceof Integer) {
            return (T) aggregate((Integer) first, (Integer) second);
        } else if (first instanceof Long) {
            return (T) aggregate((Long) first, (Long) second);
        } else if (first instanceof Short) {
            return (T) aggregate((Short) first, (Short) second);
        } else {
            throw new UnsupportedOperationException(
                    "The plus operation only supports official subclasses of Number");
        }
    }

    public static BigDecimal aggregate(BigDecimal first, BigDecimal second) {
        return first.add(second);
    }

    public static BigInteger aggregate(BigInteger first, BigInteger second) {
        return first.add(second);
    }

    public static Byte aggregate(Byte first, Byte second) {
        return (byte) (first + second);
    }

    public static Double aggregate(Double first, Double second) {
        return first + second;
    }

    public static Float aggregate(Float first, Float second) {
        return first + second;
    }

    public static Integer aggregate(Integer first, Integer second) {
        return first + second;
    }

    public static Long aggregate(Long first, Long second) {
        return first + second;
    }

    public static Short aggregate(Short first, Short second) {
        return (short) (first + second);
    }
}
