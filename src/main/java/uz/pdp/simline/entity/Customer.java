package uz.pdp.simline.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Customer extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<SimCard> simCards;
    @OneToOne(cascade = CascadeType.ALL)
    private PassportDetail passportDetail;
    @Builder
    public Customer(UUID id,String username, String email, String password, String phoneNumber, List<SimCard> simCards, PassportDetail passportDetail) {
        super(username, email, password, phoneNumber);
        this.simCards = simCards;
        this.passportDetail = passportDetail;
        this.id = id;
    }
}