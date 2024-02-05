package lg.pplmanagement.repository.data;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity(name = "Address")
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "permanent", nullable = false)
    private String permanentAddress;

    @Column(name = "temporary")
    private String temporaryAddress;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinColumn(name = "person_id")
    private Person person;

    public Address() {
    }

    public Address(Builder builder) {
        this.id = builder.id;
        this.permanentAddress = builder.permanentAddress;
        this.temporaryAddress = builder.temporaryAddress;
        this.person = builder.person;
    }

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
    }
}

