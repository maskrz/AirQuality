package airquality.project.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import airquality.project.statics.MeasurementTimePeriod;
import airquality.project.statics.MeasurementValueType;

public class MeasurementDTO {

	private Long id;
	private Timestamp measurementTimestamp;
	private BigDecimal temperatureValue;
	private BigDecimal humidityValue;
	private BigDecimal airQualityValue;
	private String comment;
	private MeasurementTimePeriod measurementTimePeriod;
	private MeasurementValueType valueType;
	private Long stationId;

	public MeasurementDTO() {
	}

	public MeasurementDTO(Long id, Timestamp measurementTimestamp, BigDecimal temperatureValue, BigDecimal humidityValue,
			BigDecimal airQualityValue, String comment, MeasurementTimePeriod measurementTimePeriod,
			MeasurementValueType valueType, Long stationId) {
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

	public MeasurementTimePeriod getMeasurementTimePeriod() {
		return measurementTimePeriod;
	}

	public void setMeasurementTimePeriod(MeasurementTimePeriod measurementTimePeriod) {
		this.measurementTimePeriod = measurementTimePeriod;
	}

	public MeasurementValueType getValueType() {
		return valueType;
	}

	public void setValueType(MeasurementValueType valueType) {
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
		return "MeasurementDTO [id=" + id + ", measurementTimestamp=" + measurementTimestamp + ", temperatureValue="
				+ temperatureValue + ", humidityValue=" + humidityValue + ", airQualityValue=" + airQualityValue
				+ ", comment=" + comment + ", measurementTimePeriod=" + measurementTimePeriod + ", valueType="
				+ valueType + ", stationId=" + stationId + "]";
	}
}
