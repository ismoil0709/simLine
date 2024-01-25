package uz.pdp.simline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.simline.util.annotations.Number;
import uz.pdp.simline.util.annotations.PhoneNumber;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class SimCard extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @PhoneNumber
    private String number;
    private Balance balance;
    @Number
    private Double price;
    @Builder.Default
    private Boolean isActive = false;
    @ManyToOne(cascade = CascadeType.ALL)
    private Plan plan;
}