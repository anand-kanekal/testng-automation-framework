package framework.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Practice {

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date);
        LocalTime time = LocalTime.now();
        System.out.println(time);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println(localDateTime.format(dateTimeFormatter));

        Month month = date.getMonth();
        System.out.println(month);
        int day = date.getDayOfMonth();
        System.out.println(day);

        LocalDate date1 = date.withDayOfMonth(12).withYear(2023);
        System.out.println(date1);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime.getZone());
        ZoneId zoneId = ZoneId.of("Europe/Dublin");
        System.out.println(zonedDateTime.withZoneSameInstant(zoneId));

        LocalDate custom = LocalDate.of(2024, Month.NOVEMBER, 1);
        Period gap = Period.between(custom, date);
        System.out.println(gap.getDays());
    }
}
