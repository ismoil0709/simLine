package uz.pdp.simline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class UserUpdateDto {
    private UUID id;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String gender;
    private String address;
}