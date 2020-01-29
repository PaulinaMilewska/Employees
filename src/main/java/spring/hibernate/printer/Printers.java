package spring.hibernate.printer;

import lombok.*;
import spring.hibernate.CarEmployeeDao;
import spring.hibernate.TypeObject;
import spring.hibernate.employee.Employees;

import javax.persistence.*;

@Entity
@Table(name = "Printer")
@ToString
@RequiredArgsConstructor
public class Printers implements TypeObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "model")
    @Getter
    @Setter
    @NonNull
    private String model;


    @Column(name = "producer")
    @Getter @Setter
    @NonNull
    private String producer;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false, referencedColumnName = "ID")
    @Getter @Setter
    @NonNull
    public Employees employees;

    public Printers(){}

}
