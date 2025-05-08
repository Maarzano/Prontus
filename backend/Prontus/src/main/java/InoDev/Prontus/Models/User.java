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
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "O CPF não pode estar vazio")
    @Column(name = "user_cpf", unique = true, columnDefinition = "CHAR(11)")
    private String cpf;

    @NotBlank(message = "O nome não pode estar vazio")
    @Column(name = "user_name", nullable = false, unique = false)
    private String name;

    @NotBlank(message = "O e-mail não pode estar vazio")
    @Column(name = "user_email", length = 100, nullable = false, unique = true)
    private String email;

    @NotBlank(message = "A senha não pode estar vazia")
    @Column(name = "user_password", nullable = false, unique = false)
    private String password;

    @Column(name = "user_role", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private AdmRole role;

    @Column(name = "user_cellphone", columnDefinition = "CHAR(11)", nullable = true, unique = false) 
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
