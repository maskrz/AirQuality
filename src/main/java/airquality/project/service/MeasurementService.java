package airquality.project.service;

import java.time.LocalDateTime;
import java.util.List;

import airquality.project.dto.MeasurementDTO;
import airquality.project.dto.SearchParameters;
import airquality.project.dto.SingleLineSearchParameters;
import airquality.project.dto.StationDTO;
import airquality.project.statics.MeasurementTimePeriod;

public interface MeasurementService {

	public MeasurementDTO getLastMeasured(Long stationId);

	public List<MeasurementDTO> getAllCurrentMeasurements();

	public MeasurementDTO getCurrentStationMeasurement(StationDTO station);

	public void saveMeasurement(MeasurementDTO measurementDTO);

	public void handleHourlyMeasurements(LocalDateTime fromTime, LocalDateTime toTime, String comment);

	public void handleCyclicMeasurements(LocalDateTime fromTime, LocalDateTime toTime, MeasurementTimePeriod timePeriod,
			String comment);
	
	public List<MeasurementDTO> getYesterdayData();

	public List<MeasurementDTO> last24Indoor();

	public List<MeasurementDTO> last24Outdoor();

	public List<MeasurementDTO> getCustom(SearchParameters searchParameters);

	public List<MeasurementDTO> getForSingleLine(SingleLineSearchParameters singleLineSearchParameters);
}
