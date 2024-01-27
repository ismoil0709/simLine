package uz.pdp.simline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.simline.dto.respone.PassportDetailDto;
import uz.pdp.simline.dto.respone.SuccessResponse;
import uz.pdp.simline.service.PassportDetailsService;
import uz.pdp.simline.util.annotations.BirthDate;
import uz.pdp.simline.util.annotations.Name;
import uz.pdp.simline.util.annotations.PassportId;
import uz.pdp.simline.util.annotations.Username;

import java.time.LocalDate;
import java.util.UUID;
@RestController
@RequestMapping("/passport/detail/")
@RequiredArgsConstructor
public class PassportDetailController {
    private final PassportDetailsService passportDetailsService;
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody PassportDetailDto passportDetailDto){
        return ResponseEntity.ok(passportDetailsService.update(passportDetailDto));
    }
    //todo
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        passportDetailsService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("Passport deleted"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        return ResponseEntity.ok(passportDetailsService.getById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(passportDetailsService.getAll());
    }
    @GetMapping("/passportId/{passportId}")
    public ResponseEntity<?> getByPassportId(@PathVariable @PassportId String passportId){
        return ResponseEntity.ok(passportDetailsService.getByPassportId(passportId));
    }
    @GetMapping("/surname/{surname}")
    public ResponseEntity<?> getAllBySurname(@PathVariable @Username String surname){
        return ResponseEntity.ok(passportDetailsService.getAllBySurname(surname));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getAllByName(@PathVariable @Name String name){
        return ResponseEntity.ok(passportDetailsService.getAllByName(name));
    }
    @GetMapping("/birthDate/{birthDate}")
    public ResponseEntity<?> getAllByBirthDate(@PathVariable @BirthDate LocalDate birthDate){
        return ResponseEntity.ok(passportDetailsService.getAllByBirthDate(birthDate));
    }
    @GetMapping("/userId/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable UUID userId){
        return ResponseEntity.ok(passportDetailsService.getByUserId(userId));
    }

}
