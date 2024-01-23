package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.respone.CustomerDto;
import uz.pdp.simline.dto.respone.PassportDetailDto;
import uz.pdp.simline.entity.PassportDetail;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface PassportDetailsService {
    PassportDetailDto update(PassportDetailDto passportDetail);
    void delete(UUID id);
    PassportDetailDto getById(UUID id);
    List<PassportDetailDto> getAll();
    PassportDetailDto getByPassportId(String passportId);
    List<PassportDetailDto> getAllBySurname(String surname);
    List<PassportDetailDto> getAllByName(String name);
    List<PassportDetailDto> getAllByBirthDate(LocalDate birthDate);
}
