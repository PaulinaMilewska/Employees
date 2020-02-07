package spring.hibernate.employee;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import spring.hibernate.TypeObject;

import spring.hibernate.car.Cars;
import spring.hibernate.printer.Printers;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Employees")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Employees implements TypeObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "LastName")
    @NonNull
    @Getter
    @Setter
    private String lastName;
    @Column(name = "FirstName")
    @NonNull
    @Getter
    @Setter
    private String firstName;
    @Column(name = "Address")
    @NonNull
    private String address;
    @Column(name = "City")
    @NonNull
    private String city;
    @Column(name = "Salary")
    @NonNull
    private int salary;
    @Column(name = "Age")
    @NonNull
    private int age;
    @Column(name = "StartJobDate")
    @NonNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startJobDate;
    @Column(name = "Benefit")
    @NonNull
    private int benefit;
    @Column(name = "Email")
    private String email;

    @OneToMany(mappedBy = "employees", orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Cars> cars;

//    //todo : make it work

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Employees_printers",
            joinColumns = @JoinColumn(name = "EMPLOYEE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRINTER_ID"))
    @ToString.Exclude
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private Set<Printers> printersSet;


    public Employees() {
    }

}
