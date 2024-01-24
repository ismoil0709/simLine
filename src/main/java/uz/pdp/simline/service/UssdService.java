package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.entity.Ussd;

import java.util.List;
import java.util.UUID;

@Service
public interface UssdService {
    void save(Ussd ussd);
    void update(Ussd updatedUssd);
    void delete(UUID id);
    void deleteByCode(String code);
    Ussd getById(UUID id);
    Ussd getByCode(String code);
    List<Ussd> getAll();
}
