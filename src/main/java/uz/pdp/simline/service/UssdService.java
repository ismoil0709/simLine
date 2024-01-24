package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.entity.Ussd;

import java.util.List;
import java.util.UUID;

@Service
public interface UssdService {
    void createUssd(Ussd ussd);
    void updateUssd(Ussd updatedUssd);
    void deleteUssd(UUID id);
    void deleteByCode(String code);
    Ussd getUssdById(UUID id);
    Ussd getUssdByCode(String code);
    List<Ussd> getAll();
}
