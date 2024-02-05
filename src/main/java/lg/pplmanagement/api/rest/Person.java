package lg.pplmanagement.api.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;
import java.util.Objects;

@JsonDeserialize(builder = Person.Builder.class)
public class Person {
    @JsonProperty("name")
    private final String name;
    @JsonProperty("dob")
    private final LocalDate dob;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("dob")
    public LocalDate getDob() {
        return dob;
    }

    private Person(Builder builder) {
        this.name = builder.name;
        this.dob = builder.dob;
    }

    public static class Builder {
        @JsonProperty("name")
        private String name;
        @JsonProperty("dob")
        private LocalDate dob;

        @JsonProperty("name")
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        @JsonProperty("dob")
        public Builder setDob(LocalDate dob) {
            this.dob = dob;
            return this;
        }

        public Person build() {
            return new Person(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Builder builder = (Builder) o;
            return Objects.equals(name, builder.name) && Objects.equals(dob, builder.dob);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, dob);
        }
    }
}
