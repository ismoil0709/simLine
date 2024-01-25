package uz.pdp.simline.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String gender;
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