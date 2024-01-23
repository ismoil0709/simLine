package uz.pdp.simline.dto.respone;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final String message;
    private final LocalDateTime localDateTime = LocalDateTime.now();
        public ErrorResponse(String message) {
            this.message = message;
        }
}
