package uz.pdp.simline.service.impl;

import io.jsonwebtoken.Claims;
import jdk.jshell.VarSnippet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.UserLoginDto;
import uz.pdp.simline.dto.request.UserRegisterDto;
import uz.pdp.simline.dto.request.UserUpdateDto;
import uz.pdp.simline.dto.respone.PassportDetailDto;
import uz.pdp.simline.dto.respone.UserDto;
import uz.pdp.simline.dto.respone.JwtDto;
import uz.pdp.simline.entity.PassportDetail;
import uz.pdp.simline.entity.Role;
import uz.pdp.simline.entity.User;
import uz.pdp.simline.exception.AlreadyExistsException;
import uz.pdp.simline.exception.InvalidArgumentException;
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.exception.NullOrEmptyException;
import uz.pdp.simline.repository.RoleRepository;
import uz.pdp.simline.repository.UserRepository;
import uz.pdp.simline.security.jwt.JwtTokenProvider;
import uz.pdp.simline.service.PassportDetailsService;
import uz.pdp.simline.service.UserService;
import uz.pdp.simline.service.EmailService;
import uz.pdp.simline.util.Validations;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;
    private final RoleRepository roleRepository;
    private final PassportDetailsService passportDetailsService;

    @Override
    public JwtDto register(UserRegisterDto userRegisterDto){
        if (userRegisterDto == null)
            throw new NullOrEmptyException("UserRegisterDto");
        if (Validations.isNullOrEmpty(userRegisterDto.getUsername()))
            throw new NullOrEmptyException("Username");
        if (Validations.isNullOrEmpty(userRegisterDto.getPassword()))
            throw new NullOrEmptyException("Password");
        if (userRepository.findByUsername(userRegisterDto.getUsername()).isPresent())
            throw new AlreadyExistsException("Username");
        if (userRegisterDto.getEmail() != null && userRepository.findByEmail(userRegisterDto.getEmail()).isPresent())
            throw new AlreadyExistsException("Email");
        if (userRegisterDto.getPhoneNumber() != null && userRepository.findByPhoneNumber(userRegisterDto.getPhoneNumber()).isPresent())
            throw new AlreadyExistsException("Phone number");

        if (userRegisterDto.getEmail() != null)
            emailService.sendEmailVerificationMessage(userRegisterDto.getUsername(),userRegisterDto.getEmail());
        User user = User.builder()
                .username(userRegisterDto.getUsername())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .phoneNumber(userRegisterDto.getPhoneNumber())
                .roles(List.of(roleRepository.findByRole("ROLE_USER").isEmpty() ? new Role(null, "ROLE_USER", "Role for users") : roleRepository.findByRole("ROLE_USER").get()))
                .address("UNKNOWN")
                .gender("UNKNOWN")
                .balance(0D)
                .build();
        return new JwtDto(jwtTokenProvider.generateToken(userRepository.save(
                user
        )));
    }

    @Override
    public JwtDto login(UserLoginDto userLoginDto) {
        if (userLoginDto == null)
            throw new NullOrEmptyException("UserLoginDto");
        if (Validations.isNullOrEmpty(userLoginDto.getUsername()))
            throw new NullOrEmptyException("Username");
        if (Validations.isNullOrEmpty(userLoginDto.getPassword()))
            throw new NullOrEmptyException("Password");
        User user = userRepository.findByUsername(userLoginDto.getUsername())
                .orElseThrow(() -> new NotFoundException("User"));
        if (passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            return new JwtDto(jwtTokenProvider.generateToken(user));
        }
        throw new InvalidArgumentException("password");
    }

    @Override
    public UserDto update(UserUpdateDto userUpdateDto) {
        if (userUpdateDto == null)
            throw new NullOrEmptyException("UserDto");
        if (userUpdateDto.getId() == null)
            throw new NullOrEmptyException("Id");
        if (userUpdateDto.getUsername() != null && userRepository.findByUsername(userUpdateDto.getUsername()).isPresent())
            throw new AlreadyExistsException("Username");
        if (userUpdateDto.getEmail() != null && userRepository.findByEmail(userUpdateDto.getEmail()).isPresent())
            throw new AlreadyExistsException("Email");
        if (userUpdateDto.getPhoneNumber() != null && userRepository.findByPhoneNumber(userUpdateDto.getPhoneNumber()).isPresent())
            throw new AlreadyExistsException("Phone number");
        User user = userRepository.findById(userUpdateDto.getId()).orElseThrow(
                () -> new NotFoundException("User")
        );
        if (userUpdateDto.getEmail() != null)
            emailService.sendEmailVerificationMessage(userUpdateDto.getUsername(),userUpdateDto.getEmail());
        return new UserDto(userRepository.save(User.builder()
                .id(userUpdateDto.getId())
                .username(Validations.requireNonNullElse(userUpdateDto.getUsername(), user.getUsername()))
                .email(Validations.requireNonNullElse(userUpdateDto.getEmail(), user.getEmail()))
                .password(Validations.requireNonNullElse(userUpdateDto.getPassword() == null ? user.getPassword() : passwordEncoder.encode(userUpdateDto.getPassword()), user.getPassword()))
                .phoneNumber(Validations.requireNonNullElse(userUpdateDto.getPhoneNumber(), user.getPhoneNumber()))
                .gender(Validations.requireNonNullElse(userUpdateDto.getGender(), user.getGender()))
                .address(Validations.requireNonNullElse(userUpdateDto.getAddress(), user.getAddress()))
                .passportDetail(user.getPassportDetail())
                .build()));
    }

    @Override
    public void delete(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        userRepository.delete(userRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("User")
                ));
    }

    @Override
    public UserDto getById(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return new UserDto(userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User")
        ));
    }

    @Override
    public List<UserDto> getAll() {
        List<User> list = userRepository.findAll();
        if (list.isEmpty())
            throw new NullOrEmptyException("Users");
        return list.stream().map(UserDto::new).toList();
    }

    @Override
    public UserDto getByPhoneNumber(String phoneNumber) {
        if (Validations.isNullOrEmpty(phoneNumber))
            throw new NullOrEmptyException("Phone number");
        return new UserDto(userRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new NotFoundException("User")
        ));
    }

    @Override
    public UserDto getByUsername(String username) {
        if (Validations.isNullOrEmpty(username))
            throw new NullOrEmptyException("Username");
        return new UserDto(userRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException("User")
        ));
    }

    @Override
    public UserDto getByEmail(String email) {
        if (Validations.isNullOrEmpty(email))
            throw new NullOrEmptyException("Email");
        return new UserDto(userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User")
        ));
    }

    @Override
    public List<UserDto> getAllByGender(String gender) {
        if (Validations.isNullOrEmpty(gender))
            throw new NullOrEmptyException("Gender");
        if (!gender.equals("MALE") && !gender.equals("FEMALE"))
            throw new InvalidArgumentException("Gender");
        List<User> all = userRepository.findAllByGender(gender);
        if (all.isEmpty())
            throw new NullOrEmptyException("Users");
        return all.stream().map(UserDto::new).toList();
    }

    @Override
    public List<UserDto> getAllByAddress(String address) {
        if (Validations.isNullOrEmpty(address))
            throw new NullOrEmptyException("Address");
        List<User> all = userRepository.findAllByAddress(address);
        if (all.isEmpty())
            throw new NullOrEmptyException("Users");
        return all.stream().map(UserDto::new).toList();
    }

    @Override
    public List<UserDto> getAllByRole(String role) {
        if (Validations.isNullOrEmpty(role))
            throw new NullOrEmptyException("Role");
        List<User> all = userRepository.findAllByRole(role);
        if (all.isEmpty())
            throw new NullOrEmptyException("Users");
        return all.stream().map(UserDto::new).toList();
    }

    @Override
    public UserDto getBySimCard(String simCardNumber) {
        if (Validations.isNullOrEmpty(simCardNumber))
            throw new NullOrEmptyException("Sim card number");
        return new UserDto(userRepository.findBySimCard(simCardNumber).orElseThrow(
                () -> new NotFoundException("User")
        ));
    }

    @Override
    public UserDto getByPassportId(String passportId) {
        if (Validations.isNullOrEmpty(passportId))
            throw new NullOrEmptyException("Passport id");
        return new UserDto(userRepository.findByPassportId(passportId).orElseThrow(
                () -> new NotFoundException("User")
        ));
    }

    @Override
    public Double getBalanceByUserId(UUID user_id) {
        if (user_id == null)
            throw new NullOrEmptyException("User id");
        User user = userRepository.findById(user_id).orElseThrow(
                () -> new NotFoundException("User")
        );
        return user.getBalance();
    }

    @Override
    public UserDto addPassportDetailsToUser(PassportDetailDto passportDetailDto, UUID user_id) {
        if (user_id == null)
            throw new NullOrEmptyException("User Id");
        PassportDetailDto passport = passportDetailsService.save(passportDetailDto);
        User user = userRepository.findById(user_id).orElseThrow(() -> new NotFoundException("User"));
        user.setPassportDetail(
                PassportDetail.builder()
                        .name(passport.getName())
                        .surname(passport.getSurname())
                        .birthDate(passport.getBirthDate())
                        .passportId(passport.getPassportId())
                        .build()
        );
        return new UserDto(userRepository.save(user));
    }
    @Override
    public boolean verify(String token) {
        if (Validations.isNullOrEmpty(token))
            return false;
        Claims claims = jwtTokenProvider.parseAllClaims(token);
        if (jwtTokenProvider.isValid(token)){
            String email = claims.getSubject();
            String username = claims.get("username", String.class);
            if (userRepository.findByUsername(username).isPresent()) {
                User user = userRepository.findByUsername(username).get();
                user.setEmail(email);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}
