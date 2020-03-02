package airquality.project.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class SearchParameters implements Serializable {

    private String measurementTimePeriod;

    private Date fromDate;

    private Date toDate;

    private ArrayList<Long> stations;

    public SearchParameters(){};

    public SearchParameters(String measurementTimePeriod, Date fromDate, Date toDate, ArrayList<Long> stations) {
        this.measurementTimePeriod = measurementTimePeriod;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.stations = stations;
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

    public void setStations(ArrayList<Long> stations) {
        this.stations = stations;
    }

    public ArrayList<Long> getStations() {
        return stations;
    }
}
