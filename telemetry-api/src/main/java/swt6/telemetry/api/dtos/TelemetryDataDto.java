package swt6.telemetry.api.dtos;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TelemetryDataDto {
    private Long id;
    private LocalDateTime timeStamp;
    private String name;
    private ApplicationInstanceDto applicationInstance;
}
