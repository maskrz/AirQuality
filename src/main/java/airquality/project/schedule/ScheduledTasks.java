package airquality.project.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import airquality.project.service.ScheduledMeasurementsService;

@Component
@Profile("prod")
public class ScheduledTasks {

	@Autowired
	private ScheduledMeasurementsService scheduledMeasurementService;

	@Scheduled(cron="0 5,15,25,35,45,55 * * * *")
	public void measureAndSaveCurrent() {
		scheduledMeasurementService.measureAndSaveCurrent();
	}

	@Scheduled(cron="0 1 * * * *")
	public void handleHourlyMeasurements() {
		scheduledMeasurementService.handleHourlyMeasurements();
	}

	@Scheduled(cron="0 3 0 * * *")
	public void handleDaily24hMeasurements() {
		scheduledMeasurementService.handleDaily24hMeasurements();
	}

	@Scheduled(cron="0 2 0 * * *")
	public void handleDailyMeasurements() {
		scheduledMeasurementService.handleDailyMeasurements();
	}

	@Scheduled(cron="0 2 12 * * *")
	public void handleNightlyMeasurements() {
		scheduledMeasurementService.handleNightlyMeasurements();
	}
}
