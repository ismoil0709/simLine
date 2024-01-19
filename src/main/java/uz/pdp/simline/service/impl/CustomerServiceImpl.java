package uz.pdp.simline.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.CustomerUpdateDto;
import uz.pdp.simline.dto.respone.CustomerDto;
import uz.pdp.simline.dto.respone.JwtDto;
import uz.pdp.simline.dto.request.CustomerLoginDto;
import uz.pdp.simline.dto.request.CustomerRegisterDto;
import uz.pdp.simline.entity.Customer;
import uz.pdp.simline.entity.PassportDetail;
import uz.pdp.simline.repository.CustomerRepository;
import uz.pdp.simline.service.CustomerService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public JwtDto register(CustomerRegisterDto customerRegisterDto) {
        if (customerRegisterDto == null){
            ///todo throws any exception
        }
        if (customerRegisterDto.getUsername() == null){
            //todo throws any exception
        }
        if (customerRegisterDto.getPassword() == null){
            //todo throws any exception
        }
        if (customerRegisterDto.getEmail() == null && customerRegisterDto.getPhoneNumber() == null){
            //todo throws any exception
        }
        if (customerRepository.findByUsername(customerRegisterDto.getUsername()).isPresent()) {
            //todo throws any exception
        }
        if (customerRegisterDto.getEmail() != null && customerRepository.findByEmail(customerRegisterDto.getEmail()).isPresent()){
            //todo throws any exception
        }
        if (customerRegisterDto.getPhoneNumber() != null && customerRepository.findByPhoneNumber(customerRegisterDto.getPhoneNumber()).isPresent()){
            //todo throws any exception
        }
        customerRepository.save(
                Customer.builder()
                        .username(customerRegisterDto.getUsername())
                        .password(passwordEncoder.encode(customerRegisterDto.getPassword()))
                        .email(customerRegisterDto.getEmail())
                        .phoneNumber(customerRegisterDto.getPhoneNumber())
                        .build()
        );
        //todo return jwtDto
        return null;
    }

    @Override
    public JwtDto login(CustomerLoginDto customerLoginDto) {
        if (customerLoginDto == null){
            //todo throws any exception
        }
        if (customerLoginDto.username()==null){
            //todo throws any exception
        }
        if (customerLoginDto.password()==null){
            //todo throws any exception
        }
        //todo add orElseThrows()
        Customer customer = customerRepository.findByUsername(customerLoginDto.username()).get();
        if (customer.getPassword().equals(
                passwordEncoder.encode(customerLoginDto.password())
        )){
            //todo return jwtDto
            return null;
        }else {
            //todo throws any exception
        }
        return null;
    }

    @Override
    public CustomerDto update(CustomerUpdateDto customerDto) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        if (id == null){
            //todo throws any exception
        }
        customerRepository.delete(
                customerRepository.findById(id).get()
        );
    }

    @Override
    public CustomerDto getById(UUID id) {
        return null;
    }

    @Override
    public List<CustomerDto> getAll() {
        return null;
    }

    @Override
    public CustomerDto getByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public CustomerDto getByUsername(String username) {
        return null;
    }

    @Override
    public CustomerDto getByEmail(String email) {
        return null;
    }
}
