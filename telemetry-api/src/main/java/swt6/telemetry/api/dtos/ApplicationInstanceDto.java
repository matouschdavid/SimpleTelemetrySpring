package swt6.telemetry.api.dtos;
import lombok.Data;

@Data
public class ApplicationInstanceDto {
    private Long id;
    private ApplicationDto application;
}
