package swt6.telemetry.api.dtos;

import lombok.Data;
@Data
public class DetectorDto {
    private Long id;
    private String name;

    private String metricName;
    private ApplicationInstanceDto instance;

    private Long timeInterval;
    private double minBound;
    private double maxBound;
}
