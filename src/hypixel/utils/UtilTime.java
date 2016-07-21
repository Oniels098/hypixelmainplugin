package hypixel.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Niels on 21-07-16.
 */
public class UtilTime {

    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";

    public static String now()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public static String when(long time)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(Long.valueOf(time));
    }

    public static String date()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    public static enum TimeUnit
    {
        FIT,  DAYS,  HOURS,  MINUTES,  SECONDS,  MILLISECONDS;
    }

    public static String since(long epoch)
    {
        return "Took " + convertString(System.currentTimeMillis() - epoch, 1, TimeUnit.FIT) + ".";
    }

    public static double convert(long time, int trim, TimeUnit type)
    {
        if (type == TimeUnit.FIT) {
            if (time < 60000L) {
                type = TimeUnit.SECONDS;
            } else if (time < 3600000L) {
                type = TimeUnit.MINUTES;
            } else if (time < 86400000L) {
                type = TimeUnit.HOURS;
            } else {
                type = TimeUnit.DAYS;
            }
        }
        if (type == TimeUnit.DAYS) {
            return UtilMath.trim(trim, time / 8.64E7D);
        }
        if (type == TimeUnit.HOURS) {
            return UtilMath.trim(trim, time / 3600000.0D);
        }
        if (type == TimeUnit.MINUTES) {
            return UtilMath.trim(trim, time / 60000.0D);
        }
        if (type == TimeUnit.SECONDS) {
            return UtilMath.trim(trim, time / 1000.0D);
        }
        return UtilMath.trim(trim, time);
    }

    public static String MakeStr(long time)
    {
        return convertString(time, 1, TimeUnit.FIT);
    }

    public static String MakeStr(long time, int trim)
    {
        return convertString(time, trim, TimeUnit.FIT);
    }

    public static String convertString(long time, int trim, TimeUnit type)
    {
        if (time == -1L) {
            return "Permanent";
        }
        if (type == TimeUnit.FIT) {
            if (time < 60000L) {
                type = TimeUnit.SECONDS;
            } else if (time < 3600000L) {
                type = TimeUnit.MINUTES;
            } else if (time < 86400000L) {
                type = TimeUnit.HOURS;
            } else {
                type = TimeUnit.DAYS;
            }
        }
        if (type == TimeUnit.DAYS) {
            return UtilMath.trim(trim, time / 8.64E7D) + " Days";
        }
        if (type == TimeUnit.HOURS) {
            return UtilMath.trim(trim, time / 3600000.0D) + " Hours";
        }
        if (type == TimeUnit.MINUTES) {
            return UtilMath.trim(trim, time / 60000.0D) + " Minutes";
        }
        if (type == TimeUnit.SECONDS) {
            return UtilMath.trim(trim, time / 1000.0D) + " Seconds";
        }
        return UtilMath.trim(trim, time) + " Milliseconds";
    }

    public static boolean elapsed(long from, long required)
    {
        return System.currentTimeMillis() - from > required;
    }
}