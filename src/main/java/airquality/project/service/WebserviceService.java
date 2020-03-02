package airquality.project.service;

import airquality.project.dto.DHT22MeasurementDTO;
import airquality.project.dto.DeviceDTO;
import airquality.project.dto.SunsetSunriseWrapperDTO;

public interface WebserviceService {

	public SunsetSunriseWrapperDTO getSunsetSunriseDataForDate(String date);

	public DHT22MeasurementDTO measureCurrent(DeviceDTO device);
}
