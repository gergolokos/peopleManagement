package lg.pplmanagement.api.converter;

import lg.pplmanagement.api.rest.Contact;
import lg.pplmanagement.repository.PersonRepository;
import lg.pplmanagement.repository.converter.PersonRepositoryConverter;
import org.springframework.stereotype.Component;

@Component
public class ContactApiConverter extends ApiConverter<Contact, lg.pplmanagement.model.Contact> {

    private final PersonRepositoryConverter personRepositoryConverter;
    private final PersonRepository personRepository;

    public ContactApiConverter(PersonRepositoryConverter personRepositoryConverter, PersonRepository personRepository) {
        this.personRepositoryConverter = personRepositoryConverter;
        this.personRepository = personRepository;
    }

    @Override
    public lg.pplmanagement.model.Contact convertToModel(Contact api) {
        return new lg.pplmanagement.model.Contact.Builder()
                .setEmail(api.getEmail())
                .setPhoneNumber(api.getPhoneNumber())
                .setPerson(personRepositoryConverter.convertToModel(personRepository.findById(api.getPersonId().intValue())
                        .orElseThrow(() -> new RuntimeException("Person not found for: " + api.getPersonId()))
                ))
                .build();
    }

    @Override
    public Contact convertToApi(lg.pplmanagement.model.Contact model) {
        return new Contact.Builder()
                .setEmail(model.getEmail())
                .setPhoneNumber(model.getPhoneNumber())
                .setPersonId(model.getId())
                .build();
    }
}