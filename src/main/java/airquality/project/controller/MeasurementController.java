package airquality.project.controller;

import java.util.List;

import airquality.project.dto.SearchParameters;
import airquality.project.dto.SingleLineSearchParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import airquality.project.dto.MeasurementDTO;
import airquality.project.service.MeasurementService;

@RequestMapping("/measurements")
@CrossOrigin(origins = { "http://192.168.1.13:3000", "http://192.168.1.13:4200", "http://192.168.1.13:8080",
		"http://localhost:3000", "http://99c8c363.ngrok.io:8080", "http://99c8c363.ngrok.io:3000", "http://99c8c363.ngrok.io" })
@RestController
public class MeasurementController {

	@Autowired
	private MeasurementService measurementService;

	@RequestMapping(value = "/getCurrent/{stationId}", method = RequestMethod.GET)
	public MeasurementDTO getCurrent(@PathVariable Long stationId) {
		MeasurementDTO dto = measurementService.getLastMeasured(stationId);
		return dto;
	}

	@RequestMapping(value = "/measureAndSave", method = RequestMethod.GET)
	public MeasurementDTO measureAndSave() {
		List<MeasurementDTO> resultList = measurementService.getAllCurrentMeasurements();
		return resultList.size() == 0? new MeasurementDTO() : resultList.get(0);
	}

	@RequestMapping(value = "/getCustom", method = RequestMethod.POST)
	public @ResponseBody List<MeasurementDTO> getCustom(@RequestBody SearchParameters searchParameters) {
		return measurementService.getCustom(searchParameters);
	}

	@RequestMapping(value = "/getForSingleLine", method = RequestMethod.POST)
	public @ResponseBody List<MeasurementDTO> getForSingleLine(@RequestBody SingleLineSearchParameters singleLineSearchParameters) {
		return measurementService.getForSingleLine(singleLineSearchParameters);
	}

	@RequestMapping(value = "/yesterdayData", method = RequestMethod.GET)
	public @ResponseBody List<MeasurementDTO> getYesterdayData() {
		return measurementService.getYesterdayData();
	}

	@RequestMapping(value = "/last24Outdoor", method = RequestMethod.GET)
	public @ResponseBody List<MeasurementDTO> last24Outdoor() {
		return measurementService.last24Outdoor();
	}

	@RequestMapping(value = "/last24Indoor", method = RequestMethod.GET)
	public @ResponseBody List<MeasurementDTO> last24Indoor() {
		return measurementService.last24Indoor();
	}
}
