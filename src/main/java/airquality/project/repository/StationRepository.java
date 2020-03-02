package airquality.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import airquality.project.entity.Station;

@Component
public interface StationRepository extends CrudRepository<Station, Long>{

}
