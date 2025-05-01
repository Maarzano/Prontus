package InoDev.Prontus.Mappers;

import InoDev.Prontus.DTO.Address.*;
import InoDev.Prontus.Models.Address;

public class AddressMapper {

    public static Address toModel(CreateAddressDTO dto) {
        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setCep(dto.getCep());
        address.setNumber(dto.getNumber());
        address.setNeighborhood(dto.getNeighborhood());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        return address;
    }

    public static Address toModel(UpdateAddressDTO dto, Address address) {
        address.setStreet(dto.getStreet());
        address.setCep(dto.getCep());
        address.setNumber(dto.getNumber());
        address.setNeighborhood(dto.getNeighborhood());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        return address;
    }

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
}