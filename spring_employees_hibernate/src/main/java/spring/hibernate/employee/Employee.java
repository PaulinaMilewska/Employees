package spring.hibernate.employee;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import spring.hibernate.car.Car;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employees")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Employee {

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL
            , fetch = FetchType.EAGER
    )
    private List<Car> cars;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "address")
    @NonNull
    private String address;

    @Column(name = "city")
    @NonNull
    private String city;

    @Column(name = "salary")
    @NonNull
    private int salary;

    @Column(name = "age")
    @NonNull
    private int age;

    @Column(name = "start_job_date")
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startJobDate;

    @Column(name = "benefit")
    @NonNull
    private int benefit;

    @Column(name = "Email")
    private String email;

    public Employee(){}

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", startJobDate=" + startJobDate +
                ", benefit=" + benefit +
                ", email='" + email + '\'' +
                '}';
    }
}
