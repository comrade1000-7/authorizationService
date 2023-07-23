package ru.netology.restspring.repository;

import org.springframework.stereotype.Repository;
import ru.netology.restspring.DTO.Person;
import ru.netology.restspring.authorities.Authorities;
import ru.netology.restspring.exceptions.UnauthorizedUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final ConcurrentHashMap<Person, List<Authorities>> users = new ConcurrentHashMap<>();

    public void addUser(Person user) {
        List<Authorities> full = new ArrayList<>();
        full.add(Authorities.READ);
        full.add(Authorities.DELETE);
        full.add(Authorities.WRITE);
        if (!users.containsKey(user)) {
            users.put(user, full);
        } else {
            throw new UnauthorizedUser("User " + user + " already exist");
        }
    }
    public List<Authorities> getUserAuthorities(Person user) {
        if (users.containsKey(user)) {
            return users.get(user);
        } else {
            throw new UnauthorizedUser("Unknown user " + user);
        }
    }
}
