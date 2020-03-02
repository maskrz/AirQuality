package airquality.project.service;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import airquality.project.dto.DHT22MeasurementDTO;
import airquality.project.dto.DeviceDTO;
import airquality.project.dto.SunsetSunriseWrapperDTO;
import airquality.project.statics.ApplicationStatics;
import airquality.project.utils.GsonProvider;

@Component
public class WebserviceServiceImpl implements WebserviceService {

	@Override
	public SunsetSunriseWrapperDTO getSunsetSunriseDataForDate(String date) {
		try {
			// TODO syspar
			URL url = new URL(
					"https://api.sunrise-sunset.org/json?lat=51.1079&lng=17.0385&formatted=0&date=" + date);
			String response = IOUtils.toString(url, ApplicationStatics.ENCODING_UTF8);
			if (response != null && !response.isEmpty()) {
				return GsonProvider.getGsonInstance().fromJson(response, SunsetSunriseWrapperDTO.class);
			}
		} catch (IOException ex) {
			// TODO
			System.err.print(ex.getLocalizedMessage());
		}
		return new SunsetSunriseWrapperDTO();
	}

	@Override
	public DHT22MeasurementDTO measureCurrent(DeviceDTO device) {
		try {
			URL url = new URL(device.getServiceUrl());
			String response = IOUtils.toString(url, ApplicationStatics.ENCODING_UTF8);
			if (response != null && !response.isEmpty()) {
				return GsonProvider.getGsonInstance().fromJson(response, DHT22MeasurementDTO.class);
			}
		} catch (IOException ex) {
			// TODO
			System.err.print(ex.getLocalizedMessage());
		}
		return new DHT22MeasurementDTO();
	}

}
