package airquality.project.measurementproviders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import airquality.project.dto.DHT22MeasurementDTO;
import airquality.project.dto.DeviceDTO;
import airquality.project.service.WebserviceService;

@Component
public class DHT22MeasurementProviderImpl implements DHT22MeasurementProvider {

	@Autowired
	WebserviceService webserviceSerivce;
	
	@Override
	public DHT22MeasurementDTO measureCurrent(DeviceDTO device) {
		return webserviceSerivce.measureCurrent(device);
	}
}
