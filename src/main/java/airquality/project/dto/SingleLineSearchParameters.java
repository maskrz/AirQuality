package airquality.project.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class SingleLineSearchParameters implements Serializable {

    private String measurementTimePeriod;

    private Date fromDate;

    private Date toDate;

    private Long station;

    private String measurementValueType;

    public SingleLineSearchParameters(){};

    public SingleLineSearchParameters(String measurementTimePeriod, Date fromDate, Date toDate, Long station, String measurementValueType) {
        this.measurementTimePeriod = measurementTimePeriod;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.station = station;
        this.measurementValueType = measurementValueType;
    }

    public String getMeasurementTimePeriod() {
        return measurementTimePeriod;
    }

    public void setMeasurementTimePeriod(String measurementTimePeriod) {
        this.measurementTimePeriod = measurementTimePeriod;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Long getStation() {
        return station;
    }

    public void setStation(Long station) {
        this.station = station;
    }

    public String getMeasurementValueType() {
        return measurementValueType;
    }

    public void setMeasurementValueType(String measurementValueType) {
        this.measurementValueType = measurementValueType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleLineSearchParameters that = (SingleLineSearchParameters) o;

        if (!Objects.equals(measurementTimePeriod, that.measurementTimePeriod))
            return false;
        if (!Objects.equals(fromDate, that.fromDate)) return false;
        if (!Objects.equals(toDate, that.toDate)) return false;
        if (!Objects.equals(station, that.station)) return false;
        return Objects.equals(measurementValueType, that.measurementValueType);
    }

    @Override
    public int hashCode() {
        int result = measurementTimePeriod != null ? measurementTimePeriod.hashCode() : 0;
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (toDate != null ? toDate.hashCode() : 0);
        result = 31 * result + (station != null ? station.hashCode() : 0);
        result = 31 * result + (measurementValueType != null ? measurementValueType.hashCode() : 0);
        return result;
    }
}
