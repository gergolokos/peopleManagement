package lg.pplmanagement.repository.data;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity(name = "Contact")
@Table(name = "contact", uniqueConstraints = {
        @UniqueConstraint(name = "email", columnNames = "email"),
        @UniqueConstraint(name = "phone_number", columnNames = "phone_number")})

public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinColumn(name = "person_id")
    private Person person;

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

    public Contact() {
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
    }
}
