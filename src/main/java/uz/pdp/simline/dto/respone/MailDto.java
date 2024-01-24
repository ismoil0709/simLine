package uz.pdp.simline.dto.respone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDto {
    private String from;
    private String to;
    private String subject;
    private String message;
}
