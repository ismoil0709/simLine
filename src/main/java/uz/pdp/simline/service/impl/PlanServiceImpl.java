package uz.pdp.simline.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.BuyPlanDto;
import uz.pdp.simline.dto.respone.PlanDto;
import uz.pdp.simline.entity.Plan;
import uz.pdp.simline.entity.SimCard;
import uz.pdp.simline.exception.AlreadyExistsException;
import uz.pdp.simline.exception.InvalidArgumentException;
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.exception.NullOrEmptyException;
import uz.pdp.simline.exception.TransactionFailedException;
import uz.pdp.simline.repository.PlanRepository;
import uz.pdp.simline.repository.SimCardRepository;
import uz.pdp.simline.service.PlanService;
import uz.pdp.simline.util.Validations;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final SimCardRepository simCardRepository;

    @Override
    public PlanDto save(PlanDto plan) {
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
        if (planRepository.findByName(plan.getName()).isPresent())
            throw new AlreadyExistsException("Plan");
        return new PlanDto(planRepository.save(PlanDto.castToPlan(plan)));
    }

    @Override
    public PlanDto update(PlanDto updatedPlan) {
        if (updatedPlan == null)
            throw new NullOrEmptyException("Updated Plan");
        if (updatedPlan.getId() == null)
            throw new NullOrEmptyException("Id");
        Plan plan = planRepository.findById(updatedPlan.getId()).orElseThrow(
                () -> new NotFoundException("Plan")
        );
        return new PlanDto(planRepository.save(
                Plan.builder()
                        .id(updatedPlan.getId())
                        .name(Validations.requireNonNullElse(updatedPlan.getName(), plan.getName()))
                        .expiry(Validations.requireNonNullElse(updatedPlan.getExpiry(), plan.getExpiry()))
                        .mb(Validations.requireNonNullElse(updatedPlan.getMb(), plan.getMb()))
                        .sms(Validations.requireNonNullElse(updatedPlan.getSms(), plan.getSms()))
                        .minute(Validations.requireNonNullElse(updatedPlan.getMinute(), plan.getMinute()))
                        .price(Validations.requireNonNullElse(updatedPlan.getPrice(), plan.getPrice()))
                        .build()
        ));
    }

    @Override
    public void delete(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        planRepository.delete(
                planRepository.findById(id).orElseThrow(
                        () -> new NotFoundException("Plan")
                )
        );
    }

    @Override
    public void deleteByName(String name) {
        if (Validations.isNullOrEmpty(name))
            throw new NullOrEmptyException("Name");
        planRepository.delete(
                planRepository.findByName(name).orElseThrow(
                        () -> new NotFoundException("Plan")
                ));
    }

    @Override
    public PlanDto getById(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return new PlanDto(planRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Plan")
        ));
    }


    @Override
    public PlanDto getByName(String name) {
        if (Validations.isNullOrEmpty(name))
            throw new NullOrEmptyException("Name");
        return new PlanDto(planRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("Plan")
        ));
    }

    @Override
    public PlanDto getByPrice(Double price) {
        if (price == null)
            throw new NullOrEmptyException("Price");
        return new PlanDto(planRepository.findByPrice(price).orElseThrow(
                () -> new NotFoundException("Plan")
        ));
    }

    @Override
    public List<PlanDto> getByWithPriceLessThan(Double maxPrice) {
        if (maxPrice == null)
            throw new NullOrEmptyException("Max Price");
        List<Plan> plans = planRepository.findByPriceLessThan(maxPrice);
        if (plans.isEmpty())
            throw new NullOrEmptyException("Plans");
        return plans.stream().map(PlanDto::new).toList();
    }

    @Override
    public List<PlanDto> getByWithPriceGreaterThan(Double minPrice) {
        if (minPrice == null)
            throw new NullOrEmptyException("Min price");
        List<Plan> plans = planRepository.findByPriceGreaterThan(minPrice);
        if (plans.isEmpty())
            throw new NullOrEmptyException("Plans");
        return plans.stream().map(PlanDto::new).toList();
    }

    @Override
    public List<PlanDto> getByMb(Long mb) {
        if (mb == null)
            throw new NullOrEmptyException("Mb");
        List<Plan> plans = planRepository.findByMb(mb);
        if (plans.isEmpty())
            throw new NullOrEmptyException("Plans");
        return plans.stream().map(PlanDto::new).toList();
    }

    @Override
    public List<PlanDto> getBySms(Long sms) {
        if (sms == null)
            throw new NullOrEmptyException("Sms");
        List<Plan> plans = planRepository.findBySms(sms);
        if (plans.isEmpty())
            throw new NullOrEmptyException("Plans");
        return plans.stream().map(PlanDto::new).toList();
    }

    @Override
    public List<PlanDto> getByMinute(Long minute) {
        if (minute == null)
            throw new NullOrEmptyException("Minute");
        List<Plan> plans = planRepository.findByMinute(minute);
        if (plans.isEmpty())
            throw new NullOrEmptyException("Plans");
        return plans.stream().map(PlanDto::new).toList();
    }

    @Override
    public void buyPlan(BuyPlanDto buyPlanDto) {
        if (buyPlanDto.getPlanId() == null)
            throw new NullOrEmptyException("Plan id");
        if (buyPlanDto.getSimCardId() == null)
            throw new NullOrEmptyException("SimCard id");
        Plan plan = planRepository.findById(buyPlanDto.getPlanId()).orElseThrow(
                () -> new NotFoundException("Plan")
        );
        SimCard simCard = simCardRepository.findById(buyPlanDto.getSimCardId()).orElseThrow(
                () -> new NotFoundException("SimCard")
        );
        if (simCard.getBalance().getBalance() < plan.getPrice())
            throw new TransactionFailedException("Not enough money in the balance");
        simCard.getBalance().setBalance(simCard.getBalance().getBalance() - plan.getPrice());
        simCard.setPlan(plan);
        simCardRepository.save(simCard);
    }

    @Override
    public List<PlanDto> getAll() {
        List<Plan> all = planRepository.findAll();
        if (all.isEmpty())
            throw new NullOrEmptyException("Plans");
        return all.stream().map(PlanDto::new).toList();
    }
}
