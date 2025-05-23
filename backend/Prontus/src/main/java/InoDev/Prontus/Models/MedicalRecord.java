package InoDev.Prontus.Models;

import java.time.LocalDateTime;

import InoDev.Prontus.Utils.Enums.Diagnostics;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medical_Record")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_record_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "scheduling_id", referencedColumnName = "scheduling_id")
    private Scheduling schedulingId;

    @Column(name = "date_register", nullable = false )
    private LocalDateTime dateRegister;

    @Column(name = "diagnostic", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private Diagnostics diagnostic;

    @NotBlank(message = "A receita não pode estar vazia")
    @Column(name = "recepies", nullable = true)
    private String recepie;

    @Column(name = "anotations", nullable = true)
    private String anotation;
}
