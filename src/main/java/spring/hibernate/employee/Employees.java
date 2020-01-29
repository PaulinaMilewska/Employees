package spring.hibernate.employee;

import lombok.*;
import org.springframework.format.Printer;
import org.springframework.format.annotation.DateTimeFormat;
import spring.hibernate.TypeObject;

import spring.hibernate.car.Cars;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Employees")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Employees implements TypeObject {

//    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, orphanRemoval = true)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private List<Cars> cars;


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
//
    //todo : make it work
//    @ManyToMany (cascade = CascadeType.ALL)
//    @JoinTable(name = "Printer", joinColumns = {@JoinColumn(referencedColumnName="printer_ID")})


//    @OneToMany(mappedBy = "employees", orphanRemoval = true, fetch = FetchType.EAGER)
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private Set<Phones> phones;
//    @OneToMany(mappedBy = "employees", orphanRemoval = true, fetch = FetchType.EAGER)
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private Set<Printer> printers;
//    public Employees(){}


    public Employees() {
    }

}
