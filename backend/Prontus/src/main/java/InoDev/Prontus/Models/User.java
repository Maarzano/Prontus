package InoDev.Prontus.Models;

import java.time.LocalDateTime;

import InoDev.Prontus.Utils.AdmRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Column(name = "user_id", unique = true, nullable = false)
    private long id;

    @Column(name = "cpf", columnDefinition = "CHAR(11)", unique = true, nullable = false)
    private String cpf;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, unique = false)
    private String password;

    @Column(name = "role", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private AdmRole role;

    @Column(name = "cellphone", length = 15, nullable = true, unique = false) 
    private String cellphone;

    @Column(name = "active", nullable = false, unique = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false, unique = false, updatable = false)
    private LocalDateTime createdAt;

}
