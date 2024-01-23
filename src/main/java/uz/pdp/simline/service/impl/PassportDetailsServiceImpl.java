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
}
