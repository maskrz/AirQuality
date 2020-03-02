package airquality.project.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import airquality.project.entity.Station;

public class ConversionUtilsTest {

	@Test
	public void convertTest() {
		String test1 = "2019-10-02T04:54:19+00:00";
		// "yyyy-MM-dd'T'HH:mm:ssZ";
		System.out.println(test1.substring(0, test1.indexOf("+")));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
		LocalDateTime ldt = LocalDateTime.parse(test1.substring(0, test1.indexOf("+")));
		System.out.println(ldt);
		ZoneId zid = ZoneId.of("UTC");
		ZonedDateTime zoned = ldt.atZone(zid);
		ZoneId gmt2 = ZoneId.of("GMT+2");
		ZonedDateTime z2 = zoned.withZoneSameInstant(gmt2);
		System.out.println(zoned);
		System.out.println(z2);
	}
}
