package lg.pplmanagement.service;

import lg.pplmanagement.api.rest.Person;
import lg.pplmanagement.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Service
public class PersonValidationService {

    private final PersonRepository personRepository;

    public PersonValidationService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public boolean isPersonValid(Person person) {
        return isNameValid(person.getName()) && isDateValid(person.getDob());
    }

    private boolean isNameValid(String name) {
        String nameRegex = "^[A-Za-zÁÉÍÓÖŐÚÜŰáéíóöőúüű-]+$";
        Pattern pattern = Pattern.compile(nameRegex);
        return pattern.matcher(name).matches();
    }

    private boolean isDateValid(LocalDate dob) {
        try {
            String dateFormat = "yyyy-MM-dd";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
            String formattedDate = dob.format(formatter);

            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            sdf.setLenient(false);

            sdf.parse(formattedDate);

            return formattedDate.matches("^[\\d-]+$");
        } catch (ParseException e) {
            return false;
        }
    }
}
