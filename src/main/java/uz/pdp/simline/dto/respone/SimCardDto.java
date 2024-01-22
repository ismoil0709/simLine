package uz.pdp.simline.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class SimCardDto {
    private UUID id;
    private String number;
    private Double price;
    private Boolean isActive = false;
}
