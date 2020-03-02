package airquality.project.dto;

import java.time.LocalDateTime;

public class NightDayTimestampDTO {

	private LocalDateTime fromTime;

	private LocalDateTime toTime;

	public NightDayTimestampDTO() {
	}

	public NightDayTimestampDTO(LocalDateTime fromTime, LocalDateTime toTime) {
		super();
		this.fromTime = fromTime;
		this.toTime = toTime;
	}

	public LocalDateTime getFromTime() {
		return fromTime;
	}

	public void setFromTime(LocalDateTime fromTime) {
		this.fromTime = fromTime;
	}

	public LocalDateTime getToTime() {
		return toTime;
	}

	public void setToTime(LocalDateTime toTime) {
		this.toTime = toTime;
	}
}
