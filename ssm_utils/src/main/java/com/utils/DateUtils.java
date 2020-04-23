package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String dateToStirng(Date date, String patt) {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String s = sdf.format(date);
        return s;
    }

    public static Date stringToDate(String str, String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse = sdf.parse(str);
        return parse;

    }
}
