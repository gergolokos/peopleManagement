package lg.pplmanagement.repository.converter;

import lg.pplmanagement.repository.data.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactRepositoryConverter extends RepositoryConverter<Contact, lg.pplmanagement.model.Contact> {

    private final PersonRepositoryConverter personRepositoryConverter;

    public ContactRepositoryConverter(PersonRepositoryConverter personRepositoryConverter) {
        this.personRepositoryConverter = personRepositoryConverter;
    }

    @Override
    public lg.pplmanagement.model.Contact convertToModel(Contact entity) {
        return new lg.pplmanagement.model.Contact.Builder()
                .setId(entity.getId())
                .setEmail(entity.getEmail())
                .setPhoneNumber(entity.getPhoneNumber())
                .setPerson(personRepositoryConverter.convertToModel(entity.getPerson()))
                .build();
    }

    @Override
    public Contact convertToEntity(lg.pplmanagement.model.Contact model) {
        return new Contact.Builder()
                .setId(model.getId())
                .setEmail(model.getEmail())
                .setPhoneNumber(model.getPhoneNumber())
                .setPerson(personRepositoryConverter.convertToEntity(model.getPerson()))
                .build();
    }

}
