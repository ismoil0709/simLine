package uz.pdp.simline.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.respone.PassportDetailDto;
import uz.pdp.simline.entity.PassportDetail;
import uz.pdp.simline.exception.AlreadyExistsException;
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.exception.NullOrEmptyException;
import uz.pdp.simline.repository.PassportDetailRepository;
import uz.pdp.simline.service.PassportDetailsService;
import uz.pdp.simline.util.Validations;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PassportDetailsServiceImpl implements PassportDetailsService {
    private final PassportDetailRepository passportDetailRepository;

    @Override
    public PassportDetailDto update(PassportDetailDto passportDetailDto) {
        if (passportDetailDto == null)
            throw new NullOrEmptyException("PassportDetailDto");
        if (passportDetailDto.getId() == null)
            throw new NullOrEmptyException("Id");
        if (passportDetailDto.getPassportId() != null
                && passportDetailRepository.findByPassportId(passportDetailDto.getPassportId()).isPresent())
            throw new AlreadyExistsException("PassportId");
        PassportDetail passportDetail = passportDetailRepository.findById(passportDetailDto.getId()).orElseThrow(() -> new NotFoundException("PassportDetail"));
        return new PassportDetailDto(passportDetailRepository.save(
                PassportDetail.builder()
                        .id(passportDetailDto.getId())
                        .name(Validations.requireNonNullElse(passportDetailDto.getName(),passportDetail.getName()))
                        .surname(Validations.requireNonNullElse(passportDetailDto.getSurname(),passportDetail.getSurname()))
                        .birthDate(Validations.requireNonNullElse(passportDetailDto.getBirthDate(),passportDetail.getBirthDate()))
                        .passportId(Validations.requireNonNullElse(passportDetailDto.getPassportId(),passportDetail.getPassportId()))
                        .build()
        ));
    }

    @Override
    public void delete(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        passportDetailRepository.delete(passportDetailRepository.findById(id).orElseThrow(
                ()->new NotFoundException("PassportDetail")
        ));
    }

    @Override
    public PassportDetailDto getById(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return new PassportDetailDto(passportDetailRepository.findById(id).orElseThrow(
                ()->new NotFoundException("PassportDetail")
        ));
    }

    @Override
    public List<PassportDetailDto> getAll() {
        List<PassportDetail> all = passportDetailRepository.findAll();
        if (all.isEmpty())
            throw new NullOrEmptyException("PassportDetails");
        return all.stream().map(PassportDetailDto::new).toList();
    }

    @Override
    public PassportDetailDto getByPassportId(String passportId) {
        if (Validations.isNullOrEmpty(passportId))
            throw new NullOrEmptyException("PassportId");
        return new PassportDetailDto(passportDetailRepository.findByPassportId(passportId).orElseThrow(
                ()->new NotFoundException("PassportDetail")
        ));
    }

    @Override
    public List<PassportDetailDto> getAllBySurname(String surname) {
        if (Validations.isNullOrEmpty(surname))
            throw new NullOrEmptyException("Surname");
        return passportDetailRepository.findAllBySurname(surname).stream().map(PassportDetailDto::new).toList();
    }

    @Override
    public List<PassportDetailDto> getAllByName(String name) {
        if (Validations.isNullOrEmpty(name))
            throw new NullOrEmptyException("Name");
        return passportDetailRepository.findAllByName(name).stream().map(PassportDetailDto::new).toList();
    }

    @Override
    public List<PassportDetailDto> getAllByBirthDate(LocalDate birthDate) {
        if (birthDate == null)
            throw new NullOrEmptyException("BirthDate");
        return passportDetailRepository.findAllByBirthDate(birthDate).stream().map(PassportDetailDto::new).toList();
    }

    @Override
    public PassportDetailDto getByUserId(UUID userId) {
        if (userId == null)
            throw new NullOrEmptyException("UserId");
        return new PassportDetailDto(passportDetailRepository.findByUserId(userId).orElseThrow(
                ()->new NotFoundException("PassportDetail")
        ));
    }
}
