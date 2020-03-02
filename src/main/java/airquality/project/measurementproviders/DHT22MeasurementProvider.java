package airquality.project.measurementproviders;

import airquality.project.dto.DHT22MeasurementDTO;
import airquality.project.dto.DeviceDTO;

public interface DHT22MeasurementProvider {

	public DHT22MeasurementDTO measureCurrent(DeviceDTO device);
}
