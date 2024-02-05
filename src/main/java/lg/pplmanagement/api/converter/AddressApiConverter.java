package lg.pplmanagement.api.converter;

import lg.pplmanagement.api.rest.Address;
import lg.pplmanagement.repository.PersonRepository;
import lg.pplmanagement.repository.converter.PersonRepositoryConverter;
import org.springframework.stereotype.Component;

@Component
public class AddressApiConverter extends ApiConverter<Address, lg.pplmanagement.model.Address> {

    private final PersonRepositoryConverter personRepositoryConverter;
    private final PersonRepository personRepository;

    public AddressApiConverter(PersonRepositoryConverter personRepositoryConverter, PersonRepository personRepository) {
        this.personRepositoryConverter = personRepositoryConverter;
        this.personRepository = personRepository;
    }

    @Override
    public lg.pplmanagement.model.Address convertToModel(Address api) {
        return new lg.pplmanagement.model.Address.Builder()
                .setPermanentAddress(api.getPermanentAddress())
                .setTemporaryAddress(api.getTemporaryAddress())
                .setPerson(personRepositoryConverter.convertToModel(personRepository.findById(api.getPersonId().intValue())
                        .orElseThrow(() -> new RuntimeException("Person not found for: " + api.getPersonId()))
                ))
                .build();
    }

    @Override
    public Address convertToApi(lg.pplmanagement.model.Address model) {
        return new Address.Builder()
                .setPermanentAddress(model.getPermanentAddress())
                .setTemporaryAddress(model.getTemporaryAddress())
                .setPersonId(model.getId())
                .build();
    }
}
