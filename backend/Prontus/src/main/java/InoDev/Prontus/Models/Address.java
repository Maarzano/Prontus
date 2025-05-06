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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long id;

    @Column(name="street", nullable = false, unique = false)
    private String street;

    @Column(name="cep", nullable = false, columnDefinition = "CHAR(9)", unique = false)
    private String cep;

    @Column(name="number", nullable = false, unique = false)
    private String number;

    @Column(name="neighborhood", nullable = false, unique = false)
    private String neighborhood;

    @Column(name="city", nullable = false, unique = false)
    private String city;

    @Column(name="state", nullable = false, unique = false, columnDefinition = "CHAR(2)")
    private String state;
}