package swt6.telemetry.jpa.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("M")

public class Metric extends TelemetryData {
    private double data;

    public Metric(String name, ApplicationInstance applicationInstance, double data) {
        super(name, applicationInstance);
        this.data = data;
    }
}
