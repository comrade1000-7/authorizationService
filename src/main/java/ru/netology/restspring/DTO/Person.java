package ru.netology.restspring.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record Person(@NotBlank @Size(min = 2, max = 32) String login,
                     @NotBlank @Size(min = 2, max = 32) String password) {

    @Override
    public boolean equals(Object o) {
        if (!o.getClass().equals(this.getClass())) return false;
        return login.equals(((Person) o).login()) && password.equals(((Person) o).password());
    }
}
