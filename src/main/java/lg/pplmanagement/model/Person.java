package lg.pplmanagement.model;

import java.time.LocalDate;
import java.util.Objects;

public class Person {

    private final Long id;
    private final String name;
    private final LocalDate dob;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Person(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.dob = builder.dob;
    }

    public static class Builder {
        private Long id;
        private String name;
        private LocalDate dob;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

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
            return Objects.equals(id, builder.id) && Objects.equals(name, builder.name) && Objects.equals(dob, builder.dob);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, dob);
        }
    }

}
