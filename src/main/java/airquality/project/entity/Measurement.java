package airquality.project.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Measurement")
public class Measurement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8918259191777117259L;

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "MEASUREMENT_TIMESTAMP")
	private Timestamp measurementTimestamp;

	@Column(name = "TEMPERATURE_VALUE")
	private BigDecimal temperatureValue;

	@Column(name = "HUMIDITY_VALUE")
	private BigDecimal humidityValue;

	@Column(name = "AIR_QUALITY_VALUE")
	private BigDecimal airQualityValue;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "MEASUREMENT_TIME_PERIOD")
	private String measurementTimePeriod;

	@Column(name = "VALUE_TYPE")
	private String valueType;

	@Column(name = "STATION_ID")
	private Long stationId;

	public Measurement() {
	}

	public Measurement(Long id, Timestamp measurementTimestamp, BigDecimal temperatureValue, BigDecimal humidityValue,
			BigDecimal airQualityValue, String comment, String measurementTimePeriod, String valueType, Long stationId) {
		super();
		this.id = id;
		this.measurementTimestamp = measurementTimestamp;
		this.temperatureValue = temperatureValue;
		this.humidityValue = humidityValue;
		this.airQualityValue = airQualityValue;
		this.comment = comment;
		this.measurementTimePeriod = measurementTimePeriod;
		this.valueType = valueType;
		this.stationId = stationId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getMeasurementTimestamp() {
		return measurementTimestamp;
	}

	public void setMeasurementTimestamp(Timestamp measurementTimestamp) {
		this.measurementTimestamp = measurementTimestamp;
	}

	public BigDecimal getTemperatureValue() {
		return temperatureValue;
	}

	public void setTemperatureValue(BigDecimal temperatureValue) {
		this.temperatureValue = temperatureValue;
	}

	public BigDecimal getHumidityValue() {
		return humidityValue;
	}

	public void setHumidityValue(BigDecimal humidityValue) {
		this.humidityValue = humidityValue;
	}

	public BigDecimal getAirQualityValue() {
		return airQualityValue;
	}

	public void setAirQualityValue(BigDecimal airQualityValue) {
		this.airQualityValue = airQualityValue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMeasurementTimePeriod() {
		return measurementTimePeriod;
	}

	public void setMeasurementTimePeriod(String measurementTimePeriod) {
		this.measurementTimePeriod = measurementTimePeriod;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	@Override
	public String toString() {
		return "Measurement [id=" + id + ", measurementTimestamp=" + measurementTimestamp + ", temperatureValue="
				+ temperatureValue + ", humidityValue=" + humidityValue + ", airQualityValue=" + airQualityValue
				+ ", comment=" + comment + ", measurementTimePeriod=" + measurementTimePeriod + ", valueType="
				+ valueType + ", stationId=" + stationId + "]";
	}

}
