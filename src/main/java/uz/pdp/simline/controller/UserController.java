package uz.pdp.simline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.simline.dto.request.UserUpdateDto;
import uz.pdp.simline.dto.respone.ErrorResponse;
import uz.pdp.simline.dto.respone.PassportDetailDto;
import uz.pdp.simline.dto.respone.UserDto;
import uz.pdp.simline.dto.respone.SuccessResponse;
import uz.pdp.simline.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/add/passport")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER, ROLE_OPERATOR')")
    public ResponseEntity<?> addPassport(@RequestBody PassportDetailDto passportDetailDto,UUID id){
        return ResponseEntity.ok(userService.addPassportDetailsToUser(passportDetailDto,id));
    }
    @PatchMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER, ROLE_OPERATOR')")
    public ResponseEntity<UserDto> update(@RequestBody UserUpdateDto userUpdateDto){
        return ResponseEntity.ok(userService.update(userUpdateDto));
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER, ROLE_OPERATOR')")
    public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id){
        userService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("User deleted"));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_OPERATOR')")
    public ResponseEntity<UserDto> getById(@PathVariable UUID id){
        return ResponseEntity.ok(userService.getById(id));
    }
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_OPERATOR')")
    public ResponseEntity<List<UserDto>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }
    @GetMapping("/phoneNumber/{phoneNumber}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<UserDto> getByPhoneNumber(@PathVariable String phoneNumber){
        return ResponseEntity.ok(userService.getByPhoneNumber(phoneNumber));
    }
    @GetMapping("/username/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getByUsername(username));
    }
    @GetMapping("/email/{email}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getByEmail(email));
    }
    @GetMapping("/all/gender/{gender}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllByGender(@PathVariable String gender){
        return ResponseEntity.ok(userService.getAllByGender(gender));
    }
    @GetMapping("/all/address/{address}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> getAllByAddress(@PathVariable String address){
        return ResponseEntity.ok(userService.getAllByAddress(address));
    }
    @GetMapping("/all/role/{role}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_OPERATOR')")
    public ResponseEntity<?> getAllByRole(@PathVariable String role){
        return ResponseEntity.ok(userService.getAllByRole(role));
    }
    @GetMapping("/sim-card/{number}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> getBySimCard(@PathVariable String number){
        return ResponseEntity.ok(userService.getBySimCard(number));
    }
    @GetMapping("/passport/{passportId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> getByPassport(@PathVariable String passportId){
        return ResponseEntity.ok(userService.getByPassportId(passportId));
    }
    @GetMapping("/balance/{user_id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER, ROLE_OPERATOR')")
    public ResponseEntity<?> getUserBalance(@PathVariable UUID user_id){
        return ResponseEntity.ok(new SuccessResponse(userService.getBalanceByUserId(user_id).toString()));
    }
}