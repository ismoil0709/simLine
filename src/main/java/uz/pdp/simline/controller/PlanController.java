package uz.pdp.simline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.simline.dto.respone.PlanDto;
import uz.pdp.simline.service.PlanService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;
    @PostMapping("/create")
    public ResponseEntity<PlanDto> create(@RequestBody PlanDto planDto) {
        return ResponseEntity.ok(planService.save(planDto));
    }
    @PatchMapping("/update")
    public ResponseEntity<PlanDto> update(@RequestBody PlanDto planDto) {
        return ResponseEntity.ok(planService.update(planDto));
    }
    @DeleteMapping("/delete/{id}")
    public void deletePlan(@PathVariable UUID id) {
        planService.delete(id);
    }
    @DeleteMapping("/delete/name/{name}")
    public void deletePlanByName(@PathVariable String name) {
        planService.deleteByName(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDto> getPlanById(@PathVariable UUID id) {
        return ResponseEntity.ok(planService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlanDto>> getAllPlans() {
        return ResponseEntity.ok(planService.getAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PlanDto> getPlanByName(@PathVariable String name) {
        return ResponseEntity.ok(planService.getByName(name));
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<PlanDto> getPlanByPrice(@PathVariable Double price) {
        return ResponseEntity.ok(planService.getByPrice(price));
    }

    @GetMapping("/price/less/{price}")
    public ResponseEntity<List<PlanDto>> getPlansWithPriceLessThan(@PathVariable Double price) {
        return ResponseEntity.ok(planService.getByWithPriceLessThan(price));
    }
    @GetMapping("/price/greater/{price}")
    public ResponseEntity<List<PlanDto>> getPlansWithPriceGreaterThan(@PathVariable Double price) {
        return ResponseEntity.ok(planService.getByWithPriceGreaterThan(price));
    }
    @GetMapping("/mb/{mb}")
    public ResponseEntity<List<PlanDto>> getPlansWithMb(@PathVariable Long mb) {
        return ResponseEntity.ok(planService.getByMb(mb));
    }
    @GetMapping("/sms/{sms}")
    public ResponseEntity<List<PlanDto>> getPlansWithSms(@PathVariable Long sms) {
        return ResponseEntity.ok(planService.getBySms(sms));
    }
    @GetMapping("/minute/{minute}")
    public ResponseEntity<List<PlanDto>> getPlansWithMinute(@PathVariable Long minute) {
        return ResponseEntity.ok(planService.getByMinute(minute));
    }
}
