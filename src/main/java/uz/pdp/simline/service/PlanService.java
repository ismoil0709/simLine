package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.entity.Plan;

import java.util.List;
import java.util.UUID;

@Service
public interface PlanService {
    Plan getPlanById(UUID id);

    Plan getPlanByName(String name);

    Plan getPlanByPrice(Double price);

    List<Plan> getPlansWithPriceLessThan(Double maxPrice);

    List<Plan> getAllPlans();

    void createPlan(Plan plan);

    void updatePlan(UUID id, Plan updatedPlan);

    void deletePlan(UUID id);

    void deletePlanByName(String name);
}
