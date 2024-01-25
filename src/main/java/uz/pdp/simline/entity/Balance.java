package uz.pdp.simline.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import uz.pdp.simline.util.annotations.Number;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public class Balance {
    @Number
    private Double balance;
    @Number
    private Double mb;
    @Number
    private Long sms;
    @Number
    private Long minute;
}
