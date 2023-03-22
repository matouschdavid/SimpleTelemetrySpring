package swt6.telemetry.api.dtos;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class IncidentDto {
    private Long id;
    private DetectorDto detector;

    private LocalDateTime timeStamp;

    private double value;
}
