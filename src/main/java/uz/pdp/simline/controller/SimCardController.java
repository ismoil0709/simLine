package uz.pdp.simline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> update(@RequestBody SimCardUpdateDto simCardUpdateDto) {
        return ResponseEntity.ok(simCardService.update(simCardUpdateDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(simCardService.getById(id));
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<?> getByNumber(@PathVariable String number) {
        return ResponseEntity.ok(simCardService.getByNumber(number));
    }
    @GetMapping("/price/{price}")
    public ResponseEntity<?> getByPrice(@PathVariable Double price) {
        return ResponseEntity.ok(simCardService.getByPrice(price));
    }

    @GetMapping("/all/price/less/{price}")
    public ResponseEntity<?> getAlByWithPriceLessThan(@PathVariable Double price) {
        return ResponseEntity.ok(simCardService.getAllByWithPriceLessThan(price));
    }

    @GetMapping("/all/price/greater/{price}")
    public ResponseEntity<?> getAlByWithPriceGreaterThan(@PathVariable Double price) {
        return ResponseEntity.ok(simCardService.getAllByWithPriceGreaterThan(price));
    }

    @GetMapping("/all/activity/{isActive}")
    public ResponseEntity<?> getByActivity(@PathVariable Boolean isActive) {
        return ResponseEntity.ok(simCardService.getAllByActivity(isActive));
    }

    @GetMapping("/all/plan/{planId}")
    public ResponseEntity<?> getAllByPlan(@PathVariable UUID planId) {
        return ResponseEntity.ok(simCardService.getAllByPlanId(planId));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(simCardService.getAll());
    }
    @GetMapping("/balance/number/{number}")
    public ResponseEntity<? > getAllByBalance(@PathVariable String number) {
        return ResponseEntity.ok(new SuccessResponse(simCardService.getBalanceByNumber(number).toString()));
    }
    @GetMapping("/all/balance/{balance}")
    public ResponseEntity<?> getAllByBalance(@PathVariable Double balance) {
        return ResponseEntity.ok(simCardService.getAllByBalance(balance));
    }
    @GetMapping("/all/balance/less/{balance}")
    public ResponseEntity<?> getAllByWithBalanceLessThan(@PathVariable Double balance) {
        return ResponseEntity.ok(simCardService.getAllByWithBalanceLessThan(balance));
    }
    @GetMapping("/all/balance/greater/{balance}")
    public ResponseEntity<?> getAllByWithBalanceGreaterThan(@PathVariable Double balance) {
        return ResponseEntity.ok(simCardService.getAllByWithBalanceGreaterThan(balance));
    }

}
