package up.krakow.pchysioterapist.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import up.krakow.pchysioterapist.api.model.Users;
import up.krakow.pchysioterapist.api.model.enums.ERole;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
    Optional<Users> findByEmail(String email);

    @Query(value = "select u.* from physioterapist.users as u where u.email = ?1 and u.role = 'GUEST'", nativeQuery = true)
    Users findGuestByEmail(String email);
    Optional<Users> findById(Integer id);
    Optional<Users> findByEmailAndRoleNot(String email, ERole eRole);
    default Optional<Users> findByEmailWhoIsNotGuest(String email) {
        return findByEmailAndRoleNot(email, ERole.GUEST);
    }
}
