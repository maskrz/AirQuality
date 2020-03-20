package airquality.project.service;

import airquality.project.dto.MeasurementDTO;
import airquality.project.dto.StationDTO;
import airquality.project.repository.StationRepository;
import airquality.project.statics.StationStatus;
import airquality.project.utils.ConversionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class HardwareServiceImpl implements HardwareService {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private MeasurementService measurementService;

    @Override
    public List<StationDTO> getAllStations() {
        return ConversionUtils
                .stationIterableEntityToDTOList(stationRepository.findAll());
    }

    @Override
    public StationStatus getStationStatus(Long stationId) {
        StationDTO station = ConversionUtils.entityToDTO(stationRepository.findById(stationId).orElseThrow(() -> new IllegalArgumentException("cannot find station with id " + stationId)));
        MeasurementDTO currentMeasurement = measurementService.getLastMeasured(stationId);
        long minutesDifference = ChronoUnit.MINUTES.between(currentMeasurement.getMeasurementTimestamp().toLocalDateTime(), LocalDateTime.now());
        return minutesDifference > 15? StationStatus.FAILED : StationStatus.OK;
    }
}
