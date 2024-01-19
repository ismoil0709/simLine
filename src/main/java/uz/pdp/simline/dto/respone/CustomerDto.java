package uz.pdp.simline.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.entity.PassportDetail;

@AllArgsConstructor
@Getter
public class CustomerDto {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private PassportDetail passportDetail;
}
