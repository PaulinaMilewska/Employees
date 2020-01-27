//package spring.hibernate.car;
//
//import lombok.*;
//import org.springframework.format.annotation.DateTimeFormat;
//import spring.hibernate.TypeObject;
//import spring.hibernate.employee.Employees;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "Car")
//@Data
//@RequiredArgsConstructor
//@AllArgsConstructor
//public class Car implements TypeObject {
//
//    public Car() {
//    }
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "employee_id")
//    @NonNull
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @Getter @Setter
//    private Employees employee;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID")
//    private Long id;
//
//    @Column(name = "brand")
//    @NonNull
//    private String brand;
//
//    @Column(name = "model")
//    @NonNull
//    private String model;
//
//    @Column(name = "max_speed")
//    @NonNull
//    private int maxSpeed;
//
//    @Column(name = "registration_date")
//    @NonNull
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date registrationDate;
//
//    public Car(String vw, String polo, int i, Date date) {
//    }
//
//    @Override
//    public String toString() {
//        return "Car{" +
//                "id=" + id +
//                ", brand='" + brand + '\'' +
//                ", model='" + model + '\'' +
//                ", maxSpeed=" + maxSpeed +
//                ", registrationDate=" + registrationDate +
//                '}';
//    }
//}
//
