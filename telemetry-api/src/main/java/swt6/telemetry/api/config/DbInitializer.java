package swt6.telemetry.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import swt6.telemetry.jpa.domain.Application;
import swt6.telemetry.jpa.domain.ApplicationInstance;
import swt6.telemetry.jpa.domain.Detector;
import swt6.telemetry.jpa.logic.interfaces.ApplicationService;
import swt6.telemetry.jpa.logic.interfaces.DetectorService;

@Component
@Profile("dev")
public class DbInitializer implements CommandLineRunner {

    private final ApplicationService applicationService;
    private final DetectorService detectorService;

    @Autowired
    public DbInitializer(ApplicationService applicationService,
                         DetectorService detectorService) {
        this.applicationService = applicationService;
        this.detectorService = detectorService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Init Unit of works");


        System.out.println("Init applications");
        Application a1 = new Application("Visual Studio 2022", ".NET", "Windows");
        Application a2 = new Application("IntelliJ", "Java", "Windows", "Mac", "Linux");

        System.out.println("Save applications");
        a1 = applicationService.save(a1);
        a2 = applicationService.save(a2);

        System.out.println("Start instances");
        ApplicationInstance a1Instance = applicationService.startApplication(a1);
        ApplicationInstance a2Instance = applicationService.startApplication(a2);
        ApplicationInstance a2Instance2 = applicationService.startApplication(a2);

        System.out.println("Init detectors");
        Detector cpuUsageA1 = new Detector("Cpu Usage of Application 1 Detector",
                "Cpu Usage", a1Instance,
                1000L, -1, 80);
        Detector cpuUsageA2 = new Detector("Cpu Usage of Application 2 Detector",
                "Cpu Usage", a2Instance,
                1000L, -1, 80);

        System.out.println("Save detectors");
        cpuUsageA1 = detectorService.saveAndStart(cpuUsageA1);
        cpuUsageA2 = detectorService.saveAndStart(cpuUsageA2);
    }
}
