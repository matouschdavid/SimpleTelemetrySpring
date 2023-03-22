package swt6.telemetry.api.dtos;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ApplicationDto {
    private Long id;
    private String name;
    private String plattform;
    private Set<String> compatibleOs = new HashSet<>();
}
