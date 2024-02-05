package lg.pplmanagement.service;

import lg.pplmanagement.api.rest.Address;
import lg.pplmanagement.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressValidationService {

    private final AddressRepository addressRepository;

    public AddressValidationService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public boolean isAddressValid(Address address) {
        return isPermanentAddressValid(address.getPermanentAddress()) && isAddressNumberValid(address);
    }

    private boolean isPermanentAddressValid(String permanent) {
        return permanent != null && !permanent.isEmpty();
    }

    private boolean isAddressNumberValid(Address address) {

        int addressWithoutTemporary = (int) addressRepository.countAddressWithoutTemporary(address.getPersonId());
        int addressWithTemporary = (int) addressRepository.countAddressWithTemporary(address.getPersonId());

        return addressWithoutTemporary + addressWithTemporary < 1;
    }

}
