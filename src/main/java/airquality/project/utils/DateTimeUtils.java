package airquality.project.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtils {

	public static LocalDateTime getCurrentFullHour() {
		return getCurrentLocalDateTime().withMinute(0).withSecond(0).withNano(0);
	}
	
	public static LocalDate getCurrentDate() {
		return LocalDate.now();
	}
	
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static Timestamp toTimestamp(LocalDateTime ldt) {
		return Timestamp.valueOf(ldt);
	}
	
	public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
		return timestamp.toLocalDateTime();
	}
	
	public static LocalDateTime getCurrentLocalDateTime() {
		return LocalDateTime.now();
	}
	
	public static LocalDateTime fromUTCtoGMT2(LocalDateTime utc) {
		ZoneId utcZone = ZoneId.of("UTC");
		ZoneId gmt2Zone = ZoneId.of("GMT+2");
		ZonedDateTime utcZoned = utc.atZone(utcZone);
		ZonedDateTime gmt2Zoned = utcZoned.withZoneSameInstant(gmt2Zone);
		return gmt2Zoned.toLocalDateTime();
	}
}
