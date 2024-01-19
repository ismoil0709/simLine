package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.CustomerLoginDto;
import uz.pdp.simline.dto.request.CustomerUpdateDto;
import uz.pdp.simline.dto.respone.JwtDto;
import uz.pdp.simline.dto.request.CustomerRegisterDto;
import uz.pdp.simline.dto.respone.CustomerDto;
import uz.pdp.simline.entity.Customer;

import java.util.List;
import java.util.UUID;

@Service
public interface CustomerService {
    JwtDto register(CustomerRegisterDto customerRegisterDto);
    JwtDto login(CustomerLoginDto customerLoginDto);
    CustomerDto update(CustomerUpdateDto customerDto);
    void delete(UUID id);
    CustomerDto getById(UUID id);
    List<CustomerDto> getAll();
    CustomerDto getByPhoneNumber(String phoneNumber);
    CustomerDto getByUsername(String username);
    CustomerDto getByEmail(String email);
}
