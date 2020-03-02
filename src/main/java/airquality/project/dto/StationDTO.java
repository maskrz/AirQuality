package airquality.project.dto;

import airquality.project.statics.StationType;

public class StationDTO {

	private Long id;
	private String name;
	private StationType type;
	private Boolean active;

	public StationDTO() {
	}

	public StationDTO(Long id, String name, StationType type, Boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
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

	public StationType getType() {
		return type;
	}

	public void setType(StationType type) {
		this.type = type;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "StationDTO [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
}
