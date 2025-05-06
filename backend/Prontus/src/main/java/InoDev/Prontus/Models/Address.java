package InoDev.Prontus.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long id;

    @NotBlank(message = "A rua não pode estar vazia")
    @Column(name="street", nullable = false, unique = false)
    private String street;

    @NotBlank(message = "O cep não pode estar vazio")
    @Column(name="cep", nullable = false, columnDefinition = "CHAR(9)", unique = false)
    private String cep;

    @NotBlank(message = "O numero não pode estar vazio")
    @Column(name="number", nullable = false, unique = false)
    private String number;

    @NotBlank(message = "O bairro não pode estar vazio")
    @Column(name="neighborhood", nullable = false, unique = false)
    private String neighborhood;

    @NotBlank(message = "A cidade não pode estar vazia")
    @Column(name="city", nullable = false, unique = false)
    private String city;

    @NotBlank(message = "O estado não pode estar vazio")
    @Column(name="state", nullable = false, unique = false, columnDefinition = "CHAR(2)")
    private String state;
}