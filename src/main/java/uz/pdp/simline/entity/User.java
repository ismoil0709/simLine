package uz.pdp.simline.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Pattern;
import uz.pdp.simline.util.annotations.*;
import uz.pdp.simline.util.annotations.Number;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "users")
public class User extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Username
    private String username;
    @Email
    private String email;
    @Password
    private String password;
    @PhoneNumber
    private String phoneNumber;
    @Gender
    private String gender;
    @Number
    private Double balance;
    private String address;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @ToString.Exclude
    private List<Role> roles;
    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<SimCard> simCards;
    @OneToOne(cascade = CascadeType.ALL)
    private PassportDetail passportDetail;
}