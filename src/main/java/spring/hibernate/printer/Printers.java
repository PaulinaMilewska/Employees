package spring.hibernate.printer;

import lombok.*;
import spring.hibernate.CarEmployeeDao;
import spring.hibernate.TypeObject;
import spring.hibernate.employee.Employees;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Printer")
@ToString
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Printers implements TypeObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "model")
    @NonNull
    private String model;


    @Column(name = "producer")
    @NonNull
    private String producer;


    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "EMPLOYEE_ID", nullable = false, referencedColumnName = "ID")
//    public void addEmployee(Employees employee) {
//        if (employees == null) {
//            employees = new ArrayList<>();
//        } else {
//            employees.add(employee);
//        }
//    }

    //    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    //    @JoinTable(name = "employee_printer", joinColumns = @JoinColumn(name = "PRINTER_ID"), inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID"))
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false, referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NonNull
    private List<Employees> employees;


    public Printers() {
    }

}
