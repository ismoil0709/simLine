package uz.pdp.simline.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.SimCardUpdateDto;
import uz.pdp.simline.entity.Plan;
import uz.pdp.simline.entity.SimCard;
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.exception.NullOrEmptyException;
import uz.pdp.simline.repository.SimCardRepository;
import uz.pdp.simline.service.SimCardService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimCardServiceImpl implements SimCardService {
    private final SimCardRepository simCardRepository;


    @Override
    public SimCard getById(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id is empty!");
        return simCardRepository.findById(id).orElseThrow(
                () -> new NotFoundException("SimCard not found!"));
    }

    @Override
    public SimCard getByNumber(String number) {
        if (number == null || number.isEmpty() || number.isBlank())
            throw new NullOrEmptyException("Number is empty!");
        return simCardRepository.findByNumber(number).orElseThrow(
                () -> new NotFoundException("SimCard not found!"));
    }

    @Override
    public SimCard getByPrice(Double minPrice, Double maxPrice) {
        if (minPrice == null || maxPrice == null)
            throw new NullOrEmptyException("Price is empty!");
        return simCardRepository.findSimCardByPriceBetweenMaxPriceAndMinPrice
                (minPrice, maxPrice).orElseThrow(
                () -> new NotFoundException("SimCard not found!"));
    }

    @Override
    public SimCard getByActivity(Boolean isActive) {
        if (isActive == null)
            throw new NullOrEmptyException("isActive is empty!");
        return simCardRepository.findByIsActive(isActive).orElseThrow(
                () -> new NotFoundException("SimCard not found!"));
    }

    @Override
    public void update(SimCardUpdateDto simCardUpdateDto) {
        //update not ready yet
    }

    @Override
    public List<SimCard> getAllByPlan(Plan plan) {
        if (plan == null)
            throw new NullOrEmptyException("Plan is empty!");
        return simCardRepository.findAllByPlan(plan);
    }

    @Override
    public List<SimCard> getAll() {
        return simCardRepository.findAll();
    }
}