package airquality.project.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import airquality.project.entity.Measurement;

@Component
public interface MeasurementRepository extends CrudRepository<Measurement, Long> {

	@Query("SELECT m FROM Measurement m WHERE m.stationId = :stationId and m.measurementTimePeriod = 'SINGLE' order by m.measurementTimestamp desc")
	List<Measurement> getLatestMeasuredByStationId(@Param("stationId") Long stationId);

	@Query("SELECT m FROM Measurement m WHERE m.measurementTimestamp >= :fromTime AND m.measurementTimestamp <= :toTime AND m.stationId = :stationId AND m.measurementTimePeriod = :measurementTimePeriod order by m.id")
	List<Measurement> getForTimePeriodByStationIdAndTimePeriod(@Param("fromTime") Timestamp fromTime,
			@Param("toTime") Timestamp toTime, @Param("stationId") Long stationId,
			@Param("measurementTimePeriod") String measurementTimePeriod);

	@Query("SELECT m FROM Measurement m WHERE m.measurementTimestamp >= :fromTime AND m.measurementTimestamp <= :toTime AND m.stationId = :stationId AND m.measurementTimePeriod = :measurementTimePeriod AND m.valueType = :valueType order by m.id")
	List<Measurement> getForTimePeriodByStationIdAndTimePeriodAndValueType(@Param("fromTime") Timestamp fromTime,
			@Param("toTime") Timestamp toTime, @Param("measurementTimePeriod") String measurementTimePeriod,
			@Param("stationId") Long stationId, @Param("valueType") String valueType);
}
