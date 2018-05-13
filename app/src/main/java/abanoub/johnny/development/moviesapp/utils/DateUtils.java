package abanoub.johnny.development.moviesapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Abanoub Maher on 5/14/16.
 */
public class DateUtils {
    public static String dateFormatter(String dtStart) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.US);
        try {
            Date date = format.parse(dtStart);
            String stringMonth = (String) android.text.format.DateFormat.format("MMM", date); //Jun
            String year = (String) android.text.format.DateFormat.format("yyyy", date); //2013
            String day = (String) android.text.format.DateFormat.format("dd", date); //20
            return stringMonth + ". " + day + " " + year;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "No date available";
        }
    }

    public static String currentDateFormatter() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMM d yyyy", Locale.getDefault());//"d MMM yyyy"
        String formattedDate = df.format(c.getTime());
        return formattedDate;

    }

    public static String dateSlashFormatter(String dtStart) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.US);
        try {
            Date date = format.parse(dtStart);
            String stringMonth = (String) android.text.format.DateFormat.format("MM", date); //Jun
            String year = (String) android.text.format.DateFormat.format("yyyy", date); //2013
            String day = (String) android.text.format.DateFormat.format("dd", date); //20
            return stringMonth + "/" + day + "/" + year;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return dtStart;
        }
    }

    public static boolean isBefore(String selectedDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date strDate = null;
        try {
            strDate = sdf.parse(selectedDate);
            Calendar c = Calendar.getInstance();
            Date currentLocalTime = c.getTime();

            return currentLocalTime.after(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isAfter(String selectedDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date strDate = null;
        try {
            strDate = sdf.parse(selectedDate);
            Calendar c = Calendar.getInstance();
            Date currentLocalTime = c.getTime();

            return currentLocalTime.before(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isDateAfter(int year, int month, int dayOfMonth) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        /*if (year == currentYear && month == currentMonth && dayOfMonth == currentDay)
            return false;
        else*/
        if (year > currentYear || month > currentMonth || dayOfMonth > currentDay)
            return true;
        else return false;
    }

    public static boolean isDateBefore(int year, int month, int dayOfMonth) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (year == currentYear && month == currentMonth && dayOfMonth == currentDay)
            return false;
        else if (year < currentYear)
            return true;
        else if(year == currentYear && month < currentMonth)
            return true;
        else if(year == currentYear && month == currentMonth && dayOfMonth < currentDay)
            return true;
        else return false;
    }

    public static boolean dateEquals(int year, int month, int dayOfMonth){
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        return (currentYear == year && currentMonth == month && currentDay == dayOfMonth);
    }

    public static boolean isTimeBefore(int hour, int min){
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMin = calendar.get(Calendar.MINUTE);

        if(hour < currentHour)
            return true;
        else if(hour == currentHour && min <= currentMin)
            return true;
        else return false;
    }

    public static String getMonthInArabic(String month){
        month = month.toLowerCase();
        month = month.trim();
        switch (month){
            case "jan":
                return "ينانير";
            case "feb":
                return "فبراير";
            case "mar":
                return "مارس";
            case "apr":
                return "أبريل";
            case "may":
                return "مايو";
            case "jun":
                return "يونيو";
            case "jul":
                return "يوليو";
            case "aug":
                return "أغسطس";
            case "sept":
                return "سبتمبر";
            case "oct":
                return "أكتوبر";
            case "nov":
                return "نوفمبر";
            case "dec":
                return "ديسمبر";
            default:
                return "ينانير";
        }
    }

    public static String replaceMonthWithArabic(String date){
        return date.replaceFirst("Jan", "يناير")
                .replaceFirst("Feb", "فبراير")
                .replaceFirst("Mar", "مارس")
                .replaceFirst("Apr", "أبريل")
                .replaceFirst("May", "مايو")
                .replaceFirst("Jun","يونيو")
                .replaceFirst("Jul", "يوليو")
                .replaceFirst("Aug", "أغسطس")
                .replaceFirst("Sept", "سبتمبر")
                .replaceFirst("Oct", "أكتوبر")
                .replaceFirst("Nov", "نوفمبر")
                .replaceFirst("Dec", "ينانير");
    }
}
