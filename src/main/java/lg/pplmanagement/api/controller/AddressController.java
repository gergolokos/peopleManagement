package lg.pplmanagement.api.controller;

import lg.pplmanagement.api.converter.AddressApiConverter;
import lg.pplmanagement.api.rest.Address;
import lg.pplmanagement.repository.AddressRepository;
import lg.pplmanagement.repository.converter.AddressRepositoryConverter;
import lg.pplmanagement.service.AddressValidationService;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AddressController {

    private final AddressApiConverter addressApiConverter;
    private final AddressRepository addressRepository;
    private final AddressRepositoryConverter addressRepositoryConverter;
    private final AddressValidationService addressValidationService;

    AddressController(AddressApiConverter addressApiConverter, AddressRepository addressRepository, AddressRepositoryConverter addressRepositoryConverter, AddressValidationService addressValidationService) {
        this.addressApiConverter = addressApiConverter;
        this.addressRepository = addressRepository;
        this.addressRepositoryConverter = addressRepositoryConverter;
        this.addressValidationService = addressValidationService;
    }

    @PostMapping("/api/v1/saveAddress")
    public ResponseEntity<Object> saveAddress(@RequestBody Address address) {
        if (addressValidationService.isAddressValid(address)) {
            try {
                lg.pplmanagement.model.Address addressModelToBeSaved = addressApiConverter.convertToModel(address);
                lg.pplmanagement.repository.data.Address savedAddressEntity = addressRepository.save(addressRepositoryConverter.convertToEntity(addressModelToBeSaved));
                lg.pplmanagement.model.Address savedAddressModel = addressRepositoryConverter.convertToModel(savedAddressEntity);
                Address savedAddress = addressApiConverter.convertToApi(savedAddressModel);

                return ResponseEntity.ok(savedAddress);

            } catch (Exception e) {
                e.printStackTrace();
                throw new DataAccessResourceFailureException("Could not save address");
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Address could not be saved");
    }

    @GetMapping("/api/v1/address/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Integer id) {

        try {
            Optional<lg.pplmanagement.repository.data.Address> addressEntityOptional = addressRepository.findById(id);

            if (addressEntityOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            lg.pplmanagement.model.Address addressModel = addressRepositoryConverter.convertToModel(addressEntityOptional.get());
            Address address = addressApiConverter.convertToApi(addressModel);

            return ResponseEntity.ok(address);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessResourceFailureException("Couldn't find address with that id.");
        }
    }

    @PutMapping("/api/v1/address/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Integer id, @RequestBody Address address) {
        try {
            Optional<lg.pplmanagement.repository.data.Address> addressEntityOptional = addressRepository.findById(id);

            if (addressEntityOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            addressRepository.updateAddress(
                    id,
                    address.getPermanentAddress(),
                    address.getTemporaryAddress()
            );

            Optional<lg.pplmanagement.repository.data.Address> updatedAddressEntityOptional = addressRepository.findById(id);

            if (updatedAddressEntityOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            lg.pplmanagement.repository.data.Address updatedAddressEntity = updatedAddressEntityOptional.get();
            lg.pplmanagement.model.Address updatedAddressModel = addressRepositoryConverter.convertToModel(updatedAddressEntity);
            Address updatedAddress = addressApiConverter.convertToApi(updatedAddressModel);


            return ResponseEntity.ok(updatedAddress);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessResourceFailureException("Couldn't update address with that id.");
        }
    }

    @PostMapping("/api/v1/deleteAddress/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable Integer id) {
        try {
            Optional<lg.pplmanagement.repository.data.Address> addressEntityOptional = addressRepository.findById(id);

            if (addressEntityOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            addressRepository.deleteById(addressEntityOptional.get().getId().intValue());

            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting address.");
        }
    }
}

