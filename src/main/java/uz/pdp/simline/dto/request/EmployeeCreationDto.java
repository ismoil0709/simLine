package uz.pdp.simline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.entity.PassportDetail;

@AllArgsConstructor
@Getter
public class EmployeeCreationDto {
    private String phoneNumber;
    private String email;
    private String gender;
    private String password;
    private String address;
    private PassportDetail passportDetail;
}
