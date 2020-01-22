package spring.hibernate.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import spring.hibernate.employee.Employee;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cars")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Car {

    public Car() {
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_id")
    @NonNull
    private Employee employee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "brand")
    @NonNull
    private String brand;

    @Column(name = "model")
    @NonNull
    private String model;

    @Column(name = "max_speed")
    @NonNull
    private int maxSpeed;

    @Column(name = "registration_date")
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;

//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
