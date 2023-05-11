package com.example.notbasictodolist;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public String getDateOneDayBefore(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = format.parse(dateString);
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            Date previousDate = calendar.getTime();
            return format.format(previousDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
