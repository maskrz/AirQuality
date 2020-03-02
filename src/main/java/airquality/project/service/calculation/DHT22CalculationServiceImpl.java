package airquality.project.service.calculation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airquality.project.dto.DHT22MeasurementDTO;
import airquality.project.dto.DeviceDTO;
import airquality.project.dto.MeasurementDTO;
import airquality.project.measurementproviders.DHT22MeasurementProvider;

@Service
public class DHT22CalculationServiceImpl implements DHT22CalculationService {

	// TODO syspar
	private static final int MEASUREMENT_TIMES = 5;

	@Autowired
	DHT22MeasurementProvider measurementProvider;

	@Override
	public MeasurementDTO calculateMeasurement(DeviceDTO device) {
		MeasurementDTO measurement = new MeasurementDTO();
		List<DHT22MeasurementDTO> measurementsList = getMeasurements(device);
		calculateAndSetMeasurement(measurementsList, measurement);
		return measurement;
	}

	private List<DHT22MeasurementDTO> getMeasurements(DeviceDTO device) {
		List<DHT22MeasurementDTO> resultsList = new ArrayList<>();
		for (int i = 0; i < MEASUREMENT_TIMES; i++) {
			resultsList.add(measurementProvider.measureCurrent(device));
		}
		return resultsList;
	}

	private void calculateAndSetMeasurement(List<DHT22MeasurementDTO> measurementsList, MeasurementDTO measurement) {
		BigDecimal humidityAverage = calculateAverageFromString(
				measurementsList.stream().map(e -> e.getHumidity()).collect(Collectors.toList()));
		BigDecimal temperatureAverage = calculateAverageFromString(
				measurementsList.stream().map(e -> e.getTemperature()).collect(Collectors.toList()));
		measurement.setHumidityValue(humidityAverage);
		measurement.setTemperatureValue(temperatureAverage);
	}

	private BigDecimal calculateAverageFromString(List<String> stringList) {
		return calculateAverageFromDouble(
				stringList.stream().mapToDouble(e -> Double.valueOf(e)).boxed().collect(Collectors.toList()));
	}

	private BigDecimal calculateAverageFromDouble(List<Double> doubleList) {
		List<Double> sortedList = doubleList.stream().sorted().collect(Collectors.toList());
		sortedList.remove(0);
		sortedList.remove(sortedList.size() - 1);
		return BigDecimal.valueOf(sortedList.stream().mapToDouble(e -> Double.valueOf(e)).average().getAsDouble())
				.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

}
