package airquality.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import airquality.project.entity.Device;

@Component
public interface DeviceRepository extends CrudRepository<Device, Long> {

	List<Device> findByStationId(Long stationId);
}
