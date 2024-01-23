package uz.pdp.simline.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.entity.SimCard;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class SimCardDto {
    private UUID id;
    private String number;
    private Double price;
    private Boolean isActive;
    public SimCardDto(SimCard simCard) {
        this.id = simCard.getId();
        this.number = simCard.getNumber();
        this.price = simCard.getPrice();
        this.isActive = simCard.getIsActive();
    }
}
