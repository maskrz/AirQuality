package airquality.project.service.calculation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import airquality.project.dto.DHT22MeasurementDTO;
import airquality.project.dto.DeviceDTO;
import airquality.project.dto.MeasurementDTO;
import airquality.project.entity.Device;
import airquality.project.measurementproviders.DHT22MeasurementProvider;
import airquality.project.statics.DeviceType;

public class DHT22CalculationServiceImplTest {

	@Mock
	private DHT22MeasurementProvider providerMock;

	@InjectMocks
	private DHT22CalculationServiceImpl calculationService;

	@Before
	public void setupOnce() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_sameValues() {
		// given
		DHT22MeasurementDTO mockReturnValue = new DHT22MeasurementDTO();
		mockReturnValue.setHumidity("15.2");
		mockReturnValue.setTemperature("23.5");
		when(providerMock.measureCurrent(ArgumentMatchers.any())).thenReturn(mockReturnValue);

		// when
		MeasurementDTO measurement = calculationService.calculateMeasurement(null);

		// then
		assertEquals(15.2, measurement.getHumidityValue().doubleValue(), 0.01);
		assertEquals(23.5, measurement.getTemperatureValue().doubleValue(), 0.01);
	}

	@Test
	public void test_oneInvalid() {
		// given
		DHT22MeasurementDTO mockFirstValue = new DHT22MeasurementDTO();
		mockFirstValue.setHumidity("10.2");
		mockFirstValue.setTemperature("23.5");
		DHT22MeasurementDTO mockLastValue = new DHT22MeasurementDTO();
		mockLastValue.setHumidity("15.2");
		mockLastValue.setTemperature("29.5");
		DHT22MeasurementDTO mockReturnValue = new DHT22MeasurementDTO();
		mockReturnValue.setHumidity("15.2");
		mockReturnValue.setTemperature("23.5");
		when(providerMock.measureCurrent(ArgumentMatchers.any())).thenReturn(mockFirstValue, mockReturnValue,
				mockLastValue, mockReturnValue, mockReturnValue);

		// when
		MeasurementDTO measurement = calculationService.calculateMeasurement(null);

		// then
		assertEquals(15.2, measurement.getHumidityValue().doubleValue(), 0.01);
		assertEquals(23.5, measurement.getTemperatureValue().doubleValue(), 0.01);

	}

	@Test
	public void test_allDifferent() {
		// given
		DHT22MeasurementDTO mock1Value = new DHT22MeasurementDTO();
		mock1Value.setHumidity("22.2");
		mock1Value.setTemperature("11.3");
		DHT22MeasurementDTO mock2Value = new DHT22MeasurementDTO();
		mock2Value.setHumidity("24.4");
		mock2Value.setTemperature("10.4");
		DHT22MeasurementDTO mock3Value = new DHT22MeasurementDTO();
		mock3Value.setHumidity("23.5");
		mock3Value.setTemperature("11.2");
		DHT22MeasurementDTO mock4Value = new DHT22MeasurementDTO();
		mock4Value.setHumidity("23.1");
		mock4Value.setTemperature("9.9");
		DHT22MeasurementDTO mock5Value = new DHT22MeasurementDTO();
		mock5Value.setHumidity("22.9");
		mock5Value.setTemperature("10.5");
		when(providerMock.measureCurrent(ArgumentMatchers.any())).thenReturn(mock1Value, mock2Value,
				mock3Value, mock4Value, mock5Value);

		// when
		MeasurementDTO measurement = calculationService.calculateMeasurement(null);

		// then
		assertEquals(23.17, measurement.getHumidityValue().doubleValue(), 0.01);
		assertEquals(10.7, measurement.getTemperatureValue().doubleValue(), 0.01);
	}
	
	@Test
	@Ignore
	public void test() {
		DeviceDTO device = new DeviceDTO(1l, "name", 2l, DeviceType.DHT22, "asdasd", Boolean.TRUE);
		Device entity = new Device();
		BeanUtils.copyProperties(device, entity);
		entity.setType(device.getType().toString());
		System.out.println(entity);
		DeviceDTO newDevice = new DeviceDTO();
		BeanUtils.copyProperties(entity, newDevice);
		newDevice.setType(DeviceType.valueOf(entity.getType()));
		System.out.println("");
	}
}
