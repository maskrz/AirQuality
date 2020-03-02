package airquality.project.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import airquality.project.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airquality.project.entity.Measurement;
import airquality.project.repository.DeviceRepository;
import airquality.project.repository.MeasurementRepository;
import airquality.project.repository.StationRepository;
import airquality.project.service.calculation.MeasurementCalculationService;
import airquality.project.statics.MeasurementTimePeriod;
import airquality.project.statics.MeasurementValueType;
import airquality.project.utils.ConversionUtils;
import airquality.project.utils.DateTimeUtils;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private static final Logger logger = LoggerFactory.getLogger(MeasurementServiceImpl.class);

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private MeasurementCalculationService measurementCalculationService;

    @Override
    public MeasurementDTO getLastMeasured(Long stationId) {
        List<Measurement> mList = measurementRepository.getLatestMeasuredByStationId(stationId);
        return mList.isEmpty() ? new MeasurementDTO() : ConversionUtils.entityToDTO(mList.get(0));
    }

    @Override
    public List<MeasurementDTO> getAllCurrentMeasurements() {
        logger.info("About to measure");
        List<MeasurementDTO> allMeasurements = new ArrayList<>();
        List<StationDTO> stations = ConversionUtils.stationIterableEntityToDTOList(stationRepository.findAll());
        logger.info("Found " + stations.size() + " stations");
        for (StationDTO station : stations) {
            logger.info("Station: " + station.getId() + " with name: " + station.getName());
            if (!station.getActive()) {
                logger.info("Station is not active! skipping...");
                continue;
            }
            try {
                MeasurementDTO measurement = getCurrentStationMeasurement(station);
                measurement.setStationId(station.getId());
                allMeasurements.add(measurement);
            } catch (Exception e) {
                // TODO: handle exception
                // TODO: some function for that

                logger.info("Exception: " + e == null ? "i empty! " : e.getLocalizedMessage());
            }
        }
        return allMeasurements;
    }

    @Override
    public MeasurementDTO getCurrentStationMeasurement(StationDTO station) {
        List<DeviceDTO> devices = ConversionUtils
                .deviceIterableEntityToDTOList(deviceRepository.findByStationId(station.getId()));
        MeasurementDTO measurementDTO = new MeasurementDTO();

        for (DeviceDTO device : devices) {
            logger.info("About to measure device: " + device.getId() + ", name: " + device.getName());
            if (!device.getActive()) {
                logger.info("Device is not active, skipping");
            }
            MeasurementDTO deviceMeasurement = measurementCalculationService.measureCurrent(device);
            ConversionUtils.copyNonNullProperties(deviceMeasurement, measurementDTO);
        }

        return measurementDTO;
    }

    @Override
    public void saveMeasurement(MeasurementDTO measurementDTO) {
        if (isValid(measurementDTO)) {
            measurementRepository.save(ConversionUtils.dtoToEntity(measurementDTO));
        }
    }

    private boolean isValid(MeasurementDTO measurement) {
        return (measurement.getHumidityValue() != null || measurement.getTemperatureValue() != null
                || measurement.getAirQualityValue() != null) && measurement.getHumidityValue() == null ? true
                : measurement.getHumidityValue().doubleValue() >= 0
                && measurement.getHumidityValue().doubleValue() <= 100;
    }

    @Override
    public void handleHourlyMeasurements(LocalDateTime fromTime, LocalDateTime toTime, String comment) {
        List<StationDTO> stations = ConversionUtils.stationIterableEntityToDTOList(stationRepository.findAll());
        for (StationDTO station : stations) {
            handleHourlyMeasurementsForStation(fromTime, toTime, comment, station.getId());
        }

    }

    private void handleHourlyMeasurementsForStation(LocalDateTime fromTime, LocalDateTime toTime, String comment,
                                                    Long stationId) {
        List<MeasurementDTO> hourResultsList = ConversionUtils.measurementIterableEntityToDTOList(
                measurementRepository.getForTimePeriodByStationIdAndTimePeriod(DateTimeUtils.toTimestamp(fromTime),
                        DateTimeUtils.toTimestamp(toTime), stationId, MeasurementTimePeriod.SINGLE.toString()));
        List<MeasurementDTO> resultsList = measurementCalculationService.calculateStatList(hourResultsList);
        resultsList.stream()
                .forEach(e -> setMeasurementInformation(e, stationId, comment, MeasurementTimePeriod.HOURLY));
        resultsList.stream().forEach(e -> saveMeasurement(e));
    }

    private void setMeasurementInformation(MeasurementDTO measurement, Long stationId, String comment,
                                           MeasurementTimePeriod timePeriod) {
        measurement.setStationId(stationId);
        measurement.setMeasurementTimePeriod(timePeriod);
        measurement.setMeasurementTimestamp(DateTimeUtils.getCurrentTimestamp());
        measurement.setComment(comment);
    }

    @Override
    public void handleCyclicMeasurements(LocalDateTime fromTime, LocalDateTime toTime, MeasurementTimePeriod timePeriod,
                                         String comment) {
        List<StationDTO> stations = ConversionUtils.stationIterableEntityToDTOList(stationRepository.findAll());
        for (StationDTO station : stations) {
            handleCyclicMeasurementsForStation(fromTime, toTime, timePeriod, comment, station.getId());
        }
    }

    private void handleCyclicMeasurementsForStation(LocalDateTime fromTime, LocalDateTime toTime,
                                                    MeasurementTimePeriod timePeriod, String comment, Long stationId) {
        List<MeasurementDTO> allMeasurements = getForTimePeriodByStationIdAndTimePeriod(fromTime, toTime, stationId,
                MeasurementTimePeriod.HOURLY);
        List<MeasurementDTO> meanMeasurements = getForTimePeriodByStationIdAndTimePeriodAndValueType(fromTime, toTime,
                stationId, MeasurementTimePeriod.HOURLY, MeasurementValueType.MEAN);
        Map<MeasurementValueType, MeasurementDTO> minMaxMap = measurementCalculationService
                .calculateStatMap(allMeasurements);
        Map<MeasurementValueType, MeasurementDTO> meanMap = measurementCalculationService
                .calculateStatMap(meanMeasurements);
        setMeasurementInformation(minMaxMap.get(MeasurementValueType.MAX), stationId, comment, timePeriod);
        setMeasurementInformation(minMaxMap.get(MeasurementValueType.MIN), stationId, comment, timePeriod);
        setMeasurementInformation(meanMap.get(MeasurementValueType.MEAN), stationId, comment, timePeriod);
        saveMeasurement(minMaxMap.get(MeasurementValueType.MAX));
        saveMeasurement(minMaxMap.get(MeasurementValueType.MIN));
        saveMeasurement(meanMap.get(MeasurementValueType.MEAN));
    }

    private List<MeasurementDTO> getForTimePeriodByStationIdAndTimePeriodAndValueType(LocalDateTime fromTime,
                                                                                      LocalDateTime toTime, Long stationId, MeasurementTimePeriod timePeriod, MeasurementValueType valueType) {
        return ConversionUtils.measurementIterableEntityToDTOList(measurementRepository
                .getForTimePeriodByStationIdAndTimePeriodAndValueType(DateTimeUtils.toTimestamp(fromTime),
                        DateTimeUtils.toTimestamp(toTime), timePeriod.toString(), stationId, valueType.toString()));
    }

    private List<MeasurementDTO> getForTimePeriodByStationIdAndTimePeriod(LocalDateTime fromTime, LocalDateTime toTime,
                                                                          Long stationId, MeasurementTimePeriod timePeriod) {
        return ConversionUtils.measurementIterableEntityToDTOList(
                measurementRepository.getForTimePeriodByStationIdAndTimePeriod(DateTimeUtils.toTimestamp(fromTime),
                        DateTimeUtils.toTimestamp(toTime), stationId, timePeriod.toString()));
    }

    @Override
    public List<MeasurementDTO> getYesterdayData() {
        LocalDate yesterday = DateTimeUtils.getCurrentDate().minusDays(1);
        LocalDateTime fromTime = yesterday.atStartOfDay();
        LocalDateTime toTime = yesterday.atTime(LocalTime.of(23, 59, 0));
        logger.info("FROM TIME " + fromTime);
        logger.info("FROM TIME TIMESTAMP " + DateTimeUtils.toTimestamp(fromTime));
        List<MeasurementDTO> yesterdayData = ConversionUtils.measurementIterableEntityToDTOList(
                measurementRepository.getForTimePeriodByStationIdAndTimePeriodAndValueType(
                        DateTimeUtils.toTimestamp(fromTime), DateTimeUtils.toTimestamp(toTime),
                        MeasurementTimePeriod.HOURLY.toString(), 2l, MeasurementValueType.MEAN.toString()));
        return yesterdayData;
    }

    @Override
    public List<MeasurementDTO> last24Indoor() {
        LocalDateTime fromTime = DateTimeUtils.getCurrentLocalDateTime().minusDays(1);
        LocalDateTime toTime = DateTimeUtils.getCurrentLocalDateTime();
        logger.info("FROM TIME " + fromTime);
        logger.info("FROM TIME TIMESTAMP " + DateTimeUtils.toTimestamp(fromTime));
        List<MeasurementDTO> yesterdayData = ConversionUtils.measurementIterableEntityToDTOList(
                measurementRepository.getForTimePeriodByStationIdAndTimePeriodAndValueType(
                        DateTimeUtils.toTimestamp(fromTime), DateTimeUtils.toTimestamp(toTime),
                        MeasurementTimePeriod.HOURLY.toString(), 2l, MeasurementValueType.MEAN.toString()));
        return yesterdayData;
    }

    @Override
    public List<MeasurementDTO> last24Outdoor() {
        LocalDateTime fromTime = DateTimeUtils.getCurrentLocalDateTime().minusDays(1);
        LocalDateTime toTime = DateTimeUtils.getCurrentLocalDateTime();
        logger.info("FROM TIME " + fromTime);
        logger.info("FROM TIME TIMESTAMP " + DateTimeUtils.toTimestamp(fromTime));
        List<MeasurementDTO> yesterdayData = ConversionUtils.measurementIterableEntityToDTOList(
                measurementRepository.getForTimePeriodByStationIdAndTimePeriodAndValueType(
                        DateTimeUtils.toTimestamp(fromTime), DateTimeUtils.toTimestamp(toTime),
                        MeasurementTimePeriod.HOURLY.toString(), 3l, MeasurementValueType.MEAN.toString()));
        return yesterdayData;
    }

    @Override
    public List<MeasurementDTO> getCustom(SearchParameters searchParameters) {
        Timestamp from = Timestamp.from(searchParameters.getFromDate().toInstant());
        Timestamp to = Timestamp.from(searchParameters.getToDate().toInstant());
        List<MeasurementDTO> customResults = ConversionUtils.measurementIterableEntityToDTOList(
                measurementRepository.getForTimePeriodByStationIdAndTimePeriod(from,
                        to, searchParameters.getStations().get(0), searchParameters.getMeasurementTimePeriod()));
        return customResults;
    }

    @Override
    public List<MeasurementDTO> getForSingleLine(SingleLineSearchParameters singleLineSearchParameters) {
        Timestamp from = Timestamp.from(singleLineSearchParameters.getFromDate().toInstant());
        Timestamp to = Timestamp.from(singleLineSearchParameters.getToDate().toInstant());
        List<MeasurementDTO> customResults = ConversionUtils.measurementIterableEntityToDTOList(
                measurementRepository.getForTimePeriodByStationIdAndTimePeriodAndValueType(from,
                        to, singleLineSearchParameters.getMeasurementTimePeriod(), singleLineSearchParameters.getStation(),
                        singleLineSearchParameters.getMeasurementValueType()));
        return customResults;
    }

}
