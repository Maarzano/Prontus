package InoDev.Prontus.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import InoDev.Prontus.DTO.Address.*;
import InoDev.Prontus.Models.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toModel(CreateAddressDTO dto);

    Address toModel(UpdateAddressDTO dto);

    AddressDTO toDTO(Address address);
}