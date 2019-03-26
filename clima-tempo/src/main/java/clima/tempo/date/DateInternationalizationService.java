package clima.tempo.date;


import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateInternationalizationService {

    public static String getDayOfWeek(Date date, Locale locale) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
    }
}