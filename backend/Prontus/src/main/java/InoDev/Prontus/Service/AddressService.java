package InoDev.Prontus.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import InoDev.Prontus.DTO.Address.*;
import InoDev.Prontus.Exceptions.ResourceNotFoundException;
import InoDev.Prontus.Mappers.AddressMapper;
import InoDev.Prontus.Models.Address;
import InoDev.Prontus.Repository.AddressRepository;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public AddressDTO createAddress(CreateAddressDTO dto) {
        Address address = AddressMapper.toModel(dto);
        address = addressRepository.save(address);
        return AddressMapper.toDTO(address);
    }

    @Transactional
    public AddressDTO updateAddress(Long id, UpdateAddressDTO dto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));

        address = AddressMapper.toModel(dto, address);
        address = addressRepository.save(address);
        return AddressMapper.toDTO(address);
    }

    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));
        return AddressMapper.toDTO(address);
    }

    public List<AddressDTO> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(AddressMapper::toDTO)
                .toList();
    }

    @Transactional
    public void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new ResourceNotFoundException("Address not found with id: " + id);
        }
        addressRepository.deleteById(id);
    }
}