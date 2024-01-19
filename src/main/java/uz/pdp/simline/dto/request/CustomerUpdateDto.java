package uz.pdp.simline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CustomerUpdateDto{
    private UUID id;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
}