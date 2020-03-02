package airquality.project.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import airquality.project.dto.DeviceDTO;
import airquality.project.dto.MeasurementDTO;
import airquality.project.dto.StationDTO;
import airquality.project.entity.Device;
import airquality.project.entity.Measurement;
import airquality.project.entity.Station;
import airquality.project.statics.DeviceType;
import airquality.project.statics.MeasurementTimePeriod;
import airquality.project.statics.MeasurementValueType;
import airquality.project.statics.StationType;

public class ConversionUtils {

	public static DeviceDTO entityToDTO(Device entity) {
		DeviceDTO dto = new DeviceDTO();
		copyProperties(entity, dto);
		dto.setType(DeviceType.valueOf(entity.getType()));
		return dto;
	}

	public static Device dtoToEntity(DeviceDTO dto) {
		Device entity = new Device();
		copyProperties(dto, entity);
		entity.setType(dto.getType().toString());
		return entity;
	}

	public static List<DeviceDTO> deviceIterableEntityToDTOList(Iterable<Device> entities) {
		List<Device> entitiesList = new ArrayList<>();
		entities.forEach(entitiesList::add);
		return deviceListEntityToDTOList(entitiesList);
	}

	public static List<DeviceDTO> deviceListEntityToDTOList(List<Device> entities) {
		return entities.stream().map(e -> entityToDTO(e)).collect(Collectors.toList());
	}

	public static StationDTO entityToDTO(Station entity) {
		StationDTO dto = new StationDTO();
		copyProperties(entity, dto);
		dto.setType(StationType.valueOf(entity.getType()));
		return dto;
	}

	public static Station dtoToEntity(StationDTO dto) {
		Station entity = new Station();
		copyProperties(dto, entity);
		entity.setType(dto.getType().toString());
		return entity;
	}

	public static List<StationDTO> stationIterableEntityToDTOList(Iterable<Station> entities) {
		List<Station> entitiesList = new ArrayList<>();
		entities.forEach(entitiesList::add);
		return stationListEntityToDTOList(entitiesList);
	}

	public static List<StationDTO> stationListEntityToDTOList(List<Station> entities) {
		return entities.stream().map(e -> entityToDTO(e)).collect(Collectors.toList());
	}

	public static MeasurementDTO entityToDTO(Measurement entity) {
		MeasurementDTO dto = new MeasurementDTO();
		copyProperties(entity, dto);
		dto.setMeasurementTimePeriod(MeasurementTimePeriod.valueOf(entity.getMeasurementTimePeriod()));
		dto.setValueType(MeasurementValueType.valueOf(entity.getValueType()));
		return dto;
	}

	public static Measurement dtoToEntity(MeasurementDTO dto) {
		Measurement entity = new Measurement();
		copyProperties(dto, entity);
		entity.setMeasurementTimePeriod(dto.getMeasurementTimePeriod().toString());
		entity.setValueType(dto.getValueType().toString());
		return entity;
	}

	public static List<MeasurementDTO> measurementIterableEntityToDTOList(Iterable<Measurement> entities) {
		List<Measurement> entitiesList = new ArrayList<>();
		entities.forEach(entitiesList::add);
		return measurementListEntityToDTOList(entitiesList);
	}

	public static List<MeasurementDTO> measurementListEntityToDTOList(List<Measurement> entities) {
		return entities.stream().map(e -> entityToDTO(e)).collect(Collectors.toList());
	}

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public static void copyNonNullProperties(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

	public static void copyProperties(Object src, Object target) {
		BeanUtils.copyProperties(src, target);
	}

	public static Double roundDouble(Double toRound) {
		return BigDecimal.valueOf(toRound).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
