package uz.pdp.simline.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.simline.entity.PassportDetail;
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.exception.NullOrEmptyException;
import uz.pdp.simline.repository.PassportDetailRepository;
import uz.pdp.simline.service.PassportDetailsService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PassportDetailsServiceImpl implements PassportDetailsService {
    private final PassportDetailRepository passportDetailRepository;

    @Override
    public List<PassportDetail> getPassposrtDetalis() {
        List<PassportDetail> all = passportDetailRepository.findAll();
        if (all.isEmpty())
            throw new NullOrEmptyException("PassportDetails");
        return all;
    }

    @Override
    public Optional<PassportDetail> getPassportDetailById(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return Optional.ofNullable(passportDetailRepository.findById(id).orElseThrow(
                () -> new NotFoundException("PassportId")));
    }

    @Override
    public void savePassportDetail(PassportDetail passportDetail) {
        if (passportDetail == null)
            throw new NullOrEmptyException("passport not found");
        passportDetailRepository.save(passportDetail);
    }

    @Override
    public void updatePassportDetail(UUID id, PassportDetail updatedDetails) {
        if (passportDetailRepository.existsById(id)) {
            updatedDetails.setId(id);
            passportDetailRepository.save(updatedDetails);
        }
    }

    @Override
    public void deletePassportDetail(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("not found delete id");
        passportDetailRepository.findById(id).orElseThrow(() -> new NotFoundException("PassportDetails"));
    }
}
