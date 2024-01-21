package uz.pdp.simline.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.simline.entity.Plan;
import uz.pdp.simline.exception.InvalidArgumentException;
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.exception.NullOrEmptyException;
import uz.pdp.simline.repository.PlanRepository;
import uz.pdp.simline.service.PlanService;
import uz.pdp.simline.util.Validations;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    @Override
    public void createPlan(Plan plan) {
        if (plan == null)
            throw new NullOrEmptyException("Plan");
        if (Validations.isNullOrEmpty(plan.getName()))
            throw new NullOrEmptyException("Name");
        if (plan.getExpiry() == null)
            throw new NullOrEmptyException("Expiry");
        if (plan.getMb() == null)
            throw new NullOrEmptyException("Mb");
        if (plan.getSms() == null)
            throw new NullOrEmptyException("Sms");
        if (plan.getMinute() == null)
            throw new NullOrEmptyException("Minute");
        if (plan.getPrice() == null)
            throw new NullOrEmptyException("Price");
        if (plan.getMb() < 0 || plan.getSms() < 0 || plan.getMinute() < 0 || plan.getPrice() < 0)
            throw new InvalidArgumentException("plans details");
        planRepository.save(plan);
    }

    @Override
    public void updatePlan(Plan updatedPlan) {
        if (updatedPlan == null)
            throw new NullOrEmptyException("Updated Plan");
        if (updatedPlan.getId() == null)
            throw new NullOrEmptyException("Id");
        Plan plan = planRepository.findById(updatedPlan.getId()).orElseThrow(
                () -> new NotFoundException("Plan")
        );
        if (updatedPlan.getMb() != null && updatedPlan.getMb() < 0)
            throw new InvalidArgumentException("mb");
        if (updatedPlan.getSms() != null && updatedPlan.getSms() < 0)
            throw new InvalidArgumentException("sms");
        if (updatedPlan.getMinute() != null && updatedPlan.getMinute() < 0)
            throw new InvalidArgumentException("minute");
        if (updatedPlan.getPrice() != null && updatedPlan.getPrice() < 0)
            throw new InvalidArgumentException("price");
        planRepository.save(
                Plan.builder()
                        .id(updatedPlan.getId())
                        .name(Objects.requireNonNullElse(updatedPlan.getName(), plan.getName()))
                        .expiry(Objects.requireNonNullElse(updatedPlan.getExpiry(), plan.getExpiry()))
                        .mb(Objects.requireNonNullElse(updatedPlan.getMb(), plan.getMb()))
                        .sms(Objects.requireNonNullElse(updatedPlan.getSms(), plan.getSms()))
                        .minute(Objects.requireNonNullElse(updatedPlan.getMinute(), plan.getMinute()))
                        .price(Objects.requireNonNullElse(updatedPlan.getPrice(), plan.getPrice()))
                        .build()
        );
    }

    @Override
    public void deletePlan(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        planRepository.delete(
                planRepository.findById(id).orElseThrow(
                        () -> new NotFoundException("Plan")
                )
        );
    }

    @Override
    public void deletePlanByName(String name) {
        if (Validations.isNullOrEmpty(name))
            throw new NullOrEmptyException("Name");
        planRepository.delete(
                planRepository.findByName(name).orElseThrow(
                        () -> new NotFoundException("Plan")
                ));
    }

    @Override
    public Plan getPlanById(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return planRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Plan")
        );
    }


    @Override
    public Plan getPlanByName(String name) {
        if (Validations.isNullOrEmpty(name))
            throw new NullOrEmptyException("Name");
        return planRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("Plan")
        );
    }

    @Override
    public Plan getPlanByPrice(Double price) {
        if (price == null)
            throw new NullOrEmptyException("Price");
        return planRepository.findByPrice(price).orElseThrow(
                () -> new NotFoundException("Plan")
        );
    }

    @Override
    public List<Plan> getPlansWithPriceLessThan(Double maxPrice) {
        if (maxPrice == null)
            throw new NullOrEmptyException("Max Price");
        List<Plan> plans = planRepository.findByPriceLessThan(maxPrice);
        if (plans.isEmpty())
            throw new NullOrEmptyException("Plans");
        return plans;
    }

    @Override
    public List<Plan> getAll() {
        List<Plan> all = planRepository.findAll();
        if (all.isEmpty())
            throw new NullOrEmptyException("Plans");
        return all;
    }
}
