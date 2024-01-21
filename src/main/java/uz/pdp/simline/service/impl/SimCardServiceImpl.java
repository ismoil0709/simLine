package uz.pdp.simline.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.SimCardUpdateDto;
import uz.pdp.simline.entity.Plan;
import uz.pdp.simline.entity.SimCard;
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.exception.NullOrEmptyException;
import uz.pdp.simline.repository.PlanRepository;
import uz.pdp.simline.repository.SimCardRepository;
import uz.pdp.simline.service.SimCardService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimCardServiceImpl implements SimCardService {
    private final SimCardRepository simCardRepository;
    private final PlanRepository planRepository;
    
    @Override
    public SimCard getById(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return simCardRepository.findById(id).orElseThrow(
                () -> new NotFoundException("SimCard"));
    }

    @Override
    public SimCard getByNumber(String number) {
        if (number == null || number.isEmpty() || number.isBlank())
            throw new NullOrEmptyException("Number");
        return simCardRepository.findByNumber(number).orElseThrow(
                () -> new NotFoundException("SimCard"));
    }

    @Override
    public SimCard getByPrice(Double minPrice, Double maxPrice) {
        if (minPrice == null || maxPrice == null)
            throw new NullOrEmptyException("Price");
        return simCardRepository.findSimCardByPriceBetweenMaxPriceAndMinPrice
                (minPrice, maxPrice).orElseThrow(
                () -> new NotFoundException("SimCard"));
    }

    @Override
    public SimCard getByActivity(Boolean isActive) {
        if (isActive == null)
            throw new NullOrEmptyException("isActive");
        return simCardRepository.findByIsActive(isActive).orElseThrow(
                () -> new NotFoundException("SimCard"));
    }

    @Override
    public void update(SimCardUpdateDto simCardUpdateDto) {
        if (simCardUpdateDto == null)
            throw new NullOrEmptyException("SimCardUpdateDto");
        if (simCardUpdateDto.getId() == null)
            throw new NullOrEmptyException("Id");
        SimCard simCard = simCardRepository.findById(simCardUpdateDto.getId()).orElseThrow(
                () -> new NotFoundException("SimCard")
        );
        simCardRepository.save(
                SimCard.builder()
                        .id(simCardUpdateDto.getId())
                        .isActive(Objects.requireNonNullElse(simCardUpdateDto.getIsActive(),simCard.getIsActive()))
                        .price(Objects.requireNonNullElse(simCardUpdateDto.getPrice(),simCard.getPrice()))
                        .plan(planRepository.findById(
                                simCardUpdateDto.getPlanId() == null ? simCard.getPlan().getId() : simCardUpdateDto.getPlanId()
                        ).orElseThrow(
                                ()->new NotFoundException("Plan")
                        ))
                        .build()
        );
    }

    @Override
    public List<SimCard> getAllByPlan(Plan plan) {
        if (plan == null)
            throw new NullOrEmptyException("Plan");
        List<SimCard> allByPlan = simCardRepository.findAllByPlan(plan);
        if (allByPlan.isEmpty())
            throw new NullOrEmptyException("SimCards");
        return allByPlan;
    }

    @Override
    public List<SimCard> getAll() {
        List<SimCard> all = simCardRepository.findAll();
        if (all.isEmpty())
            throw new NullOrEmptyException("SimCards");
        return all;
    }
}