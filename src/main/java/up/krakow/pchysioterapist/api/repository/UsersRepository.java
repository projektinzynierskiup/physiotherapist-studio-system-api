package up.krakow.pchysioterapist.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.krakow.pchysioterapist.api.model.Users;
import up.krakow.pchysioterapist.api.model.enums.ERole;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByEmailAndRoleNot(String email, ERole eRole);
    default Optional<Users> findByEmailWhoIsNotGuest(String email) {
        return findByEmailAndRoleNot(email, ERole.GUEST);
    }
}
