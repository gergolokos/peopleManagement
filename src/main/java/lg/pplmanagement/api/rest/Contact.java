package lg.pplmanagement.api.rest;

import java.util.Objects;

public class Contact {
    private final Long id;
    private final String email;
    private final String phoneNumber;
    private final Person person;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Person getPerson() {
        return person;
    }

    public Contact(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.person = builder.person;
    }

    public static class Builder {
        private Long id;
        private String email;
        private String phoneNumber;
        private Person person;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setPerson(Person person) {
            this.person = person;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Builder builder = (Builder) o;
            return Objects.equals(id, builder.id) && Objects.equals(email, builder.email) && Objects.equals(phoneNumber, builder.phoneNumber) && Objects.equals(person, builder.person);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, email, phoneNumber, person);
        }
    }
}
