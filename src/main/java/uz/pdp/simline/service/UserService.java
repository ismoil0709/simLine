package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.UserLoginDto;
import uz.pdp.simline.dto.request.UserUpdateDto;
import uz.pdp.simline.dto.respone.JwtDto;
import uz.pdp.simline.dto.request.UserRegisterDto;
import uz.pdp.simline.dto.respone.UserDto;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {
    JwtDto register(UserRegisterDto userRegisterDto);
    JwtDto login(UserLoginDto userLoginDto);
    UserDto update(UserUpdateDto customerDto);
    void delete(UUID id);
    UserDto getById(UUID id);
    List<UserDto> getAll();
    UserDto getByPhoneNumber(String phoneNumber);
    UserDto getByUsername(String username);
    UserDto getByEmail(String email);
    List<UserDto> getAllByGender(String gender);
    List<UserDto> getAllByAddress(String address);
    List<UserDto> getAllByRole(String role);
    UserDto getBySimCard(String simCardNumber);
    UserDto getByPassportId(String passportId);
    boolean verify(String token);
}
