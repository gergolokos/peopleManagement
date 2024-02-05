package lg.pplmanagement.repository;

import lg.pplmanagement.repository.data.Person;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @Disabled
    void checksIfThePersonGetsUpdated() {
        // Given
        Person person = new Person.Builder()
                .setId(1L)
                .setName("John")
                .setDob(LocalDate.of(2000, 1, 1))
                .build();

        person = personRepository.save(person);

        // When
        personRepository.updatePerson(person.getId().intValue(), "Janos", LocalDate.of(2000, 2, 2));

        // Then
        Optional<Person> updatedPersonOptional = personRepository.findById(person.getId().intValue());
        assertTrue(updatedPersonOptional.isPresent(), "Person not found after update");

        Person updatedPerson = updatedPersonOptional.get();
        assertEquals("Janos", updatedPerson.getName(), "Name not updated");
        assertEquals(LocalDate.of(2000, 2, 2), updatedPerson.getDob(), "Dob not updated");
    }
}