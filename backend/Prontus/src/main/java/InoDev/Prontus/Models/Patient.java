package InoDev.Prontus.Models;

import java.sql.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private long id;

    @NotBlank(message = "O nome do paciente não pode estar vazio")
    @Column(name = "patient_name", nullable = false, unique = false, length = 100)
    private String name;

    @NotBlank(message = "O cpf não pode estar vazio")
    @Column(name = "patient_cpf", nullable = false, unique = true, columnDefinition = "CHAR(11)")
    private String cpf;

    @Column(name = "patient_birthday", nullable = true, unique = false)
    @Past(message = "A data de nascimento precisa ser no passado!")
    private Date dataNasc;

    @NotBlank(message = "O celular não pode estar vazio")
    @Column(name = "patient_cellphone", nullable = true, columnDefinition = "CHAR(11)")
    private String cellphone;

    @NotBlank(message = "O e-mail não pode estar vazio")
    @Column(name = "patient_email", nullable = true, unique = true, length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id", unique = false, nullable = true, referencedColumnName = "address_id")
    private Address address;
}
