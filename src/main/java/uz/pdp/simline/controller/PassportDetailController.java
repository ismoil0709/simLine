package uz.pdp.simline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.simline.dto.respone.PassportDetailDto;
import uz.pdp.simline.dto.respone.SuccessResponse;
import uz.pdp.simline.service.PassportDetailsService;
import uz.pdp.simline.util.annotations.BirthDate;
import uz.pdp.simline.util.annotations.Name;
import uz.pdp.simline.util.annotations.PassportId;
import uz.pdp.simline.util.annotations.Username;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

// @PreAuthorize()
@RestController
@RequestMapping("/passport/detail/")
@RequiredArgsConstructor
public class PassportDetailController {
    private final PassportDetailsService passportDetailsService;
    @PostMapping("/save")
    public ResponseEntity<?> save(PassportDetailDto passportDetailDto){
        return ResponseEntity.ok(passportDetailsService.save(passportDetailDto));
    }
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER, ROLE_OPERATOR')")
    public ResponseEntity<?> update(PassportDetailDto passportDetailDto){
        return ResponseEntity.ok(passportDetailsService.update(passportDetailDto));
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER, ROLE_OPERATOR')")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        passportDetailsService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("Passport deleted"));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        return ResponseEntity.ok(passportDetailsService.getById(id));
    }
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(passportDetailsService.getAll());
    }
    @GetMapping("/passportId/{passportId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> getByPassportId(@PathVariable @PassportId String passportId){
        return ResponseEntity.ok(passportDetailsService.getByPassportId(passportId));
    }
    @GetMapping("/surname/{surname}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER, ROLE_OPERATOR')")
    public ResponseEntity<?> getAllBySurname(@PathVariable @Username String surname){
        return ResponseEntity.ok(passportDetailsService.getAllBySurname(surname));
    }
    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> getAllByName(@PathVariable @Name String name){
        return ResponseEntity.ok(passportDetailsService.getAllByName(name));
    }
    @GetMapping("/birthDate/{birthDate}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> getAllByBirthDate(@PathVariable @BirthDate LocalDate birthDate){
        return ResponseEntity.ok(passportDetailsService.getAllByBirthDate(birthDate));
    }
    @GetMapping("/userId/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_OPERATOR')")
    public ResponseEntity<?> getByUserId(@PathVariable UUID userId){
        return ResponseEntity.ok(passportDetailsService.getByUserId(userId));
    }

}
