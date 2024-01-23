package uz.pdp.simline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.simline.dto.request.CustomerUpdateDto;
import uz.pdp.simline.dto.respone.CustomerDto;
import uz.pdp.simline.service.CustomerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PatchMapping("/update")
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerUpdateDto customerUpdateDto){
        return ResponseEntity.ok(customerService.update(customerUpdateDto));
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id){
        customerService.delete(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable UUID id){
        return ResponseEntity.ok(customerService.getById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> getAll(){
        return ResponseEntity.ok(customerService.getAll());
    }
    @GetMapping("/phoneNumber/{phoneNumber}")
    public ResponseEntity<CustomerDto> getByPhoneNumber(@PathVariable String phoneNumber){
        return ResponseEntity.ok(customerService.getByPhoneNumber(phoneNumber));
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<CustomerDto> getByUsername(@PathVariable String username){
        return ResponseEntity.ok(customerService.getByUsername(username));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDto> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(customerService.getByEmail(email));
    }
}

