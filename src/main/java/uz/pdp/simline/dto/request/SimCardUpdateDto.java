package uz.pdp.simline.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.util.annotations.Number;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class SimCardUpdateDto {
    private UUID id;
    @Number
    private Double price;
    private Boolean isActive;
}