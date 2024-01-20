package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.entity.Plan;

import java.util.List;
import java.util.UUID;

@Service
public interface PlanService {
    void createPlan(Plan plan);

    void deletePlan(UUID id);

    void deletePlanByName(String name);

    void updatePlan(Plan updatedPlan);

    Plan getPlanById(UUID id);

    List<Plan> getAll();

    Plan getPlanByName(String name);

    Plan getPlanByPrice(Double price);

    List<Plan> getPlansWithPriceLessThan(Double maxPrice);
}
