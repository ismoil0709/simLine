package uz.pdp.simline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> update(@RequestBody SimCardUpdateDto simCardUpdateDto) {
        return ResponseEntity.ok(simCardService.update(simCardUpdateDto));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_OPERATOR')")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(simCardService.getById(id));
    }

    @GetMapping("/number/{number}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER, ROLE_OPERATOR')")
    public ResponseEntity<?> getByNumber(@PathVariable String number) {
        return ResponseEntity.ok(simCardService.getByNumber(number));
    }
    @GetMapping("/price/{price}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER, ROLE_OPERATOR')")
    public ResponseEntity<?> getByPrice(@PathVariable Double price) {
        return ResponseEntity.ok(simCardService.getByPrice(price));
    }

    @GetMapping("/all/price/less/{price}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER, ROLE_OPERATOR')")
    public ResponseEntity<?> getAlByWithPriceLessThan(@PathVariable Double price) {
        return ResponseEntity.ok(simCardService.getAllByWithPriceLessThan(price));
    }

    @GetMapping("/all/price/greater/{price}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER, ROLE_OPERATOR')")
    public ResponseEntity<?> getAlByWithPriceGreaterThan(@PathVariable Double price) {
        return ResponseEntity.ok(simCardService.getAllByWithPriceGreaterThan(price));
    }

    @GetMapping("/all/activity/{isActive}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_OPERATOR')")
    public ResponseEntity<?> getByActivity(@PathVariable Boolean isActive) {
        return ResponseEntity.ok(simCardService.getAllByActivity(isActive));
    }
    @GetMapping("/unbooked/number/{number}")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_OPERATOR, ROLE_USER')")
    public ResponseEntity<?> getByUnBookedNumber(@PathVariable String number) {
        return ResponseEntity.ok(simCardService.getUnBookedSimCard(number));
    }
    @GetMapping("/all/plan/{planId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> getAllByPlan(@PathVariable UUID planId) {
        return ResponseEntity.ok(simCardService.getAllByPlanId(planId));
    }
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER, ROLE_OPERATOR')")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(simCardService.getAll());
    }

    /**
     * new SuccessResponse returned: {
     *     "message": "uz.pdp.simline.entity.Balance@6c8ffc3c",
     *     "time": "2024-01-26T01:27:56.400089872"
     * }
     * this. So I removed it and toString() also. After that, worked perfectly!
     * */
    @GetMapping("/balance/number/{number}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER, ROLE_OPERATOR')")
    public ResponseEntity<?> getBalanceByNumber(@PathVariable String number) {
        return ResponseEntity.ok(new SuccessResponse((simCardService.getBalanceByNumber(number)).toString()));
    }
    @GetMapping("/all/balance/{balance}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> getAllByBalance(@PathVariable Double balance) {
        return ResponseEntity.ok(simCardService.getAllByBalance(balance));
    }
    @GetMapping("/all/balance/less/{balance}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllByWithBalanceLessThan(@PathVariable Double balance) {
        return ResponseEntity.ok(simCardService.getAllByWithBalanceLessThan(balance));
    }
    @GetMapping("/all/balance/greater/{balance}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER')")
    public ResponseEntity<?> getAllByWithBalanceGreaterThan(@PathVariable Double balance) {
        return ResponseEntity.ok(simCardService.getAllByWithBalanceGreaterThan(balance));
    }
    /**
     * Worked but did not throw exception while user balance is null!
     * */
    @PatchMapping("/buy/number")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER, ROLE_OPERATOR')")
    public ResponseEntity<?> buyByNumber(@RequestBody BuyNumberDto buyNumberDto) {
        simCardService.buyByNumber(buyNumberDto);
        return ResponseEntity.ok(new SuccessResponse());
    }
}