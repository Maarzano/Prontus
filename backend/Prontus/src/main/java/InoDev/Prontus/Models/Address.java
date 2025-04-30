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
    @Column(name = "address_id", unique=true,nullable=false)
    private long address_id;

    @Column(name="street", nullable = false, unique=false)
    private String street;

    @Column(name="cep", nullable = false, columnDefinition= "CHAR(8)", length = 8, unique=false)
    private long cep;

    @Column(name="number", nullable=false, unique=false)
    private String number;

    @Column(name="neighborhood", nullable = false, unique=false)
    private String neighborhood;

    @Column(name="city", nullable = false, unique=false)
    private String city;

    @Column(name="country", nullable = false, unique=false)
    private String country;
}