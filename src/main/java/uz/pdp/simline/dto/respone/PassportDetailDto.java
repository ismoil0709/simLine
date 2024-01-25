package uz.pdp.simline.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.entity.PassportDetail;
import uz.pdp.simline.util.annotations.BirthDate;
import uz.pdp.simline.util.annotations.Name;
import uz.pdp.simline.util.annotations.PassportId;
import uz.pdp.simline.util.annotations.Surname;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class PassportDetailDto {
    private UUID id;
    @Name
    private String name;
    @Surname
    private String surname;
    @BirthDate
    private LocalDate birthDate;
    @PassportId
    private String passportId;
    public PassportDetailDto (PassportDetail passportDetail){
        this.id = passportDetail.getId();
        this.name = passportDetail.getName();
        this.surname = passportDetail.getSurname();
        this.birthDate = passportDetail.getBirthDate();
        this.passportId = passportDetail.getPassportId();
    }
}