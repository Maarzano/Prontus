package InoDev.Prontus.Models;

import InoDev.Prontus.Utils.Specialties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "specialty")
public class specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specilaty_id", unique = true, nullable = false)
    private long id;

    @Column(name = "specialty_type", nullable= false,  unique = false)
    @Enumerated(EnumType.STRING)
    private Specialties specialty;
}
