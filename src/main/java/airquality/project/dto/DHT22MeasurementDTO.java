package airquality.project.dto;

import java.io.Serializable;

public class DHT22MeasurementDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2681591347769103209L;

	private String temperature;

	private String humidity;

	public DHT22MeasurementDTO() {
	}

	public DHT22MeasurementDTO(String temperature, String humidity) {
		super();
		this.temperature = temperature;
		this.humidity = humidity;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	@Override
	public String toString() {
		return "DHT22DTO [temperature=" + temperature + ", humidity=" + humidity + "]";
	}

}
