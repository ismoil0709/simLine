package uz.pdp.simline.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uz.pdp.simline.dto.respone.PassportDetailDto;
import uz.pdp.simline.dto.respone.UserDto;
import uz.pdp.simline.entity.PassportDetail;
import uz.pdp.simline.entity.User;
import uz.pdp.simline.repository.RoleRepository;
import uz.pdp.simline.repository.UserRepository;
import uz.pdp.simline.security.jwt.JwtTokenProvider;
import uz.pdp.simline.service.EmailService;
import uz.pdp.simline.service.PassportDetailsService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private EmailService emailService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PassportDetailsService passportDetailsService;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_ReturnsListOfUserDto() {
        List<User> userList = List.of(new User(), new User());

        when(userRepository.findAll()).thenReturn(userList);

        List<UserDto> userDtoList = userService.getAll();

        assertNotNull(userDtoList);
        assertEquals(userList.size(), userDtoList.size());
    }

    @Test
    void getByPhoneNumber_ValidPhoneNumber_ReturnsUserDto() {
        String phoneNumber = "1234567890";

        when(userRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.of(new User()));

        UserDto userDto = userService.getByPhoneNumber(phoneNumber);

        assertNotNull(userDto);
    }

    @Test
    void getByUsername_ValidUsername_ReturnsUserDto() {
        String username = "testUser";

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(new User()));

        UserDto userDto = userService.getByUsername(username);

        assertNotNull(userDto);
    }

    @Test
    void getByEmail_ValidEmail_ReturnsUserDto() {
        String email = "test@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        UserDto userDto = userService.getByEmail(email);

        assertNotNull(userDto);
    }

    @Test
    void getAllByGender_ValidGender_ReturnsListOfUserDto() {
        String gender = "MALE";

        List<User> userList = List.of(new User(), new User());

        when(userRepository.findAllByGender(gender)).thenReturn(userList);

        List<UserDto> userDtoList = userService.getAllByGender(gender);

        assertNotNull(userDtoList);
        assertEquals(userList.size(), userDtoList.size());
    }

    @Test
    void getAllByAddress_ValidAddress_ReturnsListOfUserDto() {
        String address = "123 Street, City";

        List<User> userList = List.of(new User(), new User());

        when(userRepository.findAllByAddress(address)).thenReturn(userList);

        List<UserDto> userDtoList = userService.getAllByAddress(address);

        assertNotNull(userDtoList);
        assertEquals(userList.size(), userDtoList.size());
    }

    @Test
    void getAllByRole_ValidRole_ReturnsListOfUserDto() {
        String role = "ROLE_USER";

        List<User> userList = List.of(new User(), new User());

        when(userRepository.findAllByRole(role)).thenReturn(userList);

        List<UserDto> userDtoList = userService.getAllByRole(role);

        assertNotNull(userDtoList);
        assertEquals(userList.size(), userDtoList.size());
    }

    @Test
    void getBySimCard_ValidSimCardNumber_ReturnsUserDto() {
        String simCardNumber = "1234567890";

        when(userRepository.findBySimCard(simCardNumber)).thenReturn(Optional.of(new User()));

        UserDto userDto = userService.getBySimCard(simCardNumber);

        assertNotNull(userDto);
    }

    @Test
    void getByPassportId_ValidPassportId_ReturnsUserDto() {
        String passportId = "ABC123";

        when(userRepository.findByPassportId(passportId)).thenReturn(Optional.of(new User()));

        UserDto userDto = userService.getByPassportId(passportId);

        assertNotNull(userDto);
    }

    @Test
    void getBalanceByUserId_ValidUserId_ReturnsBalance() {
        UUID userId = UUID.randomUUID();

        User user = new User();
        user.setBalance(100.0);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Double balance = userService.getBalanceByUserId(userId);

        assertNotNull(balance);
        assertEquals(100.0, balance);
    }

    @Test
    void addPassportDetailsToUser_ValidPassportDetailDtoAndUserId_ReturnsUserDtoWithPassportDetails() {
        UUID userId = UUID.randomUUID();

        PassportDetailDto passportDetailDto = new PassportDetailDto(UUID.randomUUID(),
                "John",
                "Doe",
                LocalDate.of(1990, 1, 1),
                "ABC123");

        PassportDetail passportDetail = new PassportDetail();
        passportDetail.setId(passportDetailDto.getId());
        passportDetail.setName(passportDetailDto.getName());
        passportDetail.setSurname(passportDetailDto.getSurname());
        passportDetail.setBirthDate(passportDetailDto.getBirthDate());
        passportDetail.setPassportId(passportDetailDto.getPassportId());

        when(passportDetailsService.save(any(PassportDetailDto.class))).thenAnswer(invocation -> {
            PassportDetailDto dto = invocation.getArgument(0);
            return new PassportDetail(
                    dto.getId(),
                    dto.getName(),
                    dto.getSurname(),
                    dto.getBirthDate(),
                    dto.getPassportId()
            );
        });
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));

        UserDto userDto = userService.addPassportDetailsToUser(passportDetailDto, userId);

        assertNotNull(userDto);
        assertNotNull(userDto.getPassportDetail());
    }

    @Test
    void verify_ValidToken_ReturnsTrue() {
        String token = "validToken";

        User user = new User();
        user.setUsername("testUser");

        when(jwtTokenProvider.parseAllClaims(token)).thenReturn(null);
        when(jwtTokenProvider.isValid(token)).thenReturn(true);
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        boolean result = userService.verify(token);

        assertTrue(result);
    }
}
