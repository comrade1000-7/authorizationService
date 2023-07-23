package ru.netology.restspring.repository;

import ru.netology.restspring.DTO.Person;
import ru.netology.restspring.authorities.Authorities;

import java.util.List;

public interface UserRepository {
    void addUser(Person user);
    List<Authorities> getUserAuthorities(Person user);
}
