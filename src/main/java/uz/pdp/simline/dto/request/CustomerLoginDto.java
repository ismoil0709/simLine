package uz.pdp.simline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomerLoginDto {
    private String username;
    private String password;
}
