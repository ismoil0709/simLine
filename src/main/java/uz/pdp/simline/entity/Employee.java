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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Employee extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String phoneNumber;
    private String email;
    private String password;
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
}
