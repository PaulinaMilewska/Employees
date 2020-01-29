package spring.hibernate.printer;

import lombok.*;
import spring.hibernate.TypeObject;
import spring.hibernate.employee.Employees;

import javax.persistence.*;

@Entity
@Table(name = "Printer")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Printers implements TypeObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false, referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
//    @Getter @Setter
    @NonNull
    public Employees employees;

    @Column(name = "model")
    @Getter
    @Setter
    @NonNull
    private String model;

    @Column(name = "producer")
    @Getter
    @Setter
    @NonNull
    private String producer;


    public Printers() {
    }

}
