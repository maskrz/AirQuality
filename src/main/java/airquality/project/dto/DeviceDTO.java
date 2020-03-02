package airquality.project.dto;

import airquality.project.statics.DeviceType;

public class DeviceDTO {

	private Long id;
	private String name;
	private Long stationId;
	private DeviceType type;
	private String serviceUrl;
	private Boolean active;

	public DeviceDTO() {
	}

	public DeviceDTO(Long id, String name, Long stationId, DeviceType type, String serviceUrl, Boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.stationId = stationId;
		this.type = type;
		this.serviceUrl = serviceUrl;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public DeviceType getType() {
		return type;
	}

	public void setType(DeviceType type) {
		this.type = type;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "DeviceDTO [id=" + id + ", name=" + name + ", stationId=" + stationId + ", type=" + type
				+ ", serviceUrl=" + serviceUrl + "]";
	}

}
