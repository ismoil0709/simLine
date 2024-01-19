package uz.pdp.simline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class SimLineApplication {

    public static void main(String[] args) {
       String s =  Objects.requireNonNullElse(null, null);
//        SpringApplication.run(SimLineApplication.class, args);
    }

}
