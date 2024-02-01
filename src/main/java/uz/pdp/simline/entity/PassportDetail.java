package uz.pdp.simline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String passportId;
    @CreatedDate
    @Column(name = "name",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "name2",nullable = false,updatable = false)
    private LocalDateTime updateAt;
}