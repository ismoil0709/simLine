package uz.pdp.simline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.entity.Plan;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class SimCardUpdateDto {
   private UUID id;
   private Double price;
   private Boolean isActive;
   private UUID planId;
}
