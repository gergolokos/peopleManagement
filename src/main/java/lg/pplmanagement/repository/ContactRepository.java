package lg.pplmanagement.repository;

import jakarta.transaction.Transactional;
import lg.pplmanagement.repository.data.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.email = :email, c.phoneNumber = :phoneNumber WHERE c.id = :id")
    void updateContact(@Param("id") Integer id,
                       @Param("email") String email,
                       @Param("phoneNumber") String phoneNumber
    );

    @Query("SELECT c FROM Contact c WHERE c.email = :email")
    Optional<Contact> findByEmail(@Param("email") String email);

    @Query("SELECT c FROM Contact c WHERE c.phoneNumber = :phoneNumber")
    Optional<Contact> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
