package uz.pdp.simline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.simline.dto.request.BuyPlanDto;
import uz.pdp.simline.dto.respone.PlanDto;
import uz.pdp.simline.dto.respone.SuccessResponse;
import uz.pdp.simline.service.PlanService;
import uz.pdp.simline.util.annotations.Name;
import uz.pdp.simline.util.annotations.Number;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<PlanDto> create(@RequestBody PlanDto planDto) {
        return ResponseEntity.ok(planService.save(planDto));
    }
    @PatchMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<PlanDto> update(@RequestBody PlanDto planDto) {
        return ResponseEntity.ok(planService.update(planDto));
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> deletePlan(@PathVariable UUID id) {
        planService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("Plan deleted"));
    }
    @DeleteMapping("/delete/name/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> deletePlanByName(@PathVariable @Name String name) {
        planService.deleteByName(name);
        return ResponseEntity.ok(new SuccessResponse("Plan deleted"));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<PlanDto> getPlanById(@PathVariable UUID id) {
        return ResponseEntity.ok(planService.getById(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<List<PlanDto>> getAllPlans() {
        return ResponseEntity.ok(planService.getAll());
    }
    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<PlanDto> getPlanByName(@PathVariable String name) {
        return ResponseEntity.ok(planService.getByName(name));
    }
    @GetMapping("/price/{price}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<PlanDto> getPlanByPrice(@PathVariable @Number Double price) {
        return ResponseEntity.ok(planService.getByPrice(price));
    }
    @GetMapping("/price/less/{price}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<List<PlanDto>> getPlansWithPriceLessThan(@PathVariable @Number Double price) {
        return ResponseEntity.ok(planService.getByWithPriceLessThan(price));
    }
    @GetMapping("/price/greater/{price}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> getPlansWithPriceGreaterThan(@PathVariable @Number Double price) {
        return ResponseEntity.ok(planService.getByWithPriceGreaterThan(price));
    }
    @GetMapping("/mb/{mb}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> getPlansWithMb(@PathVariable @Number Long mb) {
        return ResponseEntity.ok(planService.getByMb(mb));
    }
    @GetMapping("/sms/{sms}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> getPlansWithSms(@PathVariable @Number Long sms) {
        return ResponseEntity.ok(planService.getBySms(sms));
    }
    @GetMapping("/minute/{minute}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> getPlansWithMinute(@PathVariable @Number Long minute) {
        return ResponseEntity.ok(planService.getByMinute(minute));
    }
    @PatchMapping("/buy")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER, ROLE_MANAGER, ROLE_OPERATOR')")
    public ResponseEntity<?> buyPlan(@RequestBody BuyPlanDto buyPlanDto) {
        planService.buyPlan(buyPlanDto);
        return ResponseEntity.ok(new SuccessResponse("Plan bought"));
    }

}
