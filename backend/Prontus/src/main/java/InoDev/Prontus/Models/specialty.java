package InoDev.Prontus.Models;

import InoDev.Prontus.Utils.Enums.Specialties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "specialties")
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialty_id")
    private long id;

    @Column(name = "specialty_type", nullable= false,  unique = false)
    @Enumerated(EnumType.STRING)
    private Specialties specialty;
}
