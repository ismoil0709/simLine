package uz.pdp.simline.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SuccessResponse {
    private String message;
    private LocalDateTime time;
    public SuccessResponse(){
        this.message = "Successfully";
    }
    public SuccessResponse(String message){
        this.message = message;
    }
}
