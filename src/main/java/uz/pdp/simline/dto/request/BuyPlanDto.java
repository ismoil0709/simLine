package uz.pdp.simline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class BuyPlanDto {
    private UUID planId;
    private UUID simCardId;
}
