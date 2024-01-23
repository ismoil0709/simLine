package uz.pdp.simline.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public class Balance {
    private Double balance;
    private Double mb;
    private Long sms;
    private Long minute;
}
