package uz.pdp.simline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class CustomerRegisterDto {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
}