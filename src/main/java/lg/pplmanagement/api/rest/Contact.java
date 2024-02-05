package lg.pplmanagement.api.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

@JsonDeserialize(builder = Contact.Builder.class)
public class Contact {
    @JsonProperty("email")
    private final String email;
    @JsonProperty("phoneNumber")
    private final String phoneNumber;
    @JsonProperty("personId")
    private final Long personId;

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("personId")
    public Long getPersonId() {
        return personId;
    }

    private Contact(Builder builder) {
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.personId = builder.personId;
    }

    public static class Builder {
        @JsonProperty("email")
        private String email;
        @JsonProperty("phoneNumber")
        private String phoneNumber;
        private Long personId;

        @JsonProperty("email")
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        @JsonProperty("phoneNumber")
        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        @JsonProperty("personId")
        public Builder setPersonId(Long personId) {
            this.personId = personId;
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
            return Objects.equals(email, builder.email) && Objects.equals(phoneNumber, builder.phoneNumber) && Objects.equals(personId, builder.personId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(email, phoneNumber, personId);
        }
    }
}
