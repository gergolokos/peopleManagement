package lg.pplmanagement.repository;

import lg.pplmanagement.repository.data.Address;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @Disabled
    void itShouldCheckIfAddressEntityGetsUpdated() {
        // Given
        Address address = new Address.Builder()
                .setId(1L)
                .setPermanentAddress("Permanent")
                .setTemporaryAddress("Temporary")
                .build();

        // When
        addressRepository.save(address);
        addressRepository.updateAddress(1, "NewPermanent", "NewTemporary");

        // Then
        Address updatedAddress = addressRepository.findById(1).orElse(null);
        assertEquals("NewPermanent", Objects.requireNonNull(updatedAddress).getPermanentAddress());
        assertEquals("NewTemporary", Objects.requireNonNull(updatedAddress).getTemporaryAddress());
    }


    @Test
    @Disabled
    void countAddressWithoutTemporary() {
        // Given
        Long personId = 1L;
        Address address = new Address.Builder()
                .setPermanentAddress("Permanent")
                .setTemporaryAddress(null)
                .build();

        addressRepository.save(address);

        // When
        long count = addressRepository.countAddressWithoutTemporary(personId);

        // Then
        assertEquals(1, count);
    }


    @Test
    @Disabled
    void countAddressWithTemporary() {
        // Given
        Long personId = 1L;
        Address address = new Address.Builder()
                .setPermanentAddress("Permanent")
                .setTemporaryAddress("Temporary")
                .build();

        addressRepository.save(address);

        // When
        long count = addressRepository.countAddressWithTemporary(personId);

        // Then
        assertEquals(1, count);
    }
}