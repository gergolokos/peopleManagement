package lg.pplmanagement.service;

import lg.pplmanagement.api.rest.Contact;
import lg.pplmanagement.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ContactValidationService {

    private final ContactRepository contactRepository;

    public ContactValidationService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public boolean isContactValid(Contact contact) {
        return isEmailValid(contact.getEmail()) && isPhoneNumberValid(contact.getPhoneNumber()) &&
                !isEmailAlreadyInUse(contact.getEmail()) && !isPhoneNumberAlreadyInUse(contact.getPhoneNumber());
    }

    private boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        String phoneNumberRegex = "^(\\+36|06)?(20|30|70)\\d{7}$";

        Pattern pattern = Pattern.compile(phoneNumberRegex);

        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    private boolean isEmailAlreadyInUse(String email) {
        return contactRepository.findByEmail(email).isPresent();
    }

    private boolean isPhoneNumberAlreadyInUse(String phoneNumber) {
        return contactRepository.findByPhoneNumber(phoneNumber).isPresent();
    }
}