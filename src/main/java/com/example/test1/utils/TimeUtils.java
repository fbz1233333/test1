package com.example.test1.utils;

import java.util.Date;

public class TimeUtils {

    public static void main(String[] args) {

        Date date=new Date();
        date.setHours(2);
        date.setMinutes(20);
        System.out.println(countIfOver(date,1800L));
    }

    public static boolean countIfOver(Date date1,Long outTime){
        boolean result=false;
        date1.getTime();
        Date now=new Date();
        if (now.getTime()-date1.getTime()>1800000){
            result=true;
        }


        return result;

    }

}
