package uz.pdp.simline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.dto.respone.CustomerDto;
import uz.pdp.simline.dto.respone.SimCardDto;

import java.util.List;

@AllArgsConstructor
@Getter
public class CustomerCreationDto {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private List<SimCardDto> simCards;
    private CustomerDto.PassportDetailDto passportDetail;
}

