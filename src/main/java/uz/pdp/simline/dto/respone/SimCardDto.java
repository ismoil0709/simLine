package uz.pdp.simline.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.entity.Balance;
import uz.pdp.simline.entity.SimCard;
import uz.pdp.simline.util.annotations.Number;
import uz.pdp.simline.util.annotations.PhoneNumber;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class SimCardDto {
    private UUID id;
    @PhoneNumber
    private String number;
    @Number
    private Double price;
    private Boolean isActive;
    private Balance balance;
    private PlanDto plan;
    public SimCardDto(SimCard simCard) {
        this.id = simCard.getId();
        this.number = simCard.getNumber();
        this.price = simCard.getPrice();
        this.isActive = simCard.getIsActive();
        this.balance = simCard.getBalance();
        this.plan = new PlanDto(simCard.getPlan());
    }
}
