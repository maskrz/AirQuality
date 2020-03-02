package airquality.project.service.calculation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airquality.project.dto.DeviceDTO;
import airquality.project.dto.MeasurementDTO;
import airquality.project.statics.MeasurementValueType;

@Service
public class MeasurementCalculationServiceImpl implements MeasurementCalculationService {

	@Autowired
	private DHT22CalculationService dht22CalculationService;

	@Override
	public MeasurementDTO measureCurrent(DeviceDTO device) {
		switch (device.getType()) {
		case DHT22:
			return dht22CalculationService.calculateMeasurement(device);
		default:
			return new MeasurementDTO();
		}
	}

	@Override
	public List<MeasurementDTO> calculateStatList(List<MeasurementDTO> measurements) {
		return new ArrayList<MeasurementDTO>(calculateStatMap(measurements).values());
	}

	@Override
	public Map<MeasurementValueType, MeasurementDTO> calculateStatMap(List<MeasurementDTO> measurements) {
		Map<MeasurementValueType, MeasurementDTO> resultMap = createResultStub();
		calculateAllStats(resultMap, measurements);
		return resultMap;
	}

	private Map<MeasurementValueType, MeasurementDTO> createResultStub() {
		Map<MeasurementValueType, MeasurementDTO> returnMap = new HashMap<>();
		returnMap.put(MeasurementValueType.MAX, new MeasurementDTO());
		returnMap.put(MeasurementValueType.MEAN, new MeasurementDTO());
		returnMap.put(MeasurementValueType.MIN, new MeasurementDTO());
		return returnMap;
	}

	private void calculateAllStats(Map<MeasurementValueType, MeasurementDTO> resultMap,
			List<MeasurementDTO> measurements) {
		for (Entry<MeasurementValueType, MeasurementDTO> entry : resultMap.entrySet()) {
			calculateForStat(resultMap, measurements, entry.getKey());
		}
	}

	private void calculateForStat(Map<MeasurementValueType, MeasurementDTO> resultMap,
			List<MeasurementDTO> measurements, MeasurementValueType key) {
		DoubleStream humidityStream = measurements.stream().filter(e -> e.getHumidityValue() != null)
				.mapToDouble(e -> e.getHumidityValue().doubleValue());
		DoubleStream temperatureStream = measurements.stream().filter(e -> e.getTemperatureValue() != null)
				.mapToDouble(e -> e.getTemperatureValue().doubleValue());
		DoubleStream airQualityStream = measurements.stream().filter(e -> e.getAirQualityValue() != null)
				.mapToDouble(e -> e.getAirQualityValue().doubleValue());
		MeasurementDTO measurement = null;
		OptionalDouble humidityOptional = null;
		OptionalDouble temperatureOptional = null;
		OptionalDouble airQualityOptional = null;
		switch (key) {
		case MAX:
			measurement = resultMap.get(MeasurementValueType.MAX);
			measurement.setValueType(MeasurementValueType.MAX);
			humidityOptional = humidityStream.max();
			temperatureOptional = temperatureStream.max();
			airQualityOptional = airQualityStream.max();
			break;
		case MEAN:
			measurement = resultMap.get(MeasurementValueType.MEAN);
			measurement.setValueType(MeasurementValueType.MEAN);
			humidityOptional = humidityStream.average();
			temperatureOptional = temperatureStream.average();
			airQualityOptional = airQualityStream.average();
			break;
		case MIN:
			measurement = resultMap.get(MeasurementValueType.MIN);
			measurement.setValueType(MeasurementValueType.MIN);
			humidityOptional = humidityStream.min();
			temperatureOptional = temperatureStream.min();
			airQualityOptional = airQualityStream.min();
			break;
		default:
			break;
		}
		setValues(measurement, humidityOptional, temperatureOptional, airQualityOptional);
	}

	private void setValues(MeasurementDTO measurement, OptionalDouble humidityOptional,
			OptionalDouble temperatureOptional, OptionalDouble airQualityOptional) {
		if (airQualityOptional.isPresent()) {
			measurement.setAirQualityValue(
					BigDecimal.valueOf(airQualityOptional.getAsDouble()).setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		if (temperatureOptional.isPresent()) {
			measurement.setTemperatureValue(
					BigDecimal.valueOf(temperatureOptional.getAsDouble()).setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		if (humidityOptional.isPresent()) {
			measurement.setHumidityValue(
					BigDecimal.valueOf(humidityOptional.getAsDouble()).setScale(2, BigDecimal.ROUND_HALF_UP));
		}
	}
}
