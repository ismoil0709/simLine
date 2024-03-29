package uz.pdp.simline.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.simline.dto.request.UserLoginDto;
import uz.pdp.simline.dto.request.UserRegisterDto;
import uz.pdp.simline.dto.respone.ErrorResponse;
import uz.pdp.simline.dto.respone.SuccessResponse;
import uz.pdp.simline.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:3000/")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> customerRegister(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        return ResponseEntity.ok(userService.register(userRegisterDto));
    }
    @PostMapping("/login")
    public ResponseEntity<?> customerLogin(@RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(userService.login(userLoginDto));
    }
    @GetMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam String token) {
        if (userService.verify(token))
            return ResponseEntity.ok(new SuccessResponse("Success"));
        return ResponseEntity.badRequest().body(new ErrorResponse(""));
    }
}
