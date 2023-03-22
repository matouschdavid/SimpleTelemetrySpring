package swt6.telemetry.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApplicationInstance {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @ToString.Exclude
    private Application application;

    public ApplicationInstance(Application application) {
        this.application = application;
    }
}
