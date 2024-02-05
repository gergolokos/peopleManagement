package lg.pplmanagement.repository.converter;

import lg.pplmanagement.repository.data.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonRepositoryConverter extends RepositoryConverter<Person, lg.pplmanagement.model.Person> {
    @Override
    public lg.pplmanagement.model.Person convertToModel(Person entity) {
        return new lg.pplmanagement.model.Person.Builder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDob(entity.getDob())
                .build();
    }

    @Override
    public Person convertToEntity(lg.pplmanagement.model.Person model) {
        return new Person.Builder()
                .setId(model.getId())
                .setName(model.getName())
                .setDob(model.getDob())
                .build();
    }
}
