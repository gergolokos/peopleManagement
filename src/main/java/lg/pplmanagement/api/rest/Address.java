package lg.pplmanagement.api.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

@JsonDeserialize(builder = Address.Builder.class)
public class Address {
    @JsonProperty("permanentAddress")
    private final String permanentAddress;
    @JsonProperty("temporaryAddress")
    private final String temporaryAddress;
    @JsonProperty("personId")
    private final Long personId;

    @JsonProperty("permanentAddress")
    public String getPermanentAddress() {
        return permanentAddress;
    }

    @JsonProperty("temporaryAddress")
    public String getTemporaryAddress() {
        return temporaryAddress;
    }

    @JsonProperty("personId")
    public Long getPersonId() {
        return personId;
    }

    private Address(Builder builder) {
        this.permanentAddress = builder.permanentAddress;
        this.temporaryAddress = builder.temporaryAddress;
        this.personId = builder.personId;
    }

    public static class Builder {
        @JsonProperty("permanentAddress")
        private String permanentAddress;
        @JsonProperty("temporaryAddress")
        private String temporaryAddress;
        @JsonProperty("personId")
        private Long personId;

        @JsonProperty("permanentAddress")
        public Builder setPermanentAddress(String permanentAddress) {
            this.permanentAddress = permanentAddress;
            return this;
        }

        @JsonProperty("temporaryAddress")
        public Builder setTemporaryAddress(String temporaryAddress) {
            this.temporaryAddress = temporaryAddress;
            return this;
        }

        @JsonProperty("personId")
        public Builder setPersonId(Long personId) {
            this.personId = personId;
            return this;
        }

        public Address build() {
            return new Address(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Builder builder = (Builder) o;
            return Objects.equals(permanentAddress, builder.permanentAddress) && Objects.equals(temporaryAddress, builder.temporaryAddress) && Objects.equals(personId, builder.personId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(permanentAddress, temporaryAddress, personId);
        }
    }


}
