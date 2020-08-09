package examples;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateTimeAPI {
    public static void main(String args[]) {
        ZonedDateTime zonedDateTimeOf = ZonedDateTime.of(2018, 01, 01, 0, 0, 0, 0, ZoneId.of("UTC"));

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("UTC"));

        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
        String formattedString = zonedDateTime.format(formatter);

        System.out.println(formattedString);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");
        String formattedString2 = zonedDateTime.format(formatter2);

        System.out.println(formattedString2);

        ZonedDateTime zonedDateTimeConverted = ZonedDateTime.parse("2011-12-03T10:15:30+01:00");

        //exception
        ZonedDateTime zonedDateTimeEx = ZonedDateTime.parse("2011-12-03T10:15:30", DateTimeFormatter.ISO_DATE_TIME);

        ZoneId timeZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTimeWithTZ = LocalDateTime.parse("2011-12-03T10:15:30",
                DateTimeFormatter.ISO_DATE_TIME).atZone(timeZone);

        LocalDate date = LocalDate.parse("2018-05-05");

        LocalDateTime dateTime = LocalDateTime.parse("2018-05-05T11:50:55");

        ZonedDateTime zonedDateTimeParsed = ZonedDateTime.parse("2015-05-05T10:15:30+01:00[Europe/Paris]");

        String dateInString = "19590709";
        LocalDate basicDate = LocalDate.parse(dateInString, DateTimeFormatter.BASIC_ISO_DATE);

        String dateInStringWord = "Mon, 05 May 1980";
        DateTimeFormatter formatterWord = DateTimeFormatter.ofPattern("EEE, d MMM yyyy", Locale.ENGLISH);
        LocalDate dateTimeInWord = LocalDate.parse(dateInStringWord, formatterWord);

    }

    public static int getDayNumberNew(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day.getValue();
    }

    public static String getDayStringNew(LocalDate date, Locale locale) {
        DayOfWeek day = date.getDayOfWeek();
        return day.getDisplayName(TextStyle.FULL, locale);
    }
}
