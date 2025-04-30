package InoDev.Prontus.DTO.Address;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAddressDTO {
    private String street;
    private String cep;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
}