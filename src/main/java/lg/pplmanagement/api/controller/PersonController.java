package lg.pplmanagement.api.controller;

import lg.pplmanagement.api.converter.PersonApiConverter;
import lg.pplmanagement.api.rest.Person;
import lg.pplmanagement.repository.PersonRepository;
import lg.pplmanagement.repository.converter.PersonRepositoryConverter;
import lg.pplmanagement.service.PersonValidationService;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PersonController {
    private final PersonApiConverter personApiConverter;
    private final PersonRepository personRepository;
    private final PersonRepositoryConverter personRepositoryConverter;
    private final PersonValidationService personValidationService;

    public PersonController(PersonApiConverter personApiConverter, PersonRepository personRepository, PersonRepositoryConverter personRepositoryConverter, PersonValidationService personValidationService) {
        this.personApiConverter = personApiConverter;
        this.personRepository = personRepository;
        this.personRepositoryConverter = personRepositoryConverter;
        this.personValidationService = personValidationService;
    }

    @PostMapping("/api/v1/savePerson")
    public ResponseEntity<Object> savePerson(@RequestBody Person person) {

        if (personValidationService.isPersonValid(person)) {
            try {
                lg.pplmanagement.model.Person personModelToBeSaved = personApiConverter.convertToModel(person);
                lg.pplmanagement.repository.data.Person savedPersonEntity = personRepository.save(personRepositoryConverter.convertToEntity(personModelToBeSaved));
                lg.pplmanagement.model.Person savedPersonModel = personRepositoryConverter.convertToModel(savedPersonEntity);
                Person savedPerson = personApiConverter.convertToApi(savedPersonModel);

                return ResponseEntity.ok(savedPerson);

            } catch (Exception e) {
                e.printStackTrace();
                throw new DataAccessResourceFailureException("Could not save person");
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Person could not be saved");
    }

    @GetMapping("/api/v1/person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Integer id) {
        try {

            Optional<lg.pplmanagement.repository.data.Person> personEntityOptional = personRepository.findById(id);

            if (personEntityOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            lg.pplmanagement.model.Person personModel = personRepositoryConverter.convertToModel(personEntityOptional.get());
            Person person = personApiConverter.convertToApi(personModel);

            return ResponseEntity.ok(person);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessResourceFailureException("Couldn't find person with that id.");
        }
    }

    @PutMapping("/api/v1/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person person) {
        try {

            Optional<lg.pplmanagement.repository.data.Person> personEntityOptional = personRepository.findById(id);

            if (personEntityOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            personRepository.updatePerson(
                    id,
                    person.getName(),
                    person.getDob()
            );

            Optional<lg.pplmanagement.repository.data.Person> updatedPersonEntityOptional = personRepository.findById(id);

            if (updatedPersonEntityOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            lg.pplmanagement.repository.data.Person updatedPersonEntity = updatedPersonEntityOptional.get();
            lg.pplmanagement.model.Person updatedPersonModel = personRepositoryConverter.convertToModel(updatedPersonEntity);
            Person updatedPerson = personApiConverter.convertToApi(updatedPersonModel);

            return ResponseEntity.ok(updatedPerson);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessResourceFailureException("Couldn't update contact with that id.");
        }
    }


    @PostMapping("/api/v1/deletePerson/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable Integer id) {
        try {

            Optional<lg.pplmanagement.repository.data.Person> personEntityOptional = personRepository.findById(id);

            if (personEntityOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            personRepository.deleteById(personEntityOptional.get().getId().intValue());

            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting person.");
        }
    }
}
