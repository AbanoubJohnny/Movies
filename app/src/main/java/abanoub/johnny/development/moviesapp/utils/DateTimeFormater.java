package abanoub.johnny.development.moviesapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Abanoub Maher on 6/17/2017.
 */

public class DateTimeFormater {
    public static String get12HoursTime(String time) {
        String period;
        String[] timeSlices = time.split(":");
        int hour = Integer.parseInt(timeSlices[0]);
        if (hour > 12) {
            period = "PM";
            hour = hour - 12;
        } else if (hour == 0) {
            period = "AM";
            hour = 12;
        } else if (hour == 12) {
            period = "PM";
            hour = 12;
        } else {
            period = "AM";
        }
        return hour + ":" + timeSlices[1] + " " + period;
    }

    public static String getLongDateFormat(String shortDate) {
        String longDate = "";
        try {

            SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = inFormat.parse(shortDate);
            SimpleDateFormat outFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
            longDate = outFormat.format(date) + ", ";
            String[] fullDate = date.toString().split(" ");
            longDate += fullDate[2] + " " + fullDate[1] + " " + fullDate[5];

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return longDate;

    }
}
