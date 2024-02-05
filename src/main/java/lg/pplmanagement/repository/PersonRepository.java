package lg.pplmanagement.repository;

import jakarta.transaction.Transactional;
import lg.pplmanagement.repository.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Person p SET p.name = :name, p.dob = :dob WHERE p.id = :id")
    void updatePerson(
            @Param("id") Integer id,
            @Param("name") String name,
            @Param("dob") LocalDate dob
    );
}
