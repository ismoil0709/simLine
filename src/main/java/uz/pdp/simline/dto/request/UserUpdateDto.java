package uz.pdp.simline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.util.annotations.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class UserUpdateDto {
    private UUID id;
    @Username
    private String username;
    @Email
    private String email;
    @PhoneNumber
    private String phoneNumber;
    @Password
    private String password;
    @Gender
    private String gender;
    private String address;
}