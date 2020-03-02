package airquality.project.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SunsetSunriseWrapperDTO {
	
	@SerializedName("results")
	@Expose
	private SunsetSunriseDTO results;
	@SerializedName("status")
	@Expose
	private String status;

	public SunsetSunriseDTO getResults() {
		return results;
	}

	public void setResults(SunsetSunriseDTO results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
