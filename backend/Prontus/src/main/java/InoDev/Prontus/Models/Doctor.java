package InoDev.Prontus.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private long id;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "crm", nullable = false, unique = true)
    private String crm;

    @Column(name = "", nullable = false, unique = false)
    private specialty specialty;

    @Column(name = "cellphone", unique = true, nullable = true)
    private String cellphone;

    @Column(name = "actived", nullable = false, unique = false)
    private Boolean actived;

}
