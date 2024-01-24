package uz.pdp.simline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRegisterDto {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
}