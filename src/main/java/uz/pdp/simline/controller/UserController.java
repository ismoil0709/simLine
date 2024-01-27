package uz.pdp.simline.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.simline.dto.request.UserUpdateDto;
import uz.pdp.simline.dto.respone.ErrorResponse;
import uz.pdp.simline.dto.respone.PassportDetailDto;
import uz.pdp.simline.dto.respone.UserDto;
import uz.pdp.simline.dto.respone.SuccessResponse;
import uz.pdp.simline.service.UserService;
import uz.pdp.simline.util.annotations.*;
import uz.pdp.simline.util.annotations.Number;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/add/passport")
    public ResponseEntity<?> addPassport(@RequestBody @Valid PassportDetailDto passportDetailDto, @RequestParam(name = "userId") UUID userId){
        return ResponseEntity.ok(userService.addPassportDetailsToUser(passportDetailDto,userId));
    }
    @PatchMapping("/update")
    public ResponseEntity<UserDto> update(@RequestBody @Valid UserUpdateDto userUpdateDto){
        return ResponseEntity.ok(userService.update(userUpdateDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id){
        userService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("User deleted"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable UUID id){
        return ResponseEntity.ok(userService.getById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }
    @GetMapping("/phoneNumber/{phoneNumber}")
    public ResponseEntity<UserDto> getByPhoneNumber(@PathVariable String phoneNumber){
        return ResponseEntity.ok(userService.getByPhoneNumber(phoneNumber));
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getByUsername(username));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getByEmail(email));
    }
    @GetMapping("/all/gender/{gender}")
    public ResponseEntity<?> getAllByGender(@PathVariable String gender){
        return ResponseEntity.ok(userService.getAllByGender(gender));
    }
    @GetMapping("/all/address/{address}")
    public ResponseEntity<?> getAllByAddress(@PathVariable String address){
        return ResponseEntity.ok(userService.getAllByAddress(address));
    }
    @GetMapping("/all/role/{role}")
    public ResponseEntity<?> getAllByRole(@PathVariable String role){
        return ResponseEntity.ok(userService.getAllByRole(role));
    }
    @GetMapping("/sim-card/{number}")
    public ResponseEntity<?> getBySimCard(@PathVariable String number){
        return ResponseEntity.ok(userService.getBySimCard(number));
    }
    @GetMapping("/passport/{passportId}")
    public ResponseEntity<?> getByPassport(@PathVariable String passportId){
        return ResponseEntity.ok(userService.getByPassportId(passportId));
    }
    @GetMapping("/balance/{user_id}")
    public ResponseEntity<?> getUserBalance(@PathVariable UUID user_id){
        return ResponseEntity.ok(new SuccessResponse(userService.getBalanceByUserId(user_id).toString()));
    }
}

