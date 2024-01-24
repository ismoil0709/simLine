package uz.pdp.simline.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.entity.Role;
import uz.pdp.simline.entity.User;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class UserDto {
    private UUID id;
    private String username;
    private String email;
    private String phoneNumber;
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
        this.simCards = user.getSimCards().stream().map(SimCardDto::new).toList();
        if (user.getPassportDetail() != null)
            this.passportDetail = new PassportDetailDto(user.getPassportDetail());
    }

}
