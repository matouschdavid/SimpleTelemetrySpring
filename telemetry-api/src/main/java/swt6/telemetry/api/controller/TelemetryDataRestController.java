package swt6.telemetry.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swt6.telemetry.api.exceptions.FindByIdException;
import swt6.telemetry.jpa.domain.Application;
import swt6.telemetry.jpa.logic.interfaces.ApplicationInstanceService;
import swt6.telemetry.jpa.logic.interfaces.ApplicationService;
import swt6.telemetry.jpa.logic.interfaces.TelemetryDataService;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("telemetry")
public class TelemetryDataRestController {
    private final TelemetryDataService telemetryDataService;
    private final ApplicationService applicationService;
    private final ApplicationInstanceService applicationInstanceService;

    @Autowired
    public TelemetryDataRestController(TelemetryDataService telemetryDataService, ApplicationService applicationService, ApplicationInstanceService applicationInstanceService) {
        this.telemetryDataService = telemetryDataService;
        this.applicationService = applicationService;
        this.applicationInstanceService = applicationInstanceService;
    }

    @DeleteMapping("application/{id}")
    public void deleteOfApplicationAllTill(@PathVariable Long id, @RequestParam LocalDateTime end) {
        var application = applicationService.findById(id);
        if(application.isEmpty()){
            throw new FindByIdException(Application.class, id);
        }
        telemetryDataService.deleteAllTill(application.get(), end);
    }
    @DeleteMapping("applicationInstance/{id}")
    public void deleteOfInstanceAllTill(@PathVariable Long id, @RequestParam LocalDateTime end) {
        var applicationInstance = applicationInstanceService.findById(id);
        if(applicationInstance.isEmpty()){
            throw new FindByIdException(Application.class, id);
        }
        telemetryDataService.deleteAllTill(applicationInstance.get(), end);
    }

    @GetMapping("avg/application/{id}")
    public double getAverageValueOfApplicationTill(@PathVariable Long id,@RequestParam LocalDateTime end,@RequestParam String metricName){
        var application = applicationService.findById(id);
        if(application.isEmpty()){
            throw new FindByIdException(Application.class, id);
        }
        return telemetryDataService.getAverageValueTill(application.get(), end, metricName);
    }
    @GetMapping("avg/applicationInstance/{id}")
    public double getAverageValueOfInstanceTill(@PathVariable Long id,@RequestParam LocalDateTime end,@RequestParam String metricName){
        var applicationInstance = applicationInstanceService.findById(id);
        if(applicationInstance.isEmpty()){
            throw new FindByIdException(Application.class, id);
        }
        return telemetryDataService.getAverageValueTill(applicationInstance.get(), end, metricName);
    }

    @PostMapping("{id}")
    public void generateTill(@PathVariable Long id, @RequestParam LocalDateTime end, @RequestParam String metricName){
        var applicationInstance = applicationInstanceService.findById(id);
        if(applicationInstance.isEmpty()){
            throw new FindByIdException(Application.class, id);
        }
        telemetryDataService.generateTill(end, applicationInstance.get(), metricName);
    }
}
