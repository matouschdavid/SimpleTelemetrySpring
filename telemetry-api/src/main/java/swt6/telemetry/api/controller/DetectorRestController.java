package swt6.telemetry.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swt6.telemetry.jpa.domain.Detector;
import swt6.telemetry.jpa.logic.interfaces.DetectorService;

@RestController
@Slf4j
@RequestMapping("detector")
public class DetectorRestController {
    private final DetectorService detectorService;

    @Autowired
    public DetectorRestController(DetectorService detectorService) {
        this.detectorService = detectorService;
    }

    @PostMapping
    public Detector saveAndStart(@RequestBody Detector detector){
        return detectorService.saveAndStart(detector);
    }

    @PostMapping("start")
    public void startAll(){
        detectorService.startAll();
    }
}
