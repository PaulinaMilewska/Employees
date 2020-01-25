package spring.hibernate.employee;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import spring.hibernate.car.Car;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Employees")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Employees {

    @OneToMany(mappedBy = "employee"
            , fetch = FetchType.EAGER
    )
    private List<Car> cars;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
//    @Getter @Setter
    private int id;

    @Column(name = "LastName")
    @NonNull
    private String lastName;

    @Column(name = "FirstName")
    @NonNull
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
    @Getter @Setter
    private String email;

    public Employees(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getStartJobDate() {
        return startJobDate;
    }

    public void setStartJobDate(Date startJobDate) {
        this.startJobDate = startJobDate;
    }

    public int getBenefit() {
        return benefit;
    }

    public void setBenefit(int benefit) {
        this.benefit = benefit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
