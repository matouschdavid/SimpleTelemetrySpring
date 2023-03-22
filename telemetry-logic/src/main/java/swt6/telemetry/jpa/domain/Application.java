package swt6.telemetry.jpa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Application {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String plattform;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Application_Os_Compatibility", joinColumns = @JoinColumn(name = "Application_Id"))
    @ToString.Exclude
    @Setter(AccessLevel.PRIVATE)
    private Set<String> compatibleOs = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application", orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @ToString.Exclude
    @Setter(AccessLevel.PRIVATE)
    private List<ApplicationInstance> instances = new ArrayList<>();

    public Application(String name, String plattform, String... compatibleOs) {
        this.name = name;
        this.plattform = plattform;
        this.compatibleOs = new HashSet<>(Arrays.asList(compatibleOs));
    }
}
