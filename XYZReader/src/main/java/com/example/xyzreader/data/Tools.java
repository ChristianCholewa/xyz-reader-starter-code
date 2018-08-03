package com.example.xyzreader.data;

import android.text.format.DateUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Tools {

    private static final String LOG_TAG = Tools.class.getSimpleName();

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss");

    private static SimpleDateFormat outputFormat = new SimpleDateFormat();

    private static GregorianCalendar START_OF_EPOCH = new GregorianCalendar(2,1,1);

    public static String parsePublishedDate(String date) {

        Date parsedDate;

        try {
            parsedDate = dateFormat.parse(date);
        } catch (ParseException ex) {
            Log.e(LOG_TAG, ex.getMessage());
            Log.i(LOG_TAG, "passing today's date");
            parsedDate =  new Date();
        }

        if (!parsedDate.before(START_OF_EPOCH.getTime())) {
            return DateUtils.getRelativeTimeSpanString(
                    parsedDate.getTime(),
                    System.currentTimeMillis(), DateUtils.HOUR_IN_MILLIS,
                    DateUtils.FORMAT_ABBREV_ALL).toString();
        } else {
            // If date is before 1902, just show the string
            return outputFormat.format(parsedDate);
        }
    }

}
