package uz.pdp.simline.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.simline.entity.Balance;
import uz.pdp.simline.entity.SimCard;
import uz.pdp.simline.repository.SimCardRepository;
import uz.pdp.simline.security.SecurityConfiguration;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Generator{
    private final SimCardRepository simCardRepository;
    public void generator(){
        List<SimCard> simCards = new ArrayList<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i <= 1000000; i++) {
                String number = String.format("%07d", i);
                SimCard simCard= new SimCard(null,"+99877"+number,
                        new Balance(0D,0D,0L,0L),
                        PhoneNumberPricing.calculatePrice(number),
                        false,
                        null
                        );
                System.out.println("+99877"+number);
                simCardRepository.save(simCard);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 1000000; i <= 2000000; i++) {
                String number = String.format("%07d", i);
                SimCard simCard= new SimCard(null,"+99877"+number,
                        new Balance(0D,0D,0L,0L),
                        PhoneNumberPricing.calculatePrice(number),
                        false,
                        null
                        );
                System.out.println("+99877"+number);
                simCardRepository.save(simCard);
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = 2000000; i <= 3000000; i++) {
                String number = String.format("%07d", i);
                SimCard simCard= new SimCard(null,"+99877"+number,
                        new Balance(0D,0D,0L,0L),
                        PhoneNumberPricing.calculatePrice(number),
                        false,
                        null
                        );
                System.out.println("+99877"+number);
                simCardRepository.save(simCard);
            }
        });
        Thread t4 = new Thread(() -> {
            for (int i = 3000000; i <= 4000000; i++) {
                String number = String.format("%07d", i);
                SimCard simCard= new SimCard(null,"+99877"+number,
                        new Balance(0D,0D,0L,0L),
                        PhoneNumberPricing.calculatePrice(number),
                        false,
                        null
                        );
                System.out.println("+99877"+number);
                simCardRepository.save(simCard);
            }
        });
        Thread t5 = new Thread(() -> {
            for (int i = 4000000; i <= 5000000; i++) {
                String number = String.format("%07d", i);
                SimCard simCard= new SimCard(null,"+99877"+number,
                        new Balance(0D,0D,0L,0L),
                        PhoneNumberPricing.calculatePrice(number),
                        false,
                        null
                        );
                System.out.println("+99877"+number);
                simCardRepository.save(simCard);
            }
        });
        Thread t6 = new Thread(() -> {
            for (int i = 5000000; i <= 6000000; i++) {
                String number = String.format("%07d", i);
                SimCard simCard= new SimCard(null,"+99877"+number,
                        new Balance(0D,0D,0L,0L),
                        PhoneNumberPricing.calculatePrice(number),
                        false,
                        null
                        );
                System.out.println("+99877"+number);
                simCardRepository.save(simCard);
            }
        });
        Thread t7 = new Thread(() -> {
            for (int i = 6000000; i <= 7000000; i++) {
                String number = String.format("%07d", i);
                SimCard simCard= new SimCard(null,"+99877"+number,
                        new Balance(0D,0D,0L,0L),
                        PhoneNumberPricing.calculatePrice(number),
                        false,
                        null
                        );
                System.out.println("+99877"+number);
                simCardRepository.save(simCard);
            }
        });
        Thread t8 = new Thread(() -> {
            for (int i = 7000000; i <= 8000000; i++) {
                String number = String.format("%07d", i);
                SimCard simCard= new SimCard(null,"+99877"+number,
                        new Balance(0D,0D,0L,0L),
                        PhoneNumberPricing.calculatePrice(number),
                        false,
                        null
                        );
                System.out.println("+99877"+number);
                simCardRepository.save(simCard);
            }
        });
        Thread t9 = new Thread(() -> {
            for (int i = 8000000; i <= 9000000; i++) {
                String number = String.format("%07d", i);
                SimCard simCard= new SimCard(null,"+99877"+number,
                        new Balance(0D,0D,0L,0L),
                        PhoneNumberPricing.calculatePrice(number),
                        false,
                        null
                        );
                System.out.println("+99877"+number);
                simCardRepository.save(simCard);
            }
        });
        Thread t10 = new Thread(() -> {
            for (int i = 9000000; i <= 9999999; i++) {
                String number = String.format("%07d", i);
                SimCard simCard= new SimCard(null,"+99877"+number,
                        new Balance(0D,0D,0L,0L),
                        PhoneNumberPricing.calculatePrice(number),
                        false,
                        null
                        );
                System.out.println("+99877"+number);
                simCardRepository.save(simCard);
            }
        });
        Thread starter = new Thread(() -> {
            t1.start();
//            t2.start();
//            t3.start();
//            t4.start();
//            t5.start();
//            t6.start();
//            t7.start();
//            t8.start();
//            t9.start();
//            t10.start();
        });
        starter.start();
    }
}