package InoDev.Prontus.Models;

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
    @Column(name = "patient_id", unique = true, nullable = false)
    private long id;

    @Column(name = "patient_Name", nullable = false)
    private String name;

    @Column(name = "patient_CPF", nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "patient_CellPhoneNumber", nullable = false, length = 15)
    private String phone;

    @Column(name = "patient_Email", nullable = false, unique = true)
    private String email;
}
