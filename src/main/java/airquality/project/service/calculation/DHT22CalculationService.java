package airquality.project.service.calculation;

import airquality.project.dto.DeviceDTO;
import airquality.project.dto.MeasurementDTO;

public interface DHT22CalculationService {

	public MeasurementDTO calculateMeasurement(DeviceDTO device);
}
