package spring.hibernate.car;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import spring.hibernate.TypeObject;
import spring.hibernate.employee.Employees;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Cars")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Cars implements TypeObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false, referencedColumnName="ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
//    @Getter @Setter
    @NonNull
    public Employees employees;

    @Column(name = "Name")
    @NonNull
    private String name;

    @Column(name = "Model")
    @NonNull
    private String model;

    @Column(name = "RegistrationDate")
    @ToString.Exclude
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;

    public Cars() {}

}

