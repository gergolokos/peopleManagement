package lg.pplmanagement.api.rest;

import java.util.Objects;

public class Address {
    private final Long id;
    private final String permanentAddress;
    private final String temporaryAddress;

    private final Person person;

    public Long getId() {
        return id;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public String getTemporaryAddress() {
        return temporaryAddress;
    }

    public Person getPerson() {
        return person;
    }

    Address(Builder builder) {
        this.id = builder.id;
        this.permanentAddress = builder.permanentAddress;
        this.temporaryAddress = builder.temporaryAddress;
        this.person = builder.person;
    }

    public static class Builder {
        private Long id;
        private String permanentAddress;
        private String temporaryAddress;
        private Person person;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setPermanentAddress(String permanentAddress) {
            this.permanentAddress = permanentAddress;
            return this;
        }

        public Builder setTemporaryAddress(String temporaryAddress) {
            this.temporaryAddress = temporaryAddress;
            return this;
        }

        public Builder setPerson(Person person) {
            this.person = person;
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
            return Objects.equals(id, builder.id) && Objects.equals(permanentAddress, builder.permanentAddress) && Objects.equals(temporaryAddress, builder.temporaryAddress) && Objects.equals(person, builder.person);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, permanentAddress, temporaryAddress, person);
        }
    }
}
