package uz.pdp.simline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SimLineApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimLineApplication.class, args);
    }
}