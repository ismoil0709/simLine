package uz.pdp.simline.config.auditing;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import uz.pdp.simline.config.UserContext;
import uz.pdp.simline.entity.Employee;

@Configuration
@RequiredArgsConstructor
public class AuditConfiguration {
    private final UserContext userContext;
}
