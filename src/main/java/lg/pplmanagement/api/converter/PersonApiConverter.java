package lg.pplmanagement.api.converter;

import lg.pplmanagement.api.rest.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonApiConverter extends ApiConverter<Person, lg.pplmanagement.model.Person> {

    private final AddressApiConverter addressApiConverter;
    private final ContactApiConverter contactApiConverter;

    @Autowired
    public PersonApiConverter(AddressApiConverter addressApiConverter, ContactApiConverter contactApiConverter) {
        this.addressApiConverter = addressApiConverter;
        this.contactApiConverter = contactApiConverter;

    }

    @Override
    public lg.pplmanagement.model.Person convertToModel(Person api) {
        return new lg.pplmanagement.model.Person.Builder()
                .setName(api.getName())
                .setDob(api.getDob())
                .build();

    }

    @Override
    public Person convertToApi(lg.pplmanagement.model.Person model) {
        return new Person.Builder()
                .setName(model.getName())
                .setDob(model.getDob())
                .build();
    }
}