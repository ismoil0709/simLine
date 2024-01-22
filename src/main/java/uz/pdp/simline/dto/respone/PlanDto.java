package uz.pdp.simline.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class PlanDto {
    private UUID id;
    private String name;
    private Long expiry;
    private Double mb;
    private Long sms;
    private Long minute;
    private Double price;
}
