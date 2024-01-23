package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.respone.PlanDto;

import java.util.List;
import java.util.UUID;

@Service
public interface PlanService {
    PlanDto createPlan(PlanDto plan);

    void deletePlan(UUID id);

    void deletePlanByName(String name);

    PlanDto updatePlan(PlanDto updatedPlan);

    PlanDto getPlanById(UUID id);

    List<PlanDto> getAll();

    PlanDto getPlanByName(String name);

    PlanDto getPlanByPrice(Double price);

    List<PlanDto> getPlansWithPriceLessThan(Double maxPrice);
}
