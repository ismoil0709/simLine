package uz.pdp.simline.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class UssdDto {
    private UUID id;
    private String code;
    private String description;
}
