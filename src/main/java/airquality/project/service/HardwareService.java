package airquality.project.service;

import airquality.project.dto.StationDTO;
import airquality.project.statics.StationStatus;

import java.util.List;

public interface HardwareService {

    List<StationDTO> getAllStations();

    StationStatus getStationStatus(Long stationId);
}
