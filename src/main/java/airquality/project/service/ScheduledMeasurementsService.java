package airquality.project.service;

public interface ScheduledMeasurementsService {

	public void measureAndSaveCurrent();
	
	public void handleHourlyMeasurements();
	
	public void handleDailyMeasurements();
	
	public void handleNightlyMeasurements();
	
	public void handleDaily24hMeasurements();
}
