package spring.hibernate.printer;

import lombok.*;
import spring.hibernate.TypeObject;
import spring.hibernate.employee.Employees;

import javax.persistence.*;
import java.util.Set;

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

    // todo: check it
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Employees-printers")
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")
    @JoinColumn(name = "PRINTER_ID", referencedColumnName = "ID")
    @ToString.Exclude
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    public Set<Employees> employeesSet;

    public Printers() {
    }

}
