package lg.pplmanagement.repository;

import lg.pplmanagement.repository.data.Contact;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    @Disabled
    void checkIfTheContactGetsUpdated() {
        // Given
        Contact contact = new Contact.Builder()
                .setEmail("test@test.com")
                .setPhoneNumber("+3630123456")
                .build();

        contact = contactRepository.save(contact);

        // When
        contactRepository.updateContact(contact.getId().intValue(), "updated@test.com", "+3670123456");

        // Then
        Optional<Contact> updatedContactOptional = contactRepository.findById(contact.getId().intValue());
        assertTrue(updatedContactOptional.isPresent(), "Contact not found after update");

        Contact updatedContact = updatedContactOptional.get();
        assertEquals("updated@test.com", updatedContact.getEmail(), "Email not updated");
        assertEquals("+3670123456", updatedContact.getPhoneNumber(), "PhoneNumber not updated");
    }


    @Test
    void checkIfContactCanBeFindByEmail() {
        // Given
        Contact contact = new Contact.Builder()
                .setEmail("test@test.com")
                .setPhoneNumber("+3630123456")
                .build();

        contactRepository.save(contact);

        // When
        Optional<Contact> foundContactOptional = contactRepository.findByEmail("test@test.com");

        // Then
        assertTrue(foundContactOptional.isPresent(), "Contact not found by email");
        assertEquals("test@test.com", foundContactOptional.get().getEmail(), "Incorrect email found");
    }

    @Test
    void checkIfContactCanBeFindByPhoneNumber() {
        // Given
        Contact contact = new Contact.Builder()
                .setEmail("test@test.com")
                .setPhoneNumber("+3630123456")
                .build();

        contactRepository.save(contact);

        // When
        Optional<Contact> foundContactOptional = contactRepository.findByPhoneNumber("+3630123456");

        // Then
        assertTrue(foundContactOptional.isPresent(), "Contact not found by phone number");
        assertEquals("+3630123456", foundContactOptional.get().getPhoneNumber(), "Incorrect phone number found");
    }
}
