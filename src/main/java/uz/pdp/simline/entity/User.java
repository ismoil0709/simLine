package uz.pdp.simline.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class User extends Auditing{
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
}
