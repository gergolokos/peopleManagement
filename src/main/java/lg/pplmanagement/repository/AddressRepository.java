package lg.pplmanagement.repository;

import jakarta.transaction.Transactional;
import lg.pplmanagement.repository.data.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Address a SET a.permanentAddress = :permanentAddress, a.temporaryAddress = :temporaryAddress WHERE a.id = :id")
    void updateAddress(@Param("id") Integer id,
                       @Param("permanentAddress") String permanentAddress,
                       @Param("temporaryAddress") String temporaryAddress
    );

    @Query("SELECT COUNT(a.id) FROM Address a " +
            "WHERE a.person.id = :person_id AND (a.permanentAddress IS NOT NULL AND a.temporaryAddress IS NULL)")
    long countAddressWithoutTemporary(@Param("person_id") Long personId);

    @Query("SELECT COUNT(a.id) FROM Address a " +
            "WHERE a.person.id = :person_id AND (a.permanentAddress IS NOT NULL AND a.temporaryAddress IS NOT NULL)")
    long countAddressWithTemporary(@Param("person_id") Long personId);
}
