package airquality.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Device")
public class Device implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6825437459991653301L;

	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "STATION_ID")
	private Long stationId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SERVICE_URL")
	private String serviceUrl;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "ACTIVE")
	private Boolean active;

	public Device() {
	}

	public Device(Long id, Long stationId, String name, String serviceUrl, String type, Boolean active) {
		super();
		this.id = id;
		this.stationId = stationId;
		this.name = name;
		this.serviceUrl = serviceUrl;
		this.type = type;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
