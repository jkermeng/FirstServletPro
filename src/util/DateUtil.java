package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private DateUtil() {
    }

    public static String dateToStr(Date d, String formate) {
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        return sdf.format(d);
    }

    public static Date strToDate(String d, String formate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        Date parse = sdf.parse(d);

        return parse;
    }
}
