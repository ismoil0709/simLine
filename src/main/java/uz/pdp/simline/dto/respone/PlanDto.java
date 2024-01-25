package uz.pdp.simline.dto.respone;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.entity.Plan;
import uz.pdp.simline.util.annotations.Name;
import uz.pdp.simline.util.annotations.Number;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class PlanDto {
    private UUID id;
    private String name;
    @Number
    private Long expiry;
    @Number
    private Long mb;
    @Number
    private Long sms;
    @Number
    private Long minute;
    @Number
    private Double price;
    public PlanDto(Plan plan){
        this.id = plan.getId();
        this.name = plan.getName();
        this.expiry = plan.getExpiry();
        this.mb = plan.getMb();
        this.sms = plan.getSms();
        this.minute = plan.getMinute();
        this.price = plan.getPrice();

    }
    public static Plan castToPlan(PlanDto planDto){
        return Plan.builder()
                .id(planDto.getId())
                .name(planDto.getName())
                .expiry(planDto.getExpiry())
                .mb(planDto.getMb())
                .sms(planDto.getSms())
                .minute(planDto.getMinute())
                .price(planDto.getPrice())
                .build();
    }
}
