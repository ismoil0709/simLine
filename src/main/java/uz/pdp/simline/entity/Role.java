package uz.pdp.simline.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String role;
    private String description;
    @CreatedDate
    @Column(name = "name",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "name2",nullable = false,updatable = false)
    private LocalDateTime updateAt;
}
