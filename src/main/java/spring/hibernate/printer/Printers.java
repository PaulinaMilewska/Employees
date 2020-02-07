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
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
//    @JoinColumn(name = "EMPLOYEE_ID", nullable = false, referencedColumnName = "ID")
//    @JoinTable(name = "Employees-printers")
//    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")
//    @JoinColumn(name = "PRINTER_ID", referencedColumnName = "ID")
//    @ManyToMany(mappedBy = "employeesSet", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinTable(name = "Printer_Employees", joinColumns = @JoinColumn(name = "ID"), inverseJoinColumns = @JoinColumn(name = "ID"))
    @JoinTable(name = "Printer_Employees",
            joinColumns = {@JoinColumn(name = "printerId", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "employeeId", referencedColumnName = "ID")})
    @ToString.Exclude
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    public Set<Employees> employeesSet;

    public Printers() {
    }

}
