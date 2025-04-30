package InoDev.Prontus.Mapper;

import InoDev.Prontus.DTO.Address.AddressDTO;
import InoDev.Prontus.Models.Address;

public class AddressMapper {

    public static AddressDTO toDTO(Address address) {
        return new AddressDTO(
            address.getId(),
            address.getStreet(),
            address.getCep(),
            address.getNumber(),
            address.getNeighborhood(),
            address.getCity(),
            address.getState()
        );
    }

    public static Address toEntity(AddressDTO addressDTO) {
        return new Address(
            0,
            addressDTO.getStreet(),
            addressDTO.getCep(),
            addressDTO.getNumber(),
            addressDTO.getNeighborhood(),
            addressDTO.getCity(),
            addressDTO.getState()
        );
    }
}