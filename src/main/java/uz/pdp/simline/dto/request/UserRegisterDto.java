package uz.pdp.simline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.util.annotations.Email;
import uz.pdp.simline.util.annotations.Password;
import uz.pdp.simline.util.annotations.PhoneNumber;
import uz.pdp.simline.util.annotations.Username;

@AllArgsConstructor
@Getter
public class UserRegisterDto {
    @Username
    private String username;
    @Email
    private String email;
    @PhoneNumber
    private String phoneNumber;
    @Password
    private String password;
}