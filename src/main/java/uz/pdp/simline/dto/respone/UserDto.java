package uz.pdp.simline.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.entity.Role;
import uz.pdp.simline.entity.User;
import uz.pdp.simline.util.annotations.Email;
import uz.pdp.simline.util.annotations.Gender;
import uz.pdp.simline.util.annotations.PhoneNumber;
import uz.pdp.simline.util.annotations.Username;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class UserDto {
    private UUID id;
    @Username
    private String username;
    @Email
    private String email;
    @PhoneNumber
    private String phoneNumber;
    @Gender
    private String gender;
    private String address;
    private List<Role> roles;
    private List<SimCardDto> simCards;
    private PassportDetailDto passportDetail;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.gender = user.getGender();
        this.address = user.getAddress();
        this.roles = user.getRoles();
        if (user.getSimCards() != null)
            this.simCards = user.getSimCards().stream().map(SimCardDto::new).toList();
        if (user.getPassportDetail() != null)
            this.passportDetail = new PassportDetailDto(user.getPassportDetail());
    }

}
