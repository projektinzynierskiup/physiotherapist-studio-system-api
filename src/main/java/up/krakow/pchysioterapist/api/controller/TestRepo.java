package up.krakow.pchysioterapist.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.model.Users;
import up.krakow.pchysioterapist.api.repository.UsersRepository;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

@RequestMapping(value = ControllerEndpoints.GUEST)
@RestController
public class TestRepo {
    @Autowired
    UsersRepository usersRepository;
    @GetMapping("/test/{email}")
    Users get(@PathVariable String email) {
        System.out.println(email);
        Users users = usersRepository.findByEmailWhoIsNotGuest(email).get();
        System.out.println(users.getRole());
        return usersRepository.findByUsername(email);
    }

    @GetMapping("/test2")
    Users get2(String email) {
        Users users = usersRepository.findByEmail(email).get();
        System.out.println(users.toString());
        return usersRepository.findByUsername(email);
    }
}
