package airquality.project.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import airquality.project.dto.MeasurementDTO;
import airquality.project.statics.MeasurementTimePeriod;
import airquality.project.statics.MeasurementValueType;
import airquality.project.utils.DateTimeUtils;

@Component
public class ScheduledMeasurementsServiceImpl implements ScheduledMeasurementsService {

	@Autowired
	private MeasurementService measurementService;

	@Autowired
	WebserviceService webserviceSerivce;

	@Override
	public void measureAndSaveCurrent() {
		List<MeasurementDTO> allMeasurements = measurementService.getAllCurrentMeasurements();
		for (MeasurementDTO measurementDTO : allMeasurements) {
			measurementDTO.setMeasurementTimePeriod(MeasurementTimePeriod.SINGLE);
			measurementDTO.setValueType(MeasurementValueType.SINGLE);
			measurementDTO.setMeasurementTimestamp(new Timestamp(System.currentTimeMillis()));
			measurementService.saveMeasurement(measurementDTO);
		}

	}

	@Override
	public void handleHourlyMeasurements() {
		LocalDateTime current = DateTimeUtils.getCurrentFullHour();
		LocalDateTime hourBefore = current.minusHours(1);
		measurementService.handleHourlyMeasurements(hourBefore, current, hourBefore.toString());
	}

	@Override
	public void handleDailyMeasurements() {
		LocalDate yesterday = DateTimeUtils.getCurrentDate().minusDays(1);
		String sunrise = webserviceSerivce.getSunsetSunriseDataForDate(yesterday.toString()).getResults().getSunrise();
		String sunset = webserviceSerivce.getSunsetSunriseDataForDate(yesterday.toString()).getResults().getSunset();

		LocalDateTime ldtSunrise = DateTimeUtils
				.fromUTCtoGMT2(LocalDateTime.parse(sunrise.substring(0, sunrise.indexOf("+"))));
		LocalDateTime ltdSunset = DateTimeUtils
				.fromUTCtoGMT2(LocalDateTime.parse(sunset.substring(0, sunset.indexOf("+"))));
		measurementService.handleCyclicMeasurements(ldtSunrise, ltdSunset, MeasurementTimePeriod.DAILY,
				yesterday.toString());
	}

	@Override
	public void handleNightlyMeasurements() {
		LocalDate today = DateTimeUtils.getCurrentDate();
		LocalDate yesterday = today.minusDays(1);
		String sunrise = webserviceSerivce.getSunsetSunriseDataForDate(today.toString()).getResults().getSunrise();
		String sunset = webserviceSerivce.getSunsetSunriseDataForDate(yesterday.toString()).getResults().getSunset();

		LocalDateTime ldtSunrise = DateTimeUtils
				.fromUTCtoGMT2(LocalDateTime.parse(sunrise.substring(0, sunrise.indexOf("+"))));
		LocalDateTime ldtSunset = DateTimeUtils
				.fromUTCtoGMT2(LocalDateTime.parse(sunset.substring(0, sunset.indexOf("+"))));
		measurementService.handleCyclicMeasurements(ldtSunset, ldtSunrise, MeasurementTimePeriod.NIGHTLY,
				yesterday.toString() + "/" + today.toString());

	}

	@Override
	public void handleDaily24hMeasurements() {
		LocalDate yesterday = DateTimeUtils.getCurrentDate().minusDays(1);
		LocalDateTime fromTime = yesterday.atStartOfDay();
		LocalDateTime toTime = yesterday.atTime(LocalTime.of(23, 59, 0));
		measurementService.handleCyclicMeasurements(fromTime, toTime, MeasurementTimePeriod.DAILY_24H,
				yesterday.toString());

	}
}
