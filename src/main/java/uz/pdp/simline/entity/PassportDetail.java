package uz.pdp.simline.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import uz.pdp.simline.util.annotations.BirthDate;
import uz.pdp.simline.util.annotations.Name;
import uz.pdp.simline.util.annotations.PassportId;
import uz.pdp.simline.util.annotations.Surname;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
public class PassportDetail extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Name
    private String name;
    @Surname
    private String surname;
    @BirthDate
    private LocalDate birthDate;
    @PassportId
    private String passportId;

}