package lg.pplmanagement.repository.converter;

import lg.pplmanagement.repository.data.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressRepositoryConverter extends RepositoryConverter<Address, lg.pplmanagement.model.Address> {

    private final PersonRepositoryConverter personRepositoryConverter;

    public AddressRepositoryConverter(PersonRepositoryConverter personRepositoryConverter) {
        this.personRepositoryConverter = personRepositoryConverter;
    }

    @Override
    public lg.pplmanagement.model.Address convertToModel(Address entity) {
        return new lg.pplmanagement.model.Address.Builder()
                .setId(entity.getId())
                .setPermanentAddress(entity.getPermanentAddress())
                .setTemporaryAddress(entity.getTemporaryAddress())
                .setPerson(personRepositoryConverter.convertToModel(entity.getPerson()))
                .build();
    }

    @Override
    public Address convertToEntity(lg.pplmanagement.model.Address model) {
        return new Address.Builder()
                .setId(model.getId())
                .setPermanentAddress(model.getPermanentAddress())
                .setTemporaryAddress(model.getTemporaryAddress())
                .setPerson(personRepositoryConverter.convertToEntity(model.getPerson()))
                .build();
    }
}
