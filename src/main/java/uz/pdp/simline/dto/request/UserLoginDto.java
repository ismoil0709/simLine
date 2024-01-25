package uz.pdp.simline.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.util.annotations.Password;
import uz.pdp.simline.util.annotations.Username;

@AllArgsConstructor
@Getter
public class UserLoginDto {
    @Username
    private String username;
    @Password
    private String password;
}
