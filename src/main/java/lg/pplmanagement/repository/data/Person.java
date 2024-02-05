package lg.pplmanagement.repository.data;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "Person")
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private LocalDate dob;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Person() {
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
    }
}
