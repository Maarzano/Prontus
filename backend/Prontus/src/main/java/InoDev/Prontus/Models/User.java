package InoDev.Prontus.Models;

import java.time.LocalDateTime;

import InoDev.Prontus.Utils.Enums.AdmRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "user_cpf", unique = true, nullable = false, length = 14)
    private String cpf;

    @Column(name = "user_name", nullable = false, unique = false)
    private String name;

    @Column(name = "user_email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false, unique = false)
    private String password;

    @Column(name = "user_role", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private AdmRole role;

    @Column(name = "user_cellphone", length = 15, nullable = true, unique = false) 
    private String cellphone;

    @Column(name = "user_active", nullable = false, unique = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active;

    @Column(name = "created_at", nullable = false, unique = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }

}
