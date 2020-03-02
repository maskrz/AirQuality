package airquality.project.service.calculation;

import java.util.List;
import java.util.Map;

import airquality.project.dto.DeviceDTO;
import airquality.project.dto.MeasurementDTO;
import airquality.project.statics.MeasurementValueType;

public interface MeasurementCalculationService {

	public MeasurementDTO measureCurrent(DeviceDTO devices);

	public List<MeasurementDTO> calculateStatList(List<MeasurementDTO> measurements);

	public Map<MeasurementValueType, MeasurementDTO> calculateStatMap(List<MeasurementDTO> measurements);
}
