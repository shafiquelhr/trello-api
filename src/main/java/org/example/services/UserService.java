package org.example.services;

import org.example.entities.User;

public interface UserService {
    User getUserByEmail(String email);
    User getUserById(int id);
}
