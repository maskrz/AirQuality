package airquality.project.service;

import airquality.project.dto.StationDTO;
import airquality.project.repository.StationRepository;
import airquality.project.utils.ConversionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HardwareServiceImpl implements HardwareService {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public List<StationDTO> getAllStations() {
        return ConversionUtils
                .stationIterableEntityToDTOList(stationRepository.findAll());
    }
}
