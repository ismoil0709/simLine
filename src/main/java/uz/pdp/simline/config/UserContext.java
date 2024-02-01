package uz.pdp.simline.config;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UserContext {
    public Long getId(){
        return new Random().nextLong(1, 1000);
    }
}
