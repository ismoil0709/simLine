package uz.pdp.simline.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Employee extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String gender;
    private String address;
    private Double salary;
    private boolean active;
    @OneToOne(cascade = CascadeType.ALL)
    private PassportDetail passportDetail;
    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Role> employeeRoles;
    private String position;

    @Builder
    public Employee(String username, String email, String password, String phoneNumber, UUID id, String gender, String address, Double salary, boolean active, PassportDetail passportDetail, List<Role> employeeRoles, String position) {
        super(username, email, password, phoneNumber);
        this.id = id;
        this.gender = gender;
        this.address = address;
        this.salary = salary;
        this.active = active;
        this.passportDetail = passportDetail;
        this.employeeRoles = employeeRoles;
        this.position = position;
    }
}
