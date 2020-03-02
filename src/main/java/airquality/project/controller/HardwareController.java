package airquality.project.controller;

import airquality.project.dto.MeasurementDTO;
import airquality.project.dto.StationDTO;
import airquality.project.service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/hardware")
@CrossOrigin(origins = { "http://192.168.1.13:3000", "http://192.168.1.13:4200", "http://192.168.1.13:8080",
        "http://localhost:3000", "http://99c8c363.ngrok.io:8080", "http://99c8c363.ngrok.io:3000", "http://99c8c363.ngrok.io" })
@RestController
public class HardwareController {

    @Autowired
    private HardwareService hardwareService;

    @RequestMapping(value = "/stations/getAll", method = RequestMethod.GET)
    public List<StationDTO> getAllStations() {
        return hardwareService.getAllStations();
    }
}
