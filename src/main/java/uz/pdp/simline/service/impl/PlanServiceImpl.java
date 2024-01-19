package uz.pdp.simline.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.simline.entity.Plan;
import uz.pdp.simline.repository.PlanRepository;
import uz.pdp.simline.service.PlanService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    @Override
    public Plan getPlanById(UUID id) {
        if (id == null) {
            //TODO throw any exception
        }
        if (planRepository.findById(id).isEmpty()) {
            //TODO throw any exception
            //throw new IllegalArgumentException("Plan with id " + id + " not found");
        }
        return planRepository.findById(id).get();
    }

    @Override
    public Plan getPlanByName(String name) {
        if (name.isEmpty() || name.isBlank()) {
            //TODO throw any exception
            //throw new IllegalArgumentException("Name cannot be empty or blank");
        } else if (planRepository.findByName(name).isEmpty()) {
            //TODO throw any exception
        }
        return planRepository.findByName(name).get();
    }

    @Override
    public Plan getPlanByPrice(Double price) {
        if (price == null) {
            //TODO throw any exception
        }
        if (planRepository.findByPrice(price).isEmpty()) {
            //TODO throw any exception
        }
        return planRepository.findByPrice(price).get();
    }

    @Override
    public List<Plan> getPlansWithPriceLessThan(Double maxPrice) {
        if (maxPrice == null) {
            //TODO throw any exception
        }
        if (planRepository.findByPriceLessThan(maxPrice).isEmpty()) {
            //TODO throw any exception
        }
        return planRepository.findByPriceLessThan(maxPrice);
    }

    @Override
    public List<Plan> getAllPlans() {
        if (planRepository.findAll().isEmpty()) {
            //TODO throw any exception
        }
        return planRepository.findAll();
    }

    @Override
    public void createPlan(Plan plan) {

        if (plan == null) {
            //TODO throw any exception
            //throw new IllegalArgumentException("Plan cannot be null");
        }

        if (isNullOrEmpty(Objects.requireNonNull(plan).getName()) || plan.getExpiry() == null || plan.getMb() == null ||
            plan.getSms() == null || plan.getMinute() == null || plan.getPrice() == null) {
            //TODO throw any exception
        }

        CheckNegativeValuesAndExpiry(plan);

        planRepository.save(plan);
    }

    @Override
    public void updatePlan(UUID id, Plan updatedPlan) {
        if (id == null) {
            //TODO throw any exception
        }

        if (isNullOrEmpty(updatedPlan.getName()) || updatedPlan.getExpiry() == null || updatedPlan.getMb() == null ||
            updatedPlan.getSms() == null || updatedPlan.getMinute() == null || updatedPlan.getPrice() == null) {
            //TODO throw any exception
        }

        CheckNegativeValuesAndExpiry(updatedPlan);

        if (planRepository.findById(id).isEmpty()) {
            //TODO throw any exception
        }

        Plan existingPlan = planRepository.findById(id).get();

        existingPlan.setName(updatedPlan.getName());
        existingPlan.setExpiry(updatedPlan.getExpiry());
        existingPlan.setMb(updatedPlan.getMb());
        existingPlan.setSms(updatedPlan.getSms());
        existingPlan.setMinute(updatedPlan.getMinute());
        existingPlan.setPrice(updatedPlan.getPrice());

        planRepository.save(existingPlan);
    }

    @Override
    public void deletePlan(UUID id) {
        if (id == null) {
            //TODO throw any exception
        }
        planRepository.deleteById(id);
    }

    @Override
    public void deletePlanByName(String name) {
        if (isNullOrEmpty(name)) {
            //TODO throw any exception
        }
        planRepository.deleteByName(name);
    }


    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private void CheckNegativeValuesAndExpiry(Plan updatedPlan) {
        if (updatedPlan.getMb() < 0 || updatedPlan.getSms() < 0 || updatedPlan.getMinute() < 0 || updatedPlan.getPrice() < 0) {
            //TODO throw any exception
            //throw new IllegalArgumentException("mb, sms, minute and price cannot be negative");
        }

        long threeMonthsInMillis = 1000L * 60 * 60 * 24 * 30;
        if (updatedPlan.getExpiry() <= System.currentTimeMillis() + threeMonthsInMillis) {
            //TODO throw any exception
            //throw new IllegalArgumentException("Expiry must be 1 month longer than the current date");
        }
    }
}
