package ru.netology.restspring.service;

import org.springframework.stereotype.Service;
import ru.netology.restspring.DTO.Person;
import ru.netology.restspring.authorities.Authorities;
import ru.netology.restspring.exceptions.InvalidCredentials;
import ru.netology.restspring.exceptions.UnauthorizedUser;
import ru.netology.restspring.repository.UserRepositoryImpl;

import java.util.List;

@Service
public class AuthorizationService {
    private final UserRepositoryImpl userRepositoryImpl;

    public AuthorizationService(UserRepositoryImpl userRepositoryImpl) {
        this.userRepositoryImpl = userRepositoryImpl;
    }

    public List<Authorities> getAuthorities(Person user) {
        if (isEmpty(user.login()) || isEmpty(user.password())) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepositoryImpl.getUserAuthorities(user);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    public void signUpUser(Person user) {
        userRepositoryImpl.addUser(user);
    }


    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
