package lg.pplmanagement.api.controller;

import lg.pplmanagement.api.converter.ContactApiConverter;
import lg.pplmanagement.api.rest.Contact;
import lg.pplmanagement.repository.ContactRepository;
import lg.pplmanagement.repository.converter.ContactRepositoryConverter;
import lg.pplmanagement.service.ContactValidationService;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ContactController {
    private final ContactApiConverter contactApiConverter;
    private final ContactRepository contactRepository;
    private final ContactRepositoryConverter contactRepositoryConverter;
    private final ContactValidationService contactValidationService;

    public ContactController(ContactApiConverter contactApiConverter, ContactRepository contactRepository, ContactRepositoryConverter contactRepositoryConverter, ContactValidationService contactValidationService) {
        this.contactApiConverter = contactApiConverter;
        this.contactRepository = contactRepository;
        this.contactRepositoryConverter = contactRepositoryConverter;
        this.contactValidationService = contactValidationService;
    }

    @PostMapping("/api/v1/saveContact")
    public ResponseEntity<Object> saveContact(@RequestBody Contact contact) {

        if (contactValidationService.isContactValid(contact)) {
            try {
                lg.pplmanagement.model.Contact contactModelToBeSaved = contactApiConverter.convertToModel(contact);
                lg.pplmanagement.repository.data.Contact savedContactEntity = contactRepository.save(contactRepositoryConverter.convertToEntity(contactModelToBeSaved));
                lg.pplmanagement.model.Contact savedContactModel = contactRepositoryConverter.convertToModel(savedContactEntity);
                Contact savedContact = contactApiConverter.convertToApi(savedContactModel);

                return ResponseEntity.ok(savedContact);

            } catch (Exception e) {
                e.printStackTrace();
                throw new DataAccessResourceFailureException("Could not save contact");
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Contact could not be saved");
    }

    @GetMapping("/api/v1/contact/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Integer id) {
        try {

            Optional<lg.pplmanagement.repository.data.Contact> contactEntityOptional = contactRepository.findById(id);

            if (contactEntityOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            lg.pplmanagement.model.Contact contactModel = contactRepositoryConverter.convertToModel(contactEntityOptional.get());
            Contact contact = contactApiConverter.convertToApi(contactModel);

            return ResponseEntity.ok(contact);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessResourceFailureException("Couldn't find contact with that id.");

        }
    }

    @PutMapping("/api/v1/contact/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Integer id, @RequestBody Contact contact) {

        try {
            Optional<lg.pplmanagement.repository.data.Contact> contactEntityOptional = contactRepository.findById(id);

            if (contactEntityOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            contactRepository.updateContact(
                    id,
                    contact.getEmail(),
                    contact.getPhoneNumber()
            );

            Optional<lg.pplmanagement.repository.data.Contact> updatedContactEntityOptional = contactRepository.findById(id);

            if (updatedContactEntityOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            lg.pplmanagement.repository.data.Contact updatedContactEntity = updatedContactEntityOptional.get();
            lg.pplmanagement.model.Contact updatedContactModel = contactRepositoryConverter.convertToModel(updatedContactEntity);
            Contact updatedContact = contactApiConverter.convertToApi(updatedContactModel);

            return ResponseEntity.ok(updatedContact);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessResourceFailureException("Couldn't update contact with that id.");
        }
    }

    @PostMapping("/api/v1/deleteContact/{id}")
    public ResponseEntity<Object> deleteContact(@PathVariable Integer id) {
        try {
            Optional<lg.pplmanagement.repository.data.Contact> contactEntityOptional = contactRepository.findById(id);

            if (contactEntityOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            contactRepository.deleteById(contactEntityOptional.get().getId().intValue());

            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting contact.");
        }
    }
}
