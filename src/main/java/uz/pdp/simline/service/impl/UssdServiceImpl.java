package uz.pdp.simline.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.simline.entity.Ussd;
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.exception.NullOrEmptyException;
import uz.pdp.simline.repository.UssdRepository;
import uz.pdp.simline.service.UssdService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UssdServiceImpl implements UssdService {

    private final UssdRepository ussdRepository;

    @Override
    public void createUssd(Ussd ussd) {
        if (ussd == null)
            throw new NullOrEmptyException("Ussd");
        if (isNullOrEmpty(ussd.getCode()))
            throw new NullOrEmptyException("Code");
        if (isNullOrEmpty(ussd.getDescription()))
            throw new NullOrEmptyException("Description");
        ussdRepository.save(ussd);
    }

    @Override
    public void updateUssd(Ussd updatedUssd) {
        if (updatedUssd == null)
            throw new NullOrEmptyException("Updated Ussd");
        if (updatedUssd.getId() == null)
            throw new NullOrEmptyException("Id");
        Ussd ussd = ussdRepository.findById(updatedUssd.getId()).orElseThrow(
                () -> new NotFoundException("Ussd")
        );
        ussdRepository.save(Ussd.builder()
                .id(updatedUssd.getId())
                .code(Objects.requireNonNullElse(updatedUssd.getCode(), ussd.getCode()))
                .description(Objects.requireNonNullElse(updatedUssd.getDescription(), ussd.getDescription()))
                .build()
        );
    }

    @Override
    public void deleteUssd(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        ussdRepository.delete(
                ussdRepository.findById(id).orElseThrow(
                        () -> new NotFoundException("Ussd")
                )
        );
    }

    @Override
    public void deleteByCode(String code) {
        if (isNullOrEmpty(code))
            throw new NullOrEmptyException("Code");
        ussdRepository.delete(
                ussdRepository.findByCode(code).orElseThrow(
                        () -> new NotFoundException("Ussd")
                ));
    }

    @Override
    public Ussd getUssdById(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return ussdRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Ussd")
        );
    }

    @Override
    public Ussd getUssdByCode(String code) {
        if (isNullOrEmpty(code))
            throw new NullOrEmptyException("Code");
        return ussdRepository.findByCode(code).orElseThrow(
                () -> new NotFoundException("Ussd")
        );
    }

    @Override
    public List<Ussd> getAll() {
        if (ussdRepository.findAll().isEmpty())
            throw new NotFoundException("Ussds");
        return ussdRepository.findAll();
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
