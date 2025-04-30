package InoDev.Prontus.Models;

import java.sql.Date;

import jakarta.persistence.*;
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

    @Column(name = "patient_name", nullable = false, unique = false, length = 100)
    private String name;

    @Column(name = "patient_cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "patient_birthday", nullable = true, unique = false)
    private Date dataNasc;

    @Column(name = "patient_cellphone", nullable = true, length = 15)
    private String cellphone;

    @Column(name = "patient_email", nullable = true, unique = true, length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id", unique = false, nullable = true, referencedColumnName = "address_id")
    private Address address;
}
