package uz.pdp.simline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.pdp.simline.dto.request.BuyNumberDto;
import uz.pdp.simline.dto.request.SimCardUpdateDto;
import uz.pdp.simline.dto.respone.SuccessResponse;
import uz.pdp.simline.service.SimCardService;

import java.util.UUID;

@RestController
@RequestMapping("/sim-card")
@RequiredArgsConstructor
public class SimCardController {

    private final SimCardService simCardService;

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_OPERATOR')")
    public ResponseEntity<?> update(@RequestBody SimCardUpdateDto simCardUpdateDto) {
        return ResponseEntity.ok(simCardService.update(simCardUpdateDto));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_OPERATOR')")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(simCardService.getById(id));
    }

    @GetMapping("/number/{number}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_OPERATOR')")
    public ResponseEntity<?> getByNumber(@PathVariable String number) {
        return ResponseEntity.ok(simCardService.getByNumber(number));
    }
    @GetMapping("/price/{price}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_USER','ROLE_OPERATOR')")
    public ResponseEntity<?> getByPrice(@PathVariable Double price) {
        return ResponseEntity.ok(simCardService.getByPrice(price));
    }

    @GetMapping("/all/price/less/{price}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_OPERATOR')")
    public ResponseEntity<?> getAlByWithPriceLessThan(@PathVariable Double price) {
        return ResponseEntity.ok(simCardService.getAllByWithPriceLessThan(price));
    }

    @GetMapping("/all/price/greater/{price}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_OPERATOR')")
    public ResponseEntity<?> getAlByWithPriceGreaterThan(@PathVariable Double price) {
        return ResponseEntity.ok(simCardService.getAllByWithPriceGreaterThan(price));
    }
    @GetMapping("/unbooked/price/{price}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_MANAGER','ROLE_OPERATOR')")
    public ResponseEntity<?> getUnbookedWithPrice(@PathVariable Double price) {
        return ResponseEntity.ok(simCardService.getUnbookedByPrice(price));
    }
    @GetMapping("/unbooked/price/less/{price}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_MANAGER','ROLE_OPERATOR')")
    public ResponseEntity<?> getUnbookedWithPriceLessThan(@PathVariable Double price) {
        return ResponseEntity.ok(simCardService.getUnbookedWithPriceLessThan(price));
    }
    @GetMapping("/unbooked/price/greater/{price}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_MANAGER','ROLE_OPERATOR')")
    public ResponseEntity<?> getUnbookedWithPriceGreaterThan(@PathVariable Double price) {
        return ResponseEntity.ok(simCardService.getUnbookedWithPriceGreaterThan(price));
    }
    @GetMapping("/all/activity/{isActive}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_OPERATOR')")
    public ResponseEntity<?> getByActivity(@PathVariable Boolean isActive) {
        return ResponseEntity.ok(simCardService.getAllByActivity(isActive));
    }
    @GetMapping("/unbooked/number/{number}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_OPERATOR','ROLE_USER')")
    public ResponseEntity<?> getByUnBookedNumber(@PathVariable String number) {
        return ResponseEntity.ok(simCardService.getUnBookedByNumber(number));
    }
    @GetMapping("/all/plan/{planId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_OPERATOR')")
    public ResponseEntity<?> getAllByPlan(@PathVariable UUID planId) {
        return ResponseEntity.ok(simCardService.getAllByPlanId(planId));
    }
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_OPERATOR')")
    public ResponseEntity<?> getAll() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok(simCardService.getAll());
    }
    @GetMapping("/balance/number/{number}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_USER','ROLE_OPERATOR')")
    public ResponseEntity<?> getBalanceByNumber(@PathVariable String number) {
        return ResponseEntity.ok(simCardService.getBalanceByNumber(number));
    }
    @GetMapping("/all/balance/{balance}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_OPERATOR')")
    public ResponseEntity<?> getAllByBalance(@PathVariable Double balance) {
        return ResponseEntity.ok(simCardService.getAllByBalance(balance));
    }
    @GetMapping("/all/balance/less/{balance}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_OPERATOR')")
    public ResponseEntity<?> getAllByWithBalanceLessThan(@PathVariable Double balance) {
        return ResponseEntity.ok(simCardService.getAllByWithBalanceLessThan(balance));
    }
    @GetMapping("/all/balance/greater/{balance}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_OPERATOR')")
    public ResponseEntity<?> getAllByWithBalanceGreaterThan(@PathVariable Double balance) {
        return ResponseEntity.ok(simCardService.getAllByWithBalanceGreaterThan(balance));
    }
    @GetMapping("/all/number/customer/{number}")
    public ResponseEntity<?>getAllByCustomNumber(@PathVariable String number){
        return ResponseEntity.ok(simCardService.getAllByCustomNumber(number));
    }
    @PatchMapping("/buy/number")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_USER','ROLE_OPERATOR')")
    public ResponseEntity<?> buyByNumber(@RequestBody BuyNumberDto buyNumberDto) {
        simCardService.buyByNumber(buyNumberDto);
        return ResponseEntity.ok(new SuccessResponse());
    }
}