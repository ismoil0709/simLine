package uz.pdp.simline.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.entity.Customer;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class CustomerDto {
    private UUID id;
    private String username;
    private String email;
    private String phoneNumber;
    private PassportDetailDto passportDetail;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.username = customer.getUsername();
        this.email = customer.getEmail();
        this.phoneNumber = customer.getPhoneNumber();
//        this.passportDetail = new PassportDetailDto(
//                customer.getPassportDetail().getName(),
//                customer.getPassportDetail().getSurname(),
//                customer.getPassportDetail().getBirthDate(),
//                customer.getPassportDetail().getPassportId()
//        );
    }

    @AllArgsConstructor
    @Getter
    public static class PassportDetailDto {
        private String name;
        private String surname;
        private LocalDate birthDate;
        private String passportId;
    }
}
